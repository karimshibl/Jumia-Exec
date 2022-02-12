package com.jumia.services.exercise.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CustomerDto implements Serializable {

    private String name;
    private String phone;
    private String country;
    private Boolean validState;
    private String countryCode;

}
