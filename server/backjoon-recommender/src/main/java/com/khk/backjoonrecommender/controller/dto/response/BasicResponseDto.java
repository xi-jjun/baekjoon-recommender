package com.khk.backjoonrecommender.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BasicResponseDto<T> {

    private int code;
    private String message;

    private T data;
}
