package com.idealista.application.service.impl;

import com.idealista.application.repository.CrudRepository;
import com.idealista.application.rule.Rule;
import com.idealista.application.rule.ScoringRule;
import com.idealista.application.service.ScoringService;
import com.idealista.domain.data.Ad;
import com.idealista.domain.persistence.AdVO;
import com.idealista.infrastructure.mapper.AdMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class ScoringServiceImpl implements ScoringService {

    private final List<Rule> rules;

    private final List<ScoringRule> scoringRules;

    private final CrudRepository adRepository;

    private final AdMapper adMapper;

    public void calculateScores() {
        List<AdVO> adsVo = adRepository.findAll();
        List<Ad> ads = adMapper.fromAdsVoToAds(adsVo);
        ads = ads.stream().map(this::calculateScore).collect(Collectors.toList());
        ads.stream().forEach(this::calculateIrrelevantScore);
        adRepository.saveAll(adMapper.fromAdsToAdsVo(ads));
    }

    public Ad calculateScore(Ad ad) {
        Integer adScore = rules.stream().filter(rule -> rule.mustApply(ad)).map(rule -> rule.apply(ad)).reduce(0, Integer::sum);
        ad.setScore(adScore);
        return ad;
    }

    public void calculateIrrelevantScore(Ad ad) {
        scoringRules.stream().forEach(scoringRule -> scoringRule.apply(ad));
    }
}
