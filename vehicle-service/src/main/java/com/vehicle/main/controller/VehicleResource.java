package com.vehicle.main.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.vehicle.main.service.VehicleService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.main.dto.VehicleErrorLogDTO;
import com.vehicle.main.entity.Vehicle;
import com.vehicle.main.exception.VehicleServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.vehicle.main.dto.VehicleDto;
import com.vehicle.main.entity.VehicleResponse;
import com.vehicle.main.repository.VehicleErrorLogRepository;
import com.vehicle.main.repository.VehicleRepository;
//import com.vehicle.main.service.VehiclServiceHandler;
import com.vehicle.main.service.impl.VehiclServiceHandler;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/v1/api/")
@Slf4j

// THIS CLASS ABLE TO PERFORM OPERATION FOR VEHICLE RESOURCE
public class VehicleResource {

	
	@Autowired
	VehicleRepository repo1;
	
	@Autowired
	VehicleErrorLogRepository repo2;
	
	private static final String TOPIC = "vehicle_reg_pass";
	private static final String TOPIC1 = "vehicle_reg_fail";

	@Autowired
	private VehiclServiceHandler vehicleService;

	@Autowired
	private KafkaTemplate<String, VehicleDto> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, VehicleErrorLogDTO> kafkaTemplatefail;

//THIS METHOD WILL ABLE TO SEND DATA FROM SERVICE CLASS
	@PostMapping(path = "vehicle", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<VehicleResponse> registerVehicle(@RequestBody @Valid VehicleDto vehicalDto )
			throws VehicleServiceException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();

		List<VehicleDto> listofPassRecord = new ArrayList<>();
		listofPassRecord.add(vehicalDto);
//		//repo1.save(mapper.convertValue(vehicalDto, Vehicle.class));
//		System.out.println("********************** Save");
//
//		VehicleDto vehicleDto1 = new VehicleDto();
//		vehicleDto1.setVin("Shital");
//		vehicleDto1.setBrand("Kia Seltos");
//		vehicleDto1.setCreated_date("");
//		vehicleDto1.setCreated_user("RAM");
//		vehicleDto1.setCurrency("INR");
//		vehicleDto1.setMfg_Date("2001-01-14");
//		vehicleDto1.setPrice(2300L);
//		vehicleDto1.setReg_Date("2001-01-08");
//		vehicleDto1.setReg_Number("MH14SD740");
//		vehicleDto1.setSelling_Dealer("MARUTI");
//		vehicleDto1.setWarranty_Coverage("2 year");
//		vehicleDto1.setWarranty_Date("");
//
//		//repo1.save(mapper.convertValue(vehicleDto1, Vehicle.class));
//		System.out.println("********************** Save1111111111111");
//
//		listofPassRecord.add(vehicleDto1);
//
////		List<VehicleErrorLogDto> failureDtoList = new ArrayList<>();
////		VehicleErrorLogDto failureDto = new VehicleErrorLogDto();
////		failureDto.setVin(7654345L);
////		failureDto.setCreated_by("RAM1");
////		failureDto.setCreated_time(new Date());
////		failureDto.setError_msg("Not processed Data");
////		failureDto.setException_msg("Not properly inserted");
////		failureDto.setFile_id(243324L);
//		//repo2.save(mapper.convertValue(failureDto, VehicleErrorLog.class));
//
//		System.out.println("******************************** Save 3 ");
////		VehicleErrorLogDto failureDto1 = new VehicleErrorLogDto();
////		failureDto1.setVin(7656665L);
////		failureDto1.setCreated_by("Praful");
////		failureDto1.setCreated_time(new Date());
////		failureDto1.setError_msg("Not processed Data again");
////		failureDto1.setException_msg("Not properly inserted again");
////		failureDto1.setFile_id(9898L);
//	//	repo2.save(mapper.convertValue(failureDto1, VehicleErrorLog.class));
//		System.out.println("******************************** Save 4 ");

		log.info(" VehicleDto {}" + vehicalDto);
		Vehicle vehicle = vehicleService.convertDtoToEntity(vehicalDto);
		Map<String, Object> saveVehicle = vehicleService.saveVehicle(vehicle);
		VehicleResponse response = new VehicleResponse();
		response.setSuccess(true);
		response.setResponse(saveVehicle);

//		failureDtoList.add(failureDto);
//		failureDtoList.add(failureDto1);

//*******************************************
//		if (listofPassRecord.size() > 0) {
//			for (VehicleDto dtonew : listofPassRecord) {
//				//System.out.println("befor send to the pass topic{}" + dtonew);
//				kafkaTemplate.send(TOPIC, "1", dtonew);
//				//System.out.println("after send to the pass topic{}" + dtonew);
//			}
//		}

//		if (failureDtoList.size() > 0) {
//			for (VehicleErrorLogDto dtonew : failureDtoList) {
//			//	System.out.println("befor send to the fail topic{}" + dtonew);
//				kafkaTemplatefail.send(TOPIC1, "1", dtonew);
//				//System.out.println("after send to the fail topic{}" + dtonew);
//			}
//		}
//**********************************************************
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

// THIS METHOD WILL ABLE TO GET DATA FROM SERVICE CLASS
	@GetMapping(path = "getAllVehicles", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<VehicleResponse> getAllVehicles() {
		log.info("The getAllVehicles() Enpoint start");
		List<VehicleDto> allVehicles = vehicleService.getAllVehicles();
		VehicleResponse response = new VehicleResponse();
		response.setSuccess(true);
		response.setResponse(allVehicles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

//THIS METHOD WILL ABLE TO GET DATA FROM SERVICE CLASS
	@GetMapping(path = "searchVehicle", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<VehicleResponse> searchVehicleByVinOrRegNum(@RequestParam("vin") String vin,
			@RequestParam("regNumber") String reg_Number) {
		log.info("The searchVehicleByVinOrRegNum(-) Enpoint start");
		List<VehicleDto> allVehicles = vehicleService.searchVehicleByVinOrRegNum(vin, reg_Number);
		VehicleResponse response = new VehicleResponse();
		response.setSuccess(true);
		response.setResponse(allVehicles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}



}