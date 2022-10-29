package com.khk.backjoonrecommender.entity;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Setting {

    @Id
    @Column(name = "setting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String levels;
    private String tags;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_option")
    private Option option;

    public Set<Integer> getUserRecommendationLevels() {
        String[] levelArr = levels.split(",");
        return Arrays.stream(levelArr)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public Set<String> getUserRecommendationTags() {
        String[] tagArr = tags.split(",");
        return new HashSet<>(Arrays.asList(tagArr));
    }

    public void updateSettingInfo(SettingRequestDto settingRequestDTO) {
        this.levels = settingRequestDTO.getLevels();
        this.tags = settingRequestDTO.getTags();
    }
}
