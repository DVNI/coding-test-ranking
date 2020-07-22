package com.idealista.application.rule.condition.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LowerCondition implements BooleanCondition {
    private final Integer lowerLimit;

    @Override
    public boolean evaluate(Integer value) {
        return value < lowerLimit;
    }
}
