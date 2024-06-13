package com.vehicle.main.service.impl;

import com.vehicle.main.dto.VehicleErrorLogDTO;
import com.vehicle.main.entity.VehicleErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.main.repository.VehicleErrorLogRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;

@Service
public class FailRecordConsumer {
	
	@Autowired
	VehicleErrorLogRepository velr;
	
	
	@KafkaListener(topics = "vehicle_reg_fail", groupId = "my_group")
	public void listenTopic(String  record) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
//		mapper.findAndRegisterModules();

		VehicleErrorLogDTO vehicleErrorLogDto = mapper.readValue(record, VehicleErrorLogDTO.class);

		VehicleErrorLog vehicle = mapper.convertValue(vehicleErrorLogDto, VehicleErrorLog.class);
		try {
			velr.save(vehicle);
		}catch (Exception e ){
			System.out.println(e.getMessage());

		}

//		vehicleDto = mapper.readValue(recievePassrecord, PassVehicleDTO.class);

		System.out.println("Failed  messages recieved in consumer is " +record );
//		VehicleErrorLogDto veld = mapper.readValue(record, VehicleErrorLogDto.class);
		
//		VehicleErrorLog vel = mapper.convertValue(veld, VehicleErrorLog.class);
//		System.out.println("after conversion pass "+vel);

//	velr.save(vel);


//		System.out.println("the message recieved in consumerFailure is" + veld.toString());
	}
}
