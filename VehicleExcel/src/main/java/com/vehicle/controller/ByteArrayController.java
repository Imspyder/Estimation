package com.vehicle.controller;

import com.vehicle.model.ByteArrayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.service.ByteArrayService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ByteArrayController {

    @Autowired
    private ByteArrayService byteArrayService;

    @PostMapping("/upload")
    public ResponseEntity<byte[]> uploadByteArray(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] byteArrayData = file.getBytes();
            byteArrayService.saveByteArray(fileName, byteArrayData);

            //return ResponseEntity.ok("Byte array uploaded successfully.");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=my_excel_file.xlsx")
                    .body(byteArrayData);
        } catch (Exception e) {
            e.printStackTrace();
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload byte array.");
            return null;
        }
    }

    @GetMapping("getAllFiles")
    public List<ByteArrayEntity> getFile(){
        return byteArrayService.getFileDetails();
    }

}