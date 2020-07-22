package com.idealista.application.rule.impl;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.Score;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class AttributesFilledRule extends Rule {

    private final BooleanScore attributesFilledSimpleScore;

    @Override
    public Integer apply(Ad ad) {
        List<Object> mandatoryAttributes = ad.getTypology().getMandatoryFilledAttributes(ad);
        boolean check = mandatoryAttributes.stream().noneMatch(Objects::isNull);
        return attributesFilledSimpleScore.score(check);
    }
}
