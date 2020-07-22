package com.idealista.domain.data;


import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public enum PictureQualityEnum implements EnumType {

    HIGH_RESOLUTION(Arrays.asList("HD")),
    OTHER_RESOLUTION(Collections.emptyList());

    private final List<String> types;

    public static PictureQualityEnum fromValue(String type){
        for(PictureQualityEnum pictureQuality : values()) {
            if (pictureQuality.types.contains(type)) {
                return pictureQuality;
            }
        }
        return OTHER_RESOLUTION;
    }

}

