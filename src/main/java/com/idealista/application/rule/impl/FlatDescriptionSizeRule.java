package com.idealista.application.rule.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.TypologyEnum;

public class FlatDescriptionSizeRule extends DescriptionSizeByTypology {

    private final BooleanCondition closeIntervalCondition;
    private final BooleanCondition greaterOrEqualCondition;

    public FlatDescriptionSizeRule(ScoringByAdTypeFactory scoringByAdTypeFactory,
                                   BooleanCondition closeIntervalCondition,
                                   BooleanCondition greaterOrEqualCondition) {
        super(scoringByAdTypeFactory);
        this.closeIntervalCondition = closeIntervalCondition;
        this.greaterOrEqualCondition = greaterOrEqualCondition;
    }

    public Integer apply(Ad ad) {
        int descriptionSize = getDescriptionSize(ad);
        BooleanScore flatDescriptionSizeScore = getScore(ad);
        boolean isGreaterOrEqual = greaterOrEqualCondition.evaluate(descriptionSize);
        boolean isInRange = closeIntervalCondition.evaluate(descriptionSize);
        return flatDescriptionSizeScore.score(isGreaterOrEqual,isInRange);
    }

     public boolean mustApply(Ad ad) {
        return ad.getTypology().equals(TypologyEnum.FLAT);
    }
}

