package com.vehicle.main.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "vehicle_error_log")
public class VehicleErrorLog {

	@Id
	@Column(columnDefinition = "varchar(40)")
	private String id;
	@Column(columnDefinition = "varchar(40)")
	private String fileId;
	@Column(columnDefinition = "varchar(40)")
	private String fileName;

	@Column(columnDefinition = "varchar(255)")
	private String errorMsg;
	@Column(columnDefinition = "varchar(40)")
	private String exceptionMsg;
	@Column(columnDefinition = "varchar(40)")
	private String createdTime;
	@Column(columnDefinition = "varchar(40)")
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

	@Override
	public String toString() {
		return "VehicleErrorLog{" +
				"id='" + id + '\'' +
				", fileId='" + fileId + '\'' +
				", fileName='" + fileName + '\'' +
				", errorMsg='" + errorMsg + '\'' +
				", exceptionMsg='" + exceptionMsg + '\'' +
				", createdTime='" + createdTime + '\'' +
				", createdBy='" + createdBy + '\'' +
				'}';
	}
}
