package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.impl.HashDescriptionRule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootHasDescriptionRuleConfig {

    @Bean("hasDescriptionRule")
    public Rule hasDescriptionRule(@Qualifier("hasDescriptionScore") BooleanScore booleanScore){
        return new HashDescriptionRule(booleanScore);
    }

    @Bean("hasDescriptionScore")
    public BooleanScore hasDescriptionScore(@Value("${rule.hasDescription.score.max:5}") Integer hasDescriptionScore,
                          @Value("${rule.hasDescription.score.min:0}") Integer emptyDescriptionScore) {
        return new BooleanSimpleScore(hasDescriptionScore, emptyDescriptionScore);
    }


}
