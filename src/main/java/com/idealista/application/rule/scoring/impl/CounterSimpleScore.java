package com.idealista.application.rule.scoring.impl;

import com.idealista.application.rule.scoring.CounterScore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CounterSimpleScore implements CounterScore {

    private final Integer score;

    @Override
    public Integer score(Integer evaluations) {
        return score * evaluations;
    }
}
