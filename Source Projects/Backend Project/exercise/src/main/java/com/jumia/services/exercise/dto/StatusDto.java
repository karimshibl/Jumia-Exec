package com.jumia.services.exercise.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class StatusDto implements Serializable {


    private Integer code;
    private String englishMessage;
    private String arabicMessage;

    public StatusDto() {
        this.code = 200;
        this.englishMessage = "success";
    }

    public StatusDto(Integer code, String englishMessage, String arabicMessage) {
        this.code = code;
        this.englishMessage = englishMessage;
        this.arabicMessage = arabicMessage;
    }

}
