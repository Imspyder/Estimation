package com.vehicle.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "byteArrays")
public class ByteArrayEntity {

    @Id
    private String id;
    private String fileName;
    private byte[] byteArrayData;


	private String status;

	private String createdUser;

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getByteArrayData() {
		return byteArrayData;
	}
	public void setByteArrayData(byte[] byteArrayData) {
		this.byteArrayData = byteArrayData;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
