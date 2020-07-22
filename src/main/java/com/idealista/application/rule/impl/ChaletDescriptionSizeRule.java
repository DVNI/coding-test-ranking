package com.idealista.application.rule.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import com.idealista.application.rule.condition.impl.GreaterCondition;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.TypologyEnum;

public class ChaletDescriptionSizeRule extends DescriptionSizeByTypology {

    private BooleanCondition greaterCondition;

    public ChaletDescriptionSizeRule(ScoringByAdTypeFactory scoringByAdTypeFactory, BooleanCondition greaterCondition) {
        super(scoringByAdTypeFactory);
        this.greaterCondition = greaterCondition;
    }

    public Integer apply(Ad ad) { ;
        int descriptionSize = getDescriptionSize(ad);
        boolean isGreater = greaterCondition.evaluate(descriptionSize);
        BooleanScore chaletDescriptionSizeSimpleScore = getScore(ad);
        return chaletDescriptionSizeSimpleScore.score(isGreater);
    }

    public boolean mustApply(Ad ad) {
        return ad.getTypology().equals(TypologyEnum.CHALET);
    }
}