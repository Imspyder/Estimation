package com.vehicle.schedular.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class CheckUtilities {

    public Boolean dateDifferenceTodaysDateToInputDate(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        long days = ChronoUnit.DAYS.between(LocalDate.parse(LocalDate.now().format(formatter)),
                LocalDate.parse(inputDate));
        if (days >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean dateDifferenceManufacturingDateToRegistrationDate(String manufacturingDate,
                                                                     String registrationDate) {
        long days = ChronoUnit.DAYS.between(LocalDate.parse(manufacturingDate), LocalDate.parse(registrationDate));
        if (days >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean dateDifferenceWarrentyDate(String warrentyDate, String registrationDate) {
        long days = ChronoUnit.DAYS.between(LocalDate.parse(registrationDate), LocalDate.parse(warrentyDate));
        if (days > 0) {
            return true;
        } else {
            return false;
        }
    }
}

