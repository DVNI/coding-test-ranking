package com.idealista.application.rule.impl;

import com.idealista.application.rule.ScoringRule;
import com.idealista.application.rule.condition.BooleanCondition;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RangeScoringRule extends ScoringRule {

    private final BooleanCondition greaterCondition;
    private final BooleanCondition lowerCondition;
    private final BooleanSimpleScore simpleScore;

    @Override
    public Ad apply(Ad ad) {
        Boolean isGreater = greaterCondition.evaluate(ad.getScore());
        Boolean isLower = lowerCondition.evaluate(ad.getScore());
        Integer score = simpleScore.score(isGreater,isLower);
        if(isGreater || isLower)
            ad.setScore(score);
        return ad;
    }
}
