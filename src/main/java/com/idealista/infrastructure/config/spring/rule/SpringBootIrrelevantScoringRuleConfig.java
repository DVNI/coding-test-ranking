package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.ScoringRule;
import com.idealista.application.rule.condition.impl.LowerCondition;
import com.idealista.application.rule.impl.IrrelevantScoringRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootIrrelevantScoringRuleConfig {

    @Bean("irrelevantScoringRule")
    public ScoringRule irrelevantScoringRule(
            @Qualifier("irrelevantScoringLowerCondition") final LowerCondition irrelevantScoringLowerCondition) {
        return new IrrelevantScoringRule(irrelevantScoringLowerCondition);
    }

    @Bean("irrelevantScoringLowerCondition")
    public LowerCondition irrelevantScoringLowerCondition(
            @Value("${rule.irrelevantScoring.condition.lowerLimit:40}") Integer lowerLimit) {
        return new LowerCondition(lowerLimit);
    }

}
