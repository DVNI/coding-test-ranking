package com.idealista.application.rule.condition.impl;

import com.idealista.application.rule.condition.BooleanCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GreaterCondition implements BooleanCondition {

    protected final Integer upperLimit;

    @Override
    public boolean evaluate(Integer value) {
        return value > upperLimit;
    }
}
