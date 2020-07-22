package com.idealista.application.rule.condition.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GreaterOrEqualCondition implements BooleanCondition {

    private final Integer lowerLimit;

    @Override
    public boolean evaluate(Integer value) {
        return lowerLimit >= value;
    }
}
