package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.condition.BooleanCondition;
import com.idealista.application.rule.condition.impl.*;
import com.idealista.application.rule.impl.FlatDescriptionSizeRule;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.idealista")
public class SpringBootFlatDescriptionSizeRuleConfig {

    @Bean("flatDescriptionSizeRule")
    public FlatDescriptionSizeRule flatDescriptionSizeRule(
            @Qualifier("scoringByTypologyFacade") final ScoringByAdTypeFactory scoringByTypologyFacade,
            @Qualifier("flatDescriptionSizeIntervalCondition") final BooleanCondition closeIntervalCondition,
            @Qualifier("flatDescriptionSizeGreaterCondition") final BooleanCondition greaterOrEqualCondition) {
        return new FlatDescriptionSizeRule(scoringByTypologyFacade, closeIntervalCondition, greaterOrEqualCondition);
    }

    @Bean("flatDescriptionSizeIntervalCondition")
    public BooleanCondition flatDescriptionSizeIntervalCondition(
            @Value("${rule.flatDescriptionSize.condition.interval.lowerLimit:20}") Integer lowerLimit,
            @Value("${rule.flatDescriptionSize.condition.interval.upperLimit:50}") Integer upperLimit){
        return new CloseIntervalCondition(lowerLimit,upperLimit);

    }

    @Bean("flatDescriptionSizeGreaterCondition")
    public BooleanCondition flatDescriptionSizeGreaterOrEqualCondition(
            @Value("${rule.flatDescriptionSize.condition.greaterOrEqual.lowerLimit:50}") Integer lowerLimit){
        return new GreaterOrEqualCondition(lowerLimit);
    }
}
