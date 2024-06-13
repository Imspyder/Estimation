package com.vehicle.main.service.impl;

import com.vehicle.main.dto.PassVehicleDTO;
import com.vehicle.main.dto.VehicleErrorLogDTO;
import com.vehicle.main.repository.VehicleErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.main.entity.Vehicle;
import com.vehicle.main.repository.VehicleRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassRecordConsumer {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleErrorLogRepository velr;

    public static final String DUPLICATE_ENTRY = "Duplicate Entry";
    int failCount = 0;


    @KafkaListener(topics = "vehicle_reg_pass", groupId = "my_group")
    public void listenTopic(String recievePassrecord) throws JsonMappingException, JsonProcessingException, ParseException {

        List<VehicleErrorLogDTO> errorDTO = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        //mapper.findAndRegisterModules();
        PassVehicleDTO vehicleDto = mapper.readValue(recievePassrecord, PassVehicleDTO.class);
//		PassVehicleDTO newDto = null;

        String reg_date = vehicleDto.getReg_Date();
        SimpleDateFormat reGDate = new SimpleDateFormat("YYYY-MM-dd");
        Date parseRegDate = reGDate.parse(reg_date);

        String warranty_date = vehicleDto.getWarranty_Date();
        SimpleDateFormat warrantyDate = new SimpleDateFormat("YYYY-MM-dd");
        Date parseWarratyDate = warrantyDate.parse(warranty_date);

        String mfg_date = vehicleDto.getMfg_Date();
        SimpleDateFormat mfDate = new SimpleDateFormat("YYYY-MM-dd");
        Date parseMfgDate = mfDate.parse(mfg_date);

        Vehicle vehicle = new Vehicle();

        vehicle.setVin(vehicleDto.getVin());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setCurrency(vehicleDto.getCurrency());
        vehicle.setReg_Number(vehicleDto.getReg_Number());
        vehicle.setReg_Date(parseRegDate);

        if (vehicleDto.getPrice() != null)
        vehicle.setPrice(Double.parseDouble(vehicleDto.getPrice()));

        vehicle.setMfg_Date(parseMfgDate);
        vehicle.setWarranty_Date(parseWarratyDate);
        vehicle.setWarranty_Coverage(vehicleDto.getWarranty_Coverage());
        vehicle.setIs_Insured(vehicleDto.getIs_Insured().equals("true") ? true : false);
        vehicle.setSelling_Dealer(vehicleDto.getSelling_Dealer());
        vehicle.setCreated_user(vehicleDto.getCreated_user());
        vehicle.setCreated_date(vehicle.getCreated_date());
        //Vehicle vehicle = mapper.convertValue(vehicleDto, Vehicle.class);
//		System.out.println("after conversion pass "+vehicle);


        try {
            vehicleRepository.save(vehicle);
        } catch (DataIntegrityViolationException de) {
            //Do nothing because we are skipping the duplicate records.
        }



        System.out.println("failCount = " + failCount);

//		System.out.println("the message recieved in consumer is" + vehicleDto);
        System.out.println("Passed  messages recieved in consumer is" + recievePassrecord);
    }


}
