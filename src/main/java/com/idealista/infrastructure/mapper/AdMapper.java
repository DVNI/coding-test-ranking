package com.idealista.infrastructure.mapper;

import com.idealista.domain.api.QualityAd;
import com.idealista.domain.data.PictureQualityEnum;
import com.idealista.domain.api.PublicAd;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.TypologyEnum;
import com.idealista.domain.data.Picture;
import com.idealista.domain.persistence.AdVO;
import com.idealista.domain.persistence.PictureVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AdMapper {

    @Mapping(target = "typology", source = "typology", qualifiedByName = "fromTypologyStringToTypologyEnum")
    public abstract Ad fromAdVoToAd(AdVO adVO);

    public abstract List<Ad> fromAdsVoToAds(List<AdVO> adsVO);

    @Mapping(target = "typology", source = "typology", qualifiedByName = "fromTypologyStringToTypologyEnum")
    public abstract AdVO fromAdToAdVo(Ad ad);

    public abstract List<AdVO> fromAdsToAdsVo(List<Ad> ads);

    public abstract Picture fromPictureVoToPicture(PictureVO pictureVO);

    public abstract List<Picture> fromPicturesVoToPictures(List<PictureVO> picturesVO);


    public abstract PictureVO fromPictureToPictureVo(Picture picture);

    public abstract List<PictureVO> fromPicturesToPicturesVo(List<Picture> pictures);


    @Mapping(target = "pictureUrls", source = "pictures", qualifiedByName = "fromPicturesToPictureUrls")
    public abstract PublicAd fomAdToPublicAd(Ad ad);

    public abstract List<PublicAd> fomAdsToPublicAds(List<Ad> ad);

    @Mapping(target = "pictureUrls", source = "pictures", qualifiedByName = "fromPicturesToPictureUrls")
    public abstract QualityAd fomAdToQualityAd(Ad ad);

    public abstract List<QualityAd> fomAdsToQualityAds(List<Ad> ad);

    @Named("fromTypologyStringToTypologyEnum")
    public static TypologyEnum fromTypologyStringToTypologyEnum(String typology) {
        return TypologyEnum.fromValue(typology).get();
    }

    @Named("fromTypologyEnumToTypologyString")
    public static  String fromTypologyEnumToTypologyString(TypologyEnum typology) {
        return typology.getName();
    }

    @Named("fromQualityStringToQualityEnum")
    public static PictureQualityEnum fromQualityStringToQualityEnum(String quality) {
        return PictureQualityEnum.fromValue(quality);
    }

    @Named("fromPicturesToPictureUrls")
    public static List<String> fromPicturesToPictureUrls(List<Picture> pictures) {
        return pictures.stream().map(Picture::getUrl).collect(Collectors.toList());
    }
}
