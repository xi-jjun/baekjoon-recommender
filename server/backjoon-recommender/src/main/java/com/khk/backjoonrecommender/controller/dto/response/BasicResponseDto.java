package com.khk.backjoonrecommender.controller.dto.response;

import lombok.Data;

@Data
public class BasicResponseDto<T> {

    private int code;
    private String message;

    private T data;
}
