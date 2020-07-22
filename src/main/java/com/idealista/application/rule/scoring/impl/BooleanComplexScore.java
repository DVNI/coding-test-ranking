package com.idealista.application.rule.scoring.impl;

import com.idealista.application.rule.scoring.Score;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import lombok.Getter;

@Getter
public class BooleanComplexScore extends BooleanSimpleScore implements Score {

    private final static Integer secondCondition = 1 ;
    private Integer scoreMiddle;

    public BooleanComplexScore(Integer scoreMax, Integer scoreMin, Integer scoreMiddle) {
        super(scoreMax, scoreMin);
        this.scoreMiddle = scoreMiddle;
    }

    @Override
    public Integer score(Boolean... evaluations) {
        Integer score = super.score(evaluations);
        return score.equals(scoreMax) ?
                    score : evaluations[secondCondition] ?
                        scoreMiddle : scoreMin;
    }
}
