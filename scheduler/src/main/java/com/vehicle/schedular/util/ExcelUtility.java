package com.vehicle.schedular.util;

import com.vehicle.schedular.controller.ValidatorController;
import com.vehicle.schedular.model.FileDTO;
import com.vehicle.schedular.model.VehicleDto;
import com.vehicle.schedular.model.VehicleErrorLogDTO;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.DataFormatException;

@Component
@Validated
public class ExcelUtility {

    @Autowired
    private ValidatorController validatorController;

    private static final String TOPIC = "vehicle_reg_pass";
    private static final String TOPIC1 = "vehicle_reg_fail";
    @Autowired
    private KafkaTemplate<String, VehicleDto> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, VehicleErrorLogDTO> kafkaTemplatefail;

    public FileDTO processExcel(byte[] byteArray, String id, String fileName, String user) {
        FileDTO fileDTO = new FileDTO();
        List<VehicleDto> vehicleDTOList = new ArrayList<>();
        List<VehicleErrorLogDTO> errorDTO = new ArrayList<>();
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        Workbook workbook = null;
        Sheet sheet = null;
        int successCount = 0;
        int failureCount = 0;
        int totalCount = 0;

        try {
            workbook = Workbook.getWorkbook(inputStream);
            sheet = workbook.getSheet(0);

            for (int i = 1; i < sheet.getRows(); i++) {
                try {
                VehicleDto vehicleDTO = new VehicleDto();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yy");

                vehicleDTO.setBrand(sheet.getCell(1, i).getContents());
                vehicleDTO.setCreated_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
                vehicleDTO.setCreated_user(user);
                vehicleDTO.setCurrency(sheet.getCell(4, i).getContents());
                vehicleDTO.setIs_Insured(Boolean.valueOf(sheet.getCell(5, i).getContents()));

                LocalDate mfgDate = LocalDate.parse(sheet.getCell(6, i).getContents(), dateFormat);
                if(mfgDate.isAfter(LocalDate.now())){
                    throw new DataFormatException("Invalid Manufacturing Date");
                }
                vehicleDTO.setMfg_Date(mfgDate.toString());
                vehicleDTO.setPrice(sheet.getCell(7, i).getContents());
                LocalDate regDate = LocalDate.parse(sheet.getCell(8, i).getContents(), dateFormat);
                    if(regDate.isBefore(mfgDate)){
                        throw new DataFormatException("Invalid Registration Date");
                    }
                vehicleDTO.setReg_Date(regDate.toString());

                vehicleDTO.setReg_Number(sheet.getCell(9, i).getContents());
                vehicleDTO.setSelling_Dealer(sheet.getCell(10, i).getContents());
                vehicleDTO.setVin(sheet.getCell(11, i).getContents());
                vehicleDTO.setWarranty_Coverage(sheet.getCell(12, i).getContents());
                vehicleDTO.setFileId(id.toString());

                LocalDate warrantyDate = LocalDate.parse(sheet.getCell(13, i).getContents(), dateFormat);
                    if(warrantyDate.isBefore(regDate)){
                        throw new DataFormatException("Invalid Warranty Date");
                    }
                vehicleDTO.setWarranty_Date(warrantyDate.toString());


                    ResponseEntity<Object> res = validatorController.valid(vehicleDTO);
                    vehicleDTOList.add(vehicleDTO);
                    successCount++;
                } catch (ConstraintViolationException ex) {
                    VehicleErrorLogDTO vehicleErrorLogDTO = new VehicleErrorLogDTO();
                    String fieldName = ((PathImpl) ex.getConstraintViolations().stream().findFirst().get().getPropertyPath()).getLeafNode().getName();
//

                    vehicleErrorLogDTO.setErrorMsg(fieldName + ":" + ex.getConstraintViolations().stream().findFirst().get().getMessage());
                    vehicleErrorLogDTO.setId(String.valueOf(UUID.randomUUID()));
                    vehicleErrorLogDTO.setFileId(id.toString());
                    vehicleErrorLogDTO.setFileName(fileName);
                    vehicleErrorLogDTO.setCreatedTime(LocalDateTime.now().toString());
                    vehicleErrorLogDTO.setExceptionMsg(ex.getStackTrace().toString());
                    errorDTO.add(vehicleErrorLogDTO);
                    failureCount++;

                }catch (DataFormatException de){
                    VehicleErrorLogDTO vehicleErrorLogDTO = new VehicleErrorLogDTO();
                    vehicleErrorLogDTO.setErrorMsg(de.getMessage());
                    vehicleErrorLogDTO.setId(String.valueOf(UUID.randomUUID()));
                    vehicleErrorLogDTO.setFileId(id.toString());
                    vehicleErrorLogDTO.setFileName(fileName);
                    vehicleErrorLogDTO.setCreatedTime(LocalDateTime.now().toString());
                    vehicleErrorLogDTO.setExceptionMsg(de.getStackTrace().toString());
                    errorDTO.add(vehicleErrorLogDTO);
                    failureCount++;
                }
                    finally {
                    continue;
                }
            }
            workbook.close();
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        fileDTO.setFileName(fileName);
        fileDTO.setFileId(id);
        fileDTO.setSuccessCount(successCount);
        fileDTO.setFailureCount(failureCount);

        fileDTO.setVehicleDtoList(vehicleDTOList);
        fileDTO.setVehicleErrorLogDTO(errorDTO);

          producer(fileDTO);
        return fileDTO;

    }


    public void producer(FileDTO fileDTO) {
        //Pass Data
        List<VehicleDto> vehicleDtos = fileDTO.getVehicleDtoList();
        for (VehicleDto dtonew : vehicleDtos) {
            kafkaTemplate.send(TOPIC, "1", dtonew);
        }

        //Fail Data
        List<VehicleErrorLogDTO> vehicleErrorLogDTO = fileDTO.getVehicleErrorLogDTO();
        for (VehicleErrorLogDTO fail : vehicleErrorLogDTO) {
            kafkaTemplatefail.send(TOPIC1, "1", fail);
        }
    }

}