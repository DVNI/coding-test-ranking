package com.idealista.infrastructure.api;

import java.util.List;

import com.idealista.application.service.AdsService;
import com.idealista.application.service.ScoringService;
import com.idealista.application.service.impl.AdsServiceImpl;
import com.idealista.application.service.impl.ScoringServiceImpl;
import com.idealista.domain.api.PublicAd;
import com.idealista.domain.api.QualityAd;
import com.idealista.domain.data.Ad;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdsController {

    private final AdsService adsService;

    private final ScoringService scoringService;

    @GetMapping("/admin/quality/ads")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        List<QualityAd> qualityAds = adsService.getAdminAds();
        return qualityAds.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(qualityAds);

    }

    @GetMapping("/ads")
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<PublicAd> publicAds = adsService.getPublicAds();
        return publicAds.isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.ok().body(publicAds);
    }

    @PostMapping("/ads/score")
    public ResponseEntity<Void> calculateScore() {
        scoringService.calculateScores();
        return ResponseEntity.noContent().build();
    }
}
