package com.vehicle.main;


import com.vehicle.main.dto.VehicleDto;
import com.vehicle.main.entity.Vehicle;
import com.vehicle.main.exception.VehicleServiceException;
import com.vehicle.main.pojo.SearchRequest;
import com.vehicle.main.repository.VehicleRepository;
import com.vehicle.main.service.impl.VehiclServiceHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class VehiclServiceImplTest {
	
	@InjectMocks
    VehiclServiceHandler vehiclServiceImpl;
	
    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    Logger LOGGER;
    
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVehicle() throws VehicleServiceException {
        Map<String, Object> result1= new HashMap<>();
        Map<String, Object> result = vehiclServiceImpl.saveVehicle(new Vehicle(null, "vin", null, new GregorianCalendar(2023, Calendar.JUNE, 22, 11, 1).getTime(), "brand", new GregorianCalendar(2023, Calendar.JUNE, 22, 11, 1).getTime(), Long.valueOf(1), "currency", true, new GregorianCalendar(2023, Calendar.JUNE, 22, 11, 1).getTime(), "warranty_Coverage", "selling_Dealer", "created_user", LocalDateTime.of(2023, Month.JUNE, 22, 11, 1, 37)));
        Assertions.assertEquals(result1, result);
    }

    @Test
    void testGetAllVehicles() throws VehicleServiceException {
        List<VehicleDto> result1=new ArrayList<>();
        List<VehicleDto> result = vehiclServiceImpl.getAllVehicles();
        Assertions.assertEquals(result1, result);
    }

    @Test
    void testSearchVehicleByVinOrRegNum() throws VehicleServiceException {
        SearchRequest searchRequest1 = new SearchRequest();
        searchRequest1.setReg_Number("WB24B2620");
        searchRequest1.setVin("");
        SearchRequest searchRequest2 = new SearchRequest();
        searchRequest2.setReg_Number("WB24B2620");
        searchRequest2.setVin("422");

        SearchRequest searchRequest3 = new SearchRequest();
        searchRequest3.setReg_Number("");
        searchRequest3.setVin("422");
        long price=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Vehicle> findByVinAndRegNum=new ArrayList<>();
        List<Vehicle> findByVin=new ArrayList<>();
        List<Vehicle> findByRegNum=new ArrayList<>();
        List<Vehicle> findByVinAndRegNum1=new ArrayList<>();
        findByVinAndRegNum.add(new Vehicle(null,"MH32AR123","1234444",new Date(),"grand",new Date(),1111111L,"INR",true,new Date(),"0 to 1 Years","Ankit Belge",null,null));
        findByVinAndRegNum1.add(new Vehicle(null,"MH32AR123","1234444",new Date(),"grand",new Date(),1111111L,"INR",true,new Date(),"0 to 1 Years","Ankit Belge",null,null));
        findByVin.add(new Vehicle(null,"MH32AR123","1234444",new Date(),"grand",new Date(),1111111L,"INR",true,new Date(),"0 to 1 Years","Ankit Belge",null,null));
        findByRegNum.add(new Vehicle(null,"MH32AR123","1234444",new Date(),"grand",new Date(),1111111L,"INR",true,new Date(),"0 to 1 Years","Ankit Belge",null,null));
        when(vehicleRepository.findByVinAndRegNum("MH32AR123","1234444")).thenReturn(findByVinAndRegNum);
        when(vehicleRepository.findByVin("MH32AR123")).thenReturn(findByVin);
        when(vehicleRepository.findByVin("MH32AR123")).thenReturn(findByVin);
        when(vehicleRepository.findByRegNum("1234444")).thenReturn(findByRegNum);
        when(vehicleRepository.findByVinAndRegNum("MH32AR123", "1234444")).thenReturn(findByVinAndRegNum1);

        List<VehicleDto> result = vehiclServiceImpl.searchVehicleByVinOrRegNum("MH32AR123", "1234444");
        List<VehicleDto> result2 = vehiclServiceImpl.searchVehicleByVinOrRegNum("MH32AR123","");
        List<VehicleDto> result1 = vehiclServiceImpl.searchVehicleByVinOrRegNum("","1234444");
        Assertions.assertEquals(findByRegNum.size(), result.size());
        Assertions.assertEquals(findByVinAndRegNum.size(), result1.size());
        Assertions.assertEquals(findByVin.size(), result2.size());
    }
}

