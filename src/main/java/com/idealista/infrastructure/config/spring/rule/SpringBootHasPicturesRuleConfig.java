package com.idealista.infrastructure.config.spring.rule;

import com.idealista.application.rule.Rule;
import com.idealista.application.rule.impl.HasPicturesRule;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootHasPicturesRuleConfig {

    @Bean(name = "hasPictureRule")
    public Rule hasPictureRule(@Qualifier("hasPictureScore") BooleanSimpleScore hasPictureSimpleScore) {
        return new HasPicturesRule(hasPictureSimpleScore);
    }

    @Bean(name = "hasPictureScore")
    public BooleanSimpleScore hasPictureScore(@Value("${rule.hasPictures.score.max:0}") Integer hasPictureScore,
                                              @Value("${rule.hasPictures.score.min:-10}") Integer emptyPicturesScore) {
        return new BooleanSimpleScore(hasPictureScore,emptyPicturesScore);
    }


}
