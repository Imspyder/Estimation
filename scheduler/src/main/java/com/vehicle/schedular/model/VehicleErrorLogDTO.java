package com.vehicle.schedular.model;

import java.util.List;
import java.util.Map;

public class VehicleErrorLogDTO {
    private String id;
    private String fileId;
    private String fileName;
    private String errorMsg;
    private String exceptionMsg;
    private String createdTime;
    private String createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "VehicleErrorLogDTO{" +
                "id=" + id +
                ", fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", errorMsg=" + errorMsg +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
