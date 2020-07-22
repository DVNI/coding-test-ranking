package com.idealista.application.rule.scoring.impl;

import com.idealista.application.rule.scoring.BooleanScore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public class BooleanSimpleScore implements BooleanScore {

    private final static Integer firstCondition = 0 ;
    protected final Integer scoreMax;
    protected final Integer scoreMin;


    @Override
    public Integer score(Boolean... evaluations) {
        return evaluations[firstCondition] ? scoreMax : scoreMin ;
    }
}
