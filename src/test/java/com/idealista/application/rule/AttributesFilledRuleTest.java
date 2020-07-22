package com.idealista.application.rule;

import com.idealista.application.rule.impl.AttributesFilledRule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.Picture;
import com.idealista.domain.data.TypologyEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AttributesFilledRuleTest {

    private BooleanScore attributesFilledSimpleScore = Mockito.mock(BooleanSimpleScore.class);
    private AttributesFilledRule attributesFilledScoringRule = new AttributesFilledRule(attributesFilledSimpleScore);
    private Ad ad;

    @Before
    public void setup(){
        ad = new Ad();
        when(attributesFilledSimpleScore.score(true)).thenReturn(40);
        when(attributesFilledSimpleScore.score(false)).thenReturn(0);
    }

    @Test
    public void shouldCalculateScoringIfFlatNotHasAllAttributes() {
        ad.setTypology(TypologyEnum.FLAT);
        Integer scoreResult = attributesFilledScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringIfFlatHasAllAttributes() {
        ad.setPictures(Arrays.asList(new Picture(1, "http://www.idealista.com/pictures/1", "HD")));
        ad.setTypology(TypologyEnum.FLAT);
        ad.setDescription("slkafhldf ");
        ad.setHouseSize(20);
        Integer scoreResult = attributesFilledScoringRule.apply(ad);
        assertEquals(Integer.valueOf(40), scoreResult);
    }

    @Test
    public void shouldCalculateScoringIfChaletNotHasAllAttributes() {
        ad.setTypology(TypologyEnum.CHALET);
        Integer scoreResult = attributesFilledScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringIfChaletHasAllAttributes() {
        ad.setPictures(Arrays.asList(new Picture(1, "http://www.idealista.com/pictures/1", "HD")));
        ad.setTypology(TypologyEnum.CHALET);
        ad.setDescription(" ");
        ad.setHouseSize(20);
        ad.setGardenSize(20);
        Integer scoreResult = attributesFilledScoringRule.apply(ad);
        assertEquals(Integer.valueOf(40), scoreResult);
    }
}
