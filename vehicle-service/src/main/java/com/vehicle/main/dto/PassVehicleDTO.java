package com.vehicle.main.dto;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Validated
public class PassVehicleDTO {

    @Valid
    @NonNull
    private String vin;
    @Valid
    @NonNull
    private String reg_Number;
    @Valid
    @NonNull
    private String reg_Date;
    @Valid
    @NonNull
    private String brand;
    @Valid
    @NonNull
    private String mfg_Date;
    @Valid
    @NonNull
    private String price;
    private String currency;
    private String is_Insured;
    private String warranty_Date;
    private String warranty_Coverage;
    private String selling_Dealer;
    private String created_user;
    private String created_date;
    private String fileId;

}
