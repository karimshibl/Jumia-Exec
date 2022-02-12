package com.jumia.services.exercise.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ResponseDto<T> implements Serializable {

    private StatusDto status;
    private int totalDataCount;
    private boolean hasMoreData = false;
    private T data;

    public ResponseDto() {
        this.status = new StatusDto();
    }

}
