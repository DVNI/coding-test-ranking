package com.idealista.application.rule.impl;

import com.idealista.application.formatter.TextSplitter;
import com.idealista.application.rule.Rule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public abstract class DescriptionSizeByTypology extends Rule {

    protected final ScoringByAdTypeFactory scoringByAdTypeFactory;

    public Integer getDescriptionSize(Ad ad) {
        return TextSplitter.split(ad.getDescription()).size();
    }

    public BooleanScore getScore(Ad ad) {
        return scoringByAdTypeFactory.getScoringByType(ad.getTypology());
    }
}

