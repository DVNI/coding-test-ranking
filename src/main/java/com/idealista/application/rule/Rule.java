package com.idealista.application.rule;

import com.idealista.domain.data.Ad;

public abstract class Rule {

    public abstract Integer apply(Ad ad);

    public boolean mustApply(Ad ad) {
        return true;
    }

}
