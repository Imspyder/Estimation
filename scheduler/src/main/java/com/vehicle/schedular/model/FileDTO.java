package com.vehicle.schedular.model;

import java.time.LocalDateTime;
import java.util.List;

public class FileDTO {
    private String fileId;
    private String fileName;
    private List<VehicleDto> vehicleDtoList;

    private int successCount;
    private int failureCount;
    private LocalDateTime endTime;
    private List<VehicleErrorLogDTO> vehicleErrorLogDTO;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<VehicleDto> getVehicleDtoList() {
        return vehicleDtoList;
    }

    public void setVehicleDtoList(List<VehicleDto> vehicleDtoList) {
        this.vehicleDtoList = vehicleDtoList;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<VehicleErrorLogDTO> getVehicleErrorLogDTO() {
        return vehicleErrorLogDTO;
    }

    public void setVehicleErrorLogDTO(List<VehicleErrorLogDTO> vehicleErrorLogDTO) {
        this.vehicleErrorLogDTO = vehicleErrorLogDTO;
    }
}
