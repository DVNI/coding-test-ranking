package com.idealista.application.rule.impl;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.Picture;
import com.idealista.domain.data.PictureQualityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PictureQualityRule extends Rule {

    private ScoringByAdTypeFactory pictureQualityScoring;

    @Override
    public Integer apply(Ad ad) {
        Integer score = ad.getPictures().stream().map(Picture::getQuality)
                .map(quality -> PictureQualityEnum.fromValue(quality))
                .map(pictureQuality -> pictureQualityScoring.getScoringByType(pictureQuality))
                .map(bc -> bc.score(true))
                .reduce(0, Integer::sum);

        return score;
    }

}
