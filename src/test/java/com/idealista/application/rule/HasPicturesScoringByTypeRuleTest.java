package com.idealista.application.rule;

import com.idealista.application.rule.impl.HasPicturesRule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.Picture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HasPicturesScoringByTypeRuleTest {

    private BooleanScore simpleScore = Mockito.mock(BooleanSimpleScore.class);
    private HasPicturesRule hasPicturesScoringRule = new HasPicturesRule(simpleScore);
    private Ad ad;

    @Before
    public void setup() {
        ad = new Ad();
        when(simpleScore.score(true)).thenReturn(0);
        when(simpleScore.score(true)).thenReturn(-10);
    }

    @Test
    public void shouldCalculateScoringIfNotHasPictures() {
        ad.setPictures(Collections.<Picture>emptyList());
        Integer scoreResult = hasPicturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(-10), scoreResult);
    }

    @Test
    public void shouldCalculateScoringIfHasPictures() {
        Ad ad = new Ad();
        ad.setPictures(Arrays.asList(new Picture(1, "http://www.idealista.com/pictures/1", "HD")));
        Integer scoreResult = hasPicturesScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }


}
