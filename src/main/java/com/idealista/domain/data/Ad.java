package com.idealista.domain.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private Integer id;
    private TypologyEnum typology;
    private String description;
    private List<Picture> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

}
