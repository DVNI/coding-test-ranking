package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.ScoringRule;
import com.idealista.application.rule.condition.BooleanCondition;
import com.idealista.application.rule.condition.impl.CloseIntervalCondition;
import com.idealista.application.rule.condition.impl.GreaterCondition;
import com.idealista.application.rule.condition.impl.LowerCondition;
import com.idealista.application.rule.impl.RangeScoringRule;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootRangeScoringRule {

    @Bean("rangeScoringRule")
    public ScoringRule rangeScoringRule(
            @Qualifier("rangeScoringGreaterCondition") final GreaterCondition rangeScoringGreaterCondition,
            @Qualifier("rangeScoringLowerCondition") final LowerCondition irrelevantScoringLowerCondition,
            @Qualifier("rangeScoringScore") final BooleanSimpleScore rangeScoringSimpleScore) {
        return new RangeScoringRule(rangeScoringGreaterCondition, irrelevantScoringLowerCondition, rangeScoringSimpleScore);
    }

    @Bean("rangeScoringLowerCondition")
    public LowerCondition rangeScoringLowerCondition(
            @Value("${rule.rangeScoring.condition.lowerLimit:0}") Integer lowerLimit) {
        return new LowerCondition(lowerLimit);
    }

    @Bean("rangeScoringGreaterCondition")
    public GreaterCondition rangeScoringGreaterCondition(
            @Value("${rule.irrelevantScoring.condition.upperLimit:100}") Integer upperLimit) {
        return new GreaterCondition(upperLimit);
    }

    @Bean("rangeScoringScore")
    public BooleanSimpleScore rangeScoringScore(
            @Value("${rule.rangeScoring.rangeScore.max:100}") Integer rangeScoringMaxScore,
            @Value("${rule.rangeScoring.rangeScore.min:0}") Integer rangeScoringMinScore) {
        return new BooleanSimpleScore(rangeScoringMaxScore, rangeScoringMinScore);
    }
}
