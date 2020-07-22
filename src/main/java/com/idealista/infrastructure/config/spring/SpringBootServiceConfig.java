package com.idealista.infrastructure.config.spring;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.ScoringRule;
import com.idealista.application.service.impl.AdsServiceImpl;
import com.idealista.application.service.impl.ScoringServiceImpl;
import com.idealista.infrastructure.mapper.AdMapper;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringBootServiceConfig {

    @Bean("adsService")
    public AdsServiceImpl adsService(InMemoryPersistence inMemoryPersistence, AdMapper adMapper) {
        return new AdsServiceImpl(inMemoryPersistence, adMapper);
    }

    @Bean("scoringService")
    public ScoringServiceImpl scoringService(
            @Qualifier("rules") final List<Rule> rules,
            @Qualifier("scoringRules") final List<ScoringRule> scoringRules,
            InMemoryPersistence inMemoryPersistence, AdMapper adMapper) {
        return new ScoringServiceImpl(rules, scoringRules, inMemoryPersistence, adMapper);
    }

}
