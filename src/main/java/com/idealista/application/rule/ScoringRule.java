package com.idealista.application.rule;

import com.idealista.domain.data.Ad;

public abstract class ScoringRule {

    public abstract Ad apply(Ad ad);

}
