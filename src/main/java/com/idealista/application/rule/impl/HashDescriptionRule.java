package com.idealista.application.rule.impl;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;


@AllArgsConstructor
public class HashDescriptionRule extends Rule {

    private final BooleanScore simpleScore;

    @Override
    public Integer apply(Ad ad) {
        return simpleScore.score(StringUtils.hasText(ad.getDescription()));
    }
}
