package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.impl.AttributesFilledRule;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootAttributesFilledRuleConfig {

    @Bean("attributesFilledRule")
    public AttributesFilledRule attributesFilledRule(
            @Qualifier("attributesFilledScore") final BooleanSimpleScore attributesFilledSimpleScore) {
        return new AttributesFilledRule(attributesFilledSimpleScore);
    }

    @Bean("attributesFilledScore")
    public BooleanSimpleScore attributesFilledScore(
            @Value("${rule.attributesFilled.score.max:40}") Integer attributesFilledMaxScore,
            @Value("${rule.attributesFilled.score.min:0}") Integer attributesFilledMinScore) {
        return new BooleanSimpleScore(attributesFilledMaxScore, attributesFilledMinScore);
    }

}
