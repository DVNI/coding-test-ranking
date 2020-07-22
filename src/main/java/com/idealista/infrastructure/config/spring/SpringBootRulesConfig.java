package com.idealista.infrastructure.config.spring;


import com.idealista.application.rule.Rule;
import com.idealista.application.rule.ScoringRule;
import com.idealista.infrastructure.config.spring.rule.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

@Configuration
@Import({ SpringBootHasPicturesRuleConfig.class, SpringBootPictureQualityRuleConfig.class,
        SpringBootDescriptionMatchTermsRuleConfig.class, SpringBootAttributesFilledRuleConfig.class,
        SpringBootChaletDescriptionSizeRuleConfig.class, SpringBootFlatDescriptionSizeRuleConfig.class,
        SpringBootIrrelevantScoringRuleConfig.class, SpringBootRangeScoringRule.class})
public class SpringBootRulesConfig {

    @Bean("rules")
    public List<Rule> rules(
            @Qualifier("hasPictureRule") final Rule hasPictureRule,
            @Qualifier("pictureQualityRule") final Rule pictureQualityRule,
            @Qualifier("descriptionMatchTermsRule") final Rule descriptionMatchTermsRule,
            @Qualifier("attributesFilledRule") final Rule attributesFilledRule,
            @Qualifier("chaletDescriptionSizeRule") final Rule chaletDescriptionSizeRule,
            @Qualifier("flatDescriptionSizeRule") final Rule flatDescriptionSizeRule,
            @Qualifier("hasDescriptionRule") final Rule hasDescriptionRule) {
        return Arrays.asList(hasPictureRule, pictureQualityRule, descriptionMatchTermsRule,
                attributesFilledRule, chaletDescriptionSizeRule, flatDescriptionSizeRule, hasDescriptionRule);
    }

    @Bean("scoringRules")
    public List<ScoringRule> scoringRules(@Qualifier("irrelevantScoringRule") final ScoringRule irrelevantScoringRule,
                                          @Qualifier("rangeScoringRule") final ScoringRule rangeScoringRule) {
        return Arrays.asList(irrelevantScoringRule, rangeScoringRule);
    }

}
