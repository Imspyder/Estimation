package com.vehicle.schedular.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "Vehicle")
@Valid
public class Vehicle {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 10,max = 17,message = "Invalid Vin")
    @Column(unique = true, nullable = false)
    private String vin;
    @Size(min = 8,max = 10,message = "Invalid Registration Number")
    @Column(unique = true, nullable = false)
    private String reg_Number;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(unique = false, nullable = false)
    private Date reg_Date;

    @Column(unique = false, nullable = false)
    private String brand;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(unique = false, nullable = false)
    private Date mfg_Date;
    @Column(unique = false, nullable = false)
    private Long price;
    private String currency;
    private Boolean is_Insured = false;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date warranty_Date;
    private String warranty_Coverage;
    private String selling_Dealer;

    @Column(name = "`created_user`")
    @CreatedBy
    private String created_user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "`created_date`")
    @CreationTimestamp
    private LocalDateTime created_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getReg_Number() {
        return reg_Number;
    }

    public void setReg_Number(String reg_Number) {
        this.reg_Number = reg_Number;
    }

    public Date getReg_Date() {
        return reg_Date;
    }

    public void setReg_Date(Date reg_Date) {
        this.reg_Date = reg_Date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getMfg_Date() {
        return mfg_Date;
    }

    public void setMfg_Date(Date mfg_Date) {
        this.mfg_Date = mfg_Date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIs_Insured() {
        return is_Insured;
    }

    public void setIs_Insured(Boolean is_Insured) {
        this.is_Insured = is_Insured;
    }

    public Date getWarranty_Date() {
        return warranty_Date;
    }

    public void setWarranty_Date(Date warranty_Date) {
        this.warranty_Date = warranty_Date;
    }

    public String getWarranty_Coverage() {
        return warranty_Coverage;
    }

    public void setWarranty_Coverage(String warranty_Coverage) {
        this.warranty_Coverage = warranty_Coverage;
    }

    public String getSelling_Dealer() {
        return selling_Dealer;
    }

    public void setSelling_Dealer(String selling_Dealer) {
        this.selling_Dealer = selling_Dealer;
    }

    public String getCreated_user() {
        return created_user;
    }

    public void setCreated_user(String created_user) {
        this.created_user = created_user;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }
}
