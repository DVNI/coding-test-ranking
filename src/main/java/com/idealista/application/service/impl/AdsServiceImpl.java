package com.idealista.application.service.impl;

import com.idealista.application.formatter.AdsFormatter;
import com.idealista.application.repository.CrudRepository;
import com.idealista.application.service.AdsService;
import com.idealista.domain.api.PublicAd;
import com.idealista.domain.api.QualityAd;
import com.idealista.domain.data.Ad;
import com.idealista.domain.persistence.AdVO;
import com.idealista.infrastructure.mapper.AdMapper;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final CrudRepository adRepository;
    private final AdMapper adMapper;

    public List<PublicAd> getPublicAds() {
        List<AdVO> persistedAds = adRepository.findAll();
        List<Ad> ads = adMapper.fromAdsVoToAds(persistedAds);
        List<Ad> formattedAds = AdsFormatter.formatAdsForPublicAds(ads);
        List<PublicAd> formattedPublicAds = adMapper.fomAdsToPublicAds(formattedAds);
        return formattedPublicAds;
    }

    public List<QualityAd> getAdminAds() {
        List<AdVO> persistedAds = adRepository.findAll();
        List<Ad> ads = adMapper.fromAdsVoToAds(persistedAds);
        List<Ad> formattedAds = AdsFormatter.formatAdsForQualityAds(ads);
        List<QualityAd> qualityAds = adMapper.fomAdsToQualityAds(formattedAds);
        return qualityAds;
    }

}
