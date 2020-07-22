package com.idealista.application.rule.condition.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OpenIntervalCondition implements BooleanCondition {

    protected final Integer endLimit;
    protected final Integer startLimit;

    @Override
    public boolean evaluate(Integer value) {
        return startLimit < value || value < endLimit ;
    }
}