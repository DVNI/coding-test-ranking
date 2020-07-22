package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.condition.impl.WordsMatchCondition;
import com.idealista.application.rule.impl.DescriptionMatchTermsRule;
import com.idealista.application.rule.scoring.CounterScore;
import com.idealista.application.rule.scoring.impl.CounterSimpleScore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringBootDescriptionMatchTermsRuleConfig {

    @Bean("descriptionMatchTermsRule")
    public DescriptionMatchTermsRule descriptionMatchTermsRule(
            @Qualifier("matchWordScore") CounterScore matchWordSimpleScore,
            @Qualifier("matchCondition") WordsMatchCondition matchCondition) {
        return new DescriptionMatchTermsRule(matchWordSimpleScore, matchCondition);
    }

    @Bean("matchCondition")
    public WordsMatchCondition matchCondition(
            @Value("#{'${rule.descriptionMatchTerms.matchCondition.lookedWords:LUMINOSO,NUEVO,CÉNTRICO,REFORMADO,ÁTICO}'.split(',')}")
                    List<String> lookedWords) {
        return new WordsMatchCondition(lookedWords);
    }

    @Bean("matchWordScore")
    public CounterScore matchWordScore(@Value("${rule.descriptionMatchTerms.score.max:5}") Integer matchWordScore) {
        return new CounterSimpleScore(matchWordScore);
    }

}
