package com.khk.backjoonrecommender.controller.dto.response;

import com.khk.backjoonrecommender.entity.Rival;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RivalResponseDto {

    private Long id;
    private String username;
}
