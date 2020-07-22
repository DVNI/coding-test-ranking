package com.idealista.application.rule.impl;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.condition.MatchCounterCondition;
import com.idealista.application.rule.scoring.CounterScore;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DescriptionMatchTermsRule extends Rule {

    private final CounterScore matchWordSimpleScore;

    private final MatchCounterCondition matchCondition;

    @Override
    public Integer apply(Ad ad) {
        Integer numberOfMatches = matchCondition.evaluate(ad.getDescription());
        return matchWordSimpleScore.score(numberOfMatches);
    }

}
