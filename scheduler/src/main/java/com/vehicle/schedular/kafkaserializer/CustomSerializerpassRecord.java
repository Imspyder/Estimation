package com.vehicle.schedular.kafkaserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.vehicle.schedular.model.VehicleDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializerpassRecord implements Serializer<VehicleDto>{

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> config, boolean isKey){

	}

	@Override
	public byte[] serialize(String topic, VehicleDto data){
		try {
			if (data == null){
				System.out.println("null recieved while serializing");
			}

			System.out.println("Start serializing");
			return objectMapper.writeValueAsBytes(data);

		} catch (Exception e){
			throw new SerializationException();
		}
	}

	@Override
	public void close() {

	}

}
