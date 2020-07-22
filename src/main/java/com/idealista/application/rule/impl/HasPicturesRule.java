package com.idealista.application.rule.impl;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HasPicturesRule extends Rule {

    private final BooleanScore hasPicturesSimpleScore;

    @Override
    public Integer apply(Ad ad) {
        return hasPicturesSimpleScore.score(ad.getPictures().isEmpty());
    }

}
