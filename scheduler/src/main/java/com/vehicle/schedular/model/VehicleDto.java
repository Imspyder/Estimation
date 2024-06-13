package com.vehicle.schedular.model;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.ValidationException;

@Valid
@Validated
public class VehicleDto {

    @Valid
    @NonNull
    @NotBlank
    @Size(min = 10, max = 17)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "VIN Should be alphanumeric and Uppercase only")
    private String vin;
    @Valid
    @NonNull
    @NotBlank
    @Size(min = 8, max = 10)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Regestration number Should be alphanumeric and Uppercase only")
    private String reg_Number;
    @Valid
    @NonNull
    @NotBlank
    private String reg_Date;
    @Valid
    @NonNull
    @NotBlank
    private String brand;
    @Valid
    @NonNull
    @NotBlank
    private String mfg_Date;
    @Valid
    @NotBlank
    @NonNull
//    @Digits(integer = 10, fraction = 0,message = "price should be 0-10 digits")
    @Pattern(regexp = "^[0-9]{0,9}[.]{0,1}[0-9]{0,2}$", message = "price should be 0-10 digits")
    private String price;
    @NotBlank
    @Pattern(regexp = "^[₹INR]+$", message = "Currency must be INR only")
    private String currency;
    private Boolean is_Insured;
    private String warranty_Date;
    private String warranty_Coverage;
    private String selling_Dealer;
    private String created_user;
    private String created_date;

    private String fileId;

//    private int errorCount;

    @NonNull
    public String getVin() {
        return vin;
    }

    public void setVin(@NonNull String vin) {
        this.vin = vin;
    }

    @NonNull
    public String getReg_Number() {
        return reg_Number;
    }

    public void setReg_Number(@NonNull String reg_Number) {
        this.reg_Number = reg_Number;
    }

    @NonNull
    public String getReg_Date() {
        return reg_Date;
    }

    public void setReg_Date(@NonNull String reg_Date) {
        this.reg_Date = reg_Date;
    }

    @NonNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(@NonNull String brand) {
        this.brand = brand;
    }

    @NonNull
    public String getMfg_Date() {
        return mfg_Date;
    }

    public void setMfg_Date(@NonNull String mfg_Date) {
        this.mfg_Date = mfg_Date;
    }

    @NonNull
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getWarranty_Date() {
        return warranty_Date;
    }

    public void setWarranty_Date(String warranty_Date) {
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getFileId() {return fileId;    }

    public void setFileId(String fileId) {this.fileId = fileId;    }

  /*  public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }*/

    @Override
    public String toString() {
        return "VehicleDto{" +
                "vin='" + vin + '\'' +
                ", reg_Number='" + reg_Number + '\'' +
                ", reg_Date='" + reg_Date + '\'' +
                ", brand='" + brand + '\'' +
                ", mfg_Date='" + mfg_Date + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", is_Insured=" + is_Insured +
                ", warranty_Date='" + warranty_Date + '\'' +
                ", warranty_Coverage='" + warranty_Coverage + '\'' +
                ", selling_Dealer='" + selling_Dealer + '\'' +
                ", created_user='" + created_user + '\'' +
                ", created_date='" + created_date + '\'' +
                '}';
    }
}