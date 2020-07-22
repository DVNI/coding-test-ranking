package com.idealista.application.rule.condition.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CloseIntervalCondition implements BooleanCondition {

    protected final Integer startLimit;
    protected final Integer endLimit;

    @Override
    public boolean evaluate(Integer value) {
        return startLimit <= value || value <= endLimit ;
    }
}
