package com.idealista.application.service;

import com.idealista.domain.api.PublicAd;
import com.idealista.domain.api.QualityAd;

import java.util.List;

public interface AdsService {
    List<PublicAd> getPublicAds();
    List<QualityAd> getAdminAds();
}
