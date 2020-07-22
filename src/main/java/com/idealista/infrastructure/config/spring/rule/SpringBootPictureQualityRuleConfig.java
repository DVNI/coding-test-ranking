package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.impl.PictureQualityRule;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.application.rule.scoring.impl.ScoringByAdPictureQualityFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootPictureQualityRuleConfig {

    @Bean("pictureQualityRule")
    public Rule pictureQualityRule(ScoringByAdPictureQualityFactory pictureQualityScoringFacade) {
        return new PictureQualityRule(pictureQualityScoringFacade);
    }

    @Bean("pictureQualityScoringFacade")
    public ScoringByAdPictureQualityFactory pictureQualityScoringFacade(
            @Qualifier("highResolutionScore") final BooleanSimpleScore highResolutionSimpleScore,
            @Qualifier("otherResolutionScore") final BooleanSimpleScore otherResolutionMaxSimpleScore) {
        return  new ScoringByAdPictureQualityFactory(highResolutionSimpleScore, otherResolutionMaxSimpleScore);
    }

    @Bean("highResolutionScore")
    public BooleanSimpleScore highResolutionScore(
            @Value("${rule.pictureQuality.highResolutionScore.max:20}") Integer highResolutionMaxScore,
            @Value("${rule.pictureQuality.highResolutionScore.min:0}") Integer highResolutionMinScore) {
        return new BooleanSimpleScore(highResolutionMaxScore, highResolutionMinScore);
    }

    @Bean("otherResolutionScore")
    public BooleanSimpleScore otherResolutionScore(
            @Value("${rule.pictureQuality.otherResolutionScore.max:10}") Integer otherResolutionMaxScore,
            @Value("${rule.pictureQuality.otherResolutionScore.min:0}") Integer otherResolutionMinScore) {
        return new BooleanSimpleScore(otherResolutionMaxScore, otherResolutionMinScore);
    }
}
