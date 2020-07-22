package com.idealista.application.rule;

import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.Picture;
import com.idealista.application.rule.impl.PictureQualityRule;
import com.idealista.domain.data.PictureQualityEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PictureQualityScoringByTypeTest {

    private BooleanScore highResolutionSimpleScore = Mockito.mock(BooleanSimpleScore.class);
    private BooleanScore otherResolutionSimpleScore = Mockito.mock(BooleanSimpleScore.class);
    private final ScoringByAdTypeFactory scoringByAdTypeFactory = Mockito.mock(ScoringByAdTypeFactory.class);
    private final PictureQualityRule picturesScoringRule = new PictureQualityRule(scoringByAdTypeFactory);
    private Ad ad;

    @Before
    public void setup() {
        ad = new Ad();
        when(scoringByAdTypeFactory.getScoringByType(PictureQualityEnum.HIGH_RESOLUTION)).thenReturn(highResolutionSimpleScore);
        when(scoringByAdTypeFactory.getScoringByType(PictureQualityEnum.OTHER_RESOLUTION)).thenReturn(otherResolutionSimpleScore);
        when(highResolutionSimpleScore.score(true)).thenReturn(20);
        when(highResolutionSimpleScore.score(false)).thenReturn(0);
        when(otherResolutionSimpleScore.score(true)).thenReturn(10);
        when(otherResolutionSimpleScore.score(false)).thenReturn(0);
    }

    @Test
    public void shouldCalculateScoringWithoutPictures() {
        ad.setPictures(Collections.<Picture>emptyList());
        Integer scoreResult = picturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWitHDPicture() {
        ad.setPictures(Arrays.asList(new Picture(1, "http://www.idealista.com/pictures/1", "HD")));
        Integer scoreResult = picturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(20), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWitHDPictures() {
        ad.setPictures(Arrays.asList(
                new Picture(1, "http://www.idealista.com/pictures/1", "HD"),
                new Picture(2, "http://www.idealista.com/pictures/2", "HD")));
        Integer scoreResult = picturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(40), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWitSDPicture() {
        ad.setPictures(Arrays.asList(new Picture(1, "http://www.idealista.com/pictures/1", "SD")));
        Integer scoreResult = picturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(10), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWitSDPictures() {
        ad.setPictures(Arrays.asList(
                new Picture(1, "http://www.idealista.com/pictures/1", "SD"),
                new Picture(2, "http://www.idealista.com/pictures/2", "SD")));
        Integer scoreResult = picturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(20), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWitHDAndSDPictures() {
        ad.setPictures(Arrays.asList(
                new Picture(1, "http://www.idealista.com/pictures/1", "HD"),
                new Picture(2, "http://www.idealista.com/pictures/2", "SD")));
        Integer scoreResult = picturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(30), scoreResult);
    }
}
