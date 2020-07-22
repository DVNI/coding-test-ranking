package com.idealista.application.formatter;

import com.idealista.domain.data.Ad;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdsFormatter {

    public static List<Ad> formatAdsForPublicAds(List<Ad> ads) {
        Stream<Ad> adsStream = ads.stream();
        adsStream = filterByContainsScoring(adsStream);
        adsStream = filterByRelevant(adsStream);
        adsStream = sortReverseByScore(adsStream);
        return  adsStream.collect(Collectors.toList());
    }

    public static List<Ad> formatAdsForQualityAds(List<Ad> ads) {
        Stream<Ad> adsStream = ads.stream();
        adsStream = filterByContainsScoring(adsStream);
        adsStream = sortByScore(adsStream);
        return  adsStream.collect(Collectors.toList());
    }

    private static Stream<Ad> filterByContainsScoring(Stream<Ad> ads) {
        return ads.filter(ad -> Objects.nonNull(ad.getScore()));
    }

    private static Stream<Ad> filterByRelevant(Stream<Ad> ads) {
        return ads.filter(ad -> Objects.isNull(ad.getIrrelevantSince()));
    }
    private static Stream<Ad> sortByScore(Stream<Ad> ads) {
        return ads.sorted(Comparator.comparingInt(Ad::getScore));
    }
    private static Stream<Ad> sortReverseByScore(Stream<Ad> ads) {
        return ads.sorted(Comparator.comparingInt(Ad::getScore).reversed());
    }
}
