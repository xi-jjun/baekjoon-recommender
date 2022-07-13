package com.khk.backjoonrecommender.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RivalSearchRequestDto {

    @NotBlank
    private String username;
}
