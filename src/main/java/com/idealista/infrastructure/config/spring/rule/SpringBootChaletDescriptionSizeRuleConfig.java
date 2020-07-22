package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.condition.impl.GreaterCondition;
import com.idealista.application.rule.impl.ChaletDescriptionSizeRule;
import com.idealista.application.rule.scoring.impl.BooleanComplexScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.application.rule.scoring.impl.ScoringByAdTypologyFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootChaletDescriptionSizeRuleConfig {

    @Bean("chaletDescriptionSizeRule")
    public ChaletDescriptionSizeRule chaletDescriptionSizeRule(
            @Qualifier("scoringByTypologyFacade") final ScoringByAdTypeFactory scoringByTypologyFacade,
            @Qualifier("chaletDescriptionSizeGreaterOrEqualCondition") final GreaterCondition greaterCondition) {
        return new ChaletDescriptionSizeRule(scoringByTypologyFacade, greaterCondition);
    }

    @Bean("scoringByTypologyFacade")
    public ScoringByAdTypeFactory scoringByTypologyFacade(@Qualifier( "chaletDescriptionSizeScore") final BooleanSimpleScore chaletDescriptionSizeSimpleScore,
                                                          @Qualifier( "flatDescriptionSizeScore") final BooleanComplexScore flatDescriptionSizeScore,
                                                          @Qualifier( "flatDescriptionSizeScore")final BooleanSimpleScore garageDescriptionSizeSimpleScore) {

        return new ScoringByAdTypologyFactory(chaletDescriptionSizeSimpleScore, flatDescriptionSizeScore, garageDescriptionSizeSimpleScore);
    }

    @Bean("chaletDescriptionSizeScore")
    public BooleanSimpleScore chaletDescriptionSizeScore(
            @Value("${rule.chaletDescriptionSize.score.max:20}") Integer chaletDescriptionSizeMaxScore,
            @Value("${rule.chaletDescriptionSize.score.min:0}") Integer chaletDescriptionSizeMinScore) {
        return new BooleanSimpleScore(chaletDescriptionSizeMaxScore,chaletDescriptionSizeMinScore);
    }

    @Bean("flatDescriptionSizeScore")
    public BooleanComplexScore flatDescriptionSizeScore(
            @Value("${rule.garageDescriptionSize.score.max:30}") Integer flatDescriptionSizeMaxScore,
            @Value("${rule.garageDescriptionSize.score.min:0}") Integer flatDescriptionSizeMinScore,
            @Value("${rule.garageDescriptionSize.score.inRange:10}") Integer flatDescriptionSizeInRangeScore){
        return new BooleanComplexScore(flatDescriptionSizeMaxScore, flatDescriptionSizeMinScore,
                flatDescriptionSizeInRangeScore);
    }

    @Bean("garageDescriptionSizeScore")
    public BooleanSimpleScore garageDescriptionSizeScore(
            @Value("${rule.garageDescriptionSize.score.max:0}") Integer garageDescriptionSizeMaxScore,
            @Value("${rule.garageDescriptionSize.score.min:0}") Integer garageDescriptionSizeMinScore) {
        return new BooleanSimpleScore(garageDescriptionSizeMaxScore, garageDescriptionSizeMinScore);
    }

    @Bean("chaletDescriptionSizeGreaterOrEqualCondition")
    public GreaterCondition chaletDescriptionSizeGreaterOrEqualCondition(
            @Value("${rule.chaletDescriptionSize.condition.upperLimit:50}") Integer upperLimit){
        return new GreaterCondition(upperLimit);
    }
}
