package com.vehicle.schedular.controller;

import com.vehicle.schedular.model.FileDTO;
import com.vehicle.schedular.model.FileProcessingLog;
import com.vehicle.schedular.model.VehicleDto;
import com.vehicle.schedular.model.VehicleResponse;
import com.vehicle.schedular.repository.FileProcessingRepository;
import com.vehicle.schedular.service.ByteArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
//import org.springframework.web.reactive.function.BodyInserter;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/schedular/")
@Validated
@CrossOrigin("*")
public class SchedularController {

    @Autowired
    private FileProcessingRepository fileProcessingRepository;
    @Autowired
    private ByteArrayService byteArrayService;

    @Scheduled(cron = "0 * * * * *")
    public void fileProcess(){
        System.out.println("starting schedular");
        String status=null;

        FileProcessingLog fileProcessingLog = new FileProcessingLog();
        fileProcessingLog.setStartTime(LocalDateTime.now());

        List<FileDTO> fileDTO = byteArrayService.getAllFile();
        for(FileDTO fileDTO1:fileDTO) {
            if (fileDTO1 != null) {
                fileProcessingLog.setFileId(fileDTO1.getFileId());
                fileProcessingLog.setFileName(fileDTO1.getFileName());
                fileProcessingLog.setSuccessCount(fileDTO1.getSuccessCount());
                fileProcessingLog.setFailureCount(fileDTO1.getFailureCount());
                fileProcessingLog.setTotalCount(fileDTO1.getSuccessCount() + fileDTO1.getFailureCount());
                fileProcessingLog.setEndTime(LocalDateTime.now());
                fileProcessingLog.setStatus("Completed");
                fileProcessingRepository.save(fileProcessingLog);
            }

            if (fileProcessingLog.getSuccessCount() == fileProcessingLog.getTotalCount()){
                status = "Completed";
            }else if(fileProcessingLog.getFailureCount() == fileProcessingLog.getTotalCount()){
                status = "Incomplete";
            }else {
                status = "Partially Processed";
            }

            byteArrayService.saveStatus(fileProcessingLog.getFileId(),status);

        }
    }

    @GetMapping("fileStatus/{fileId}")
    public FileProcessingLog getFileStatus(@PathVariable("fileId") String fileId){
        return byteArrayService.getFileDetails(fileId);
    }

    @GetMapping("msg")
    public String getMsg(){
        return "Hello";
    }

}
