package com.idealista.application.rule.impl;

import com.idealista.application.rule.ScoringRule;
import com.idealista.application.rule.condition.impl.LowerCondition;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
public class IrrelevantScoringRule extends ScoringRule {

    private final LowerCondition lowerCondition;

    @Override
    public Ad apply(Ad ad) {
        boolean isLowerThanRelevant = lowerCondition.evaluate(ad.getScore());
        boolean alreadyIrrelevant = ad.getIrrelevantSince() != null;
        if (isLowerThanRelevant && !alreadyIrrelevant) {
            ad.setIrrelevantSince(Date.from(Instant.now()));
        } else {
            ad.setIrrelevantSince(null);
        }
        return ad;
    }
}
