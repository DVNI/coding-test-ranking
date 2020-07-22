package com.idealista.application.rule.scoring.impl;

import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.EnumType;
import com.idealista.domain.data.PictureQualityEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScoringByAdPictureQualityFactory extends ScoringByAdTypeFactory {

    private final BooleanScore highResolutionSimpleScore;
    private final BooleanScore otherResolutionSimpleScore;

    @Override
    public BooleanScore getScoringByType(EnumType pictureQuality) {
        return pictureQuality.equals(PictureQualityEnum.HIGH_RESOLUTION) ? highResolutionSimpleScore : otherResolutionSimpleScore;
    }

}
