package com.vehicle.schedular.service;

import com.vehicle.schedular.model.ByteArrayEntity;
import com.vehicle.schedular.model.FileDTO;
import com.vehicle.schedular.model.FileProcessingLog;
import com.vehicle.schedular.model.VehicleDto;
//import com.vehicle.schedular.repository.ByteArrayRepository;
import com.vehicle.schedular.repository.ByteArrayRepository;
import com.vehicle.schedular.repository.FileProcessingRepository;
import com.vehicle.schedular.util.ExcelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ByteArrayService {

	@Autowired
	private FileProcessingRepository fileProcessingRepository;
	@Autowired
	private ByteArrayRepository byteArrayRepository;
	@Autowired
	private ExcelUtility excelUtility;

	public List<FileDTO> getAllFile(){
		List<ByteArrayEntity> byteArrayEntityList=byteArrayRepository.findByStatusNotIn("Completed");
		List<FileDTO> fileDTOList=new ArrayList<>();
		for(ByteArrayEntity byteArray:byteArrayEntityList){
			FileDTO fileDTO=new FileDTO();
			fileDTO = excelUtility.processExcel(byteArray.getByteArrayData(),byteArray.getId(),byteArray.getFileName(),byteArray.getCreatedUser());
			fileDTO.setEndTime(LocalDateTime.now());
			fileDTOList.add(fileDTO);
		}
		return fileDTOList;
	}

	public String saveStatus(String fileId,String status){
		Optional<ByteArrayEntity> byteArrayEntity=byteArrayRepository.findById(fileId);

		if(byteArrayEntity.isPresent()){
			ByteArrayEntity byteArray=byteArrayEntity.get();
			byteArray.setStatus(status);
			byteArrayRepository.save(byteArray);
		}

		return null;
	}

	public FileProcessingLog getFileDetails(String fileId){
		Optional<FileProcessingLog> fileProcessingLog=fileProcessingRepository.findById(fileId);
		FileProcessingLog file=new FileProcessingLog();
		if (fileProcessingLog.isPresent()){
			file=fileProcessingLog.get();
		}
		return file;

	}
}
