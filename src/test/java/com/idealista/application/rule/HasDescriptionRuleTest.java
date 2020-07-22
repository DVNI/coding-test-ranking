package com.idealista.application.rule;

import com.idealista.application.rule.impl.HashDescriptionRule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.domain.data.Ad;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HasDescriptionRuleTest {

    private BooleanScore simpleScore = Mockito.mock(BooleanSimpleScore.class);
    private HashDescriptionRule hashDescriptionRule = new HashDescriptionRule(simpleScore);

    private Ad ad;

    @Before
    public void setup() {
        ad = new Ad();
        when(simpleScore.score(true)).thenReturn(5);
        when(simpleScore.score(false)).thenReturn(0);
    }

    @Test
    public void shouldCalculateScoringWithoutDescription() {
        Integer scoreResult = hashDescriptionRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithEmptyDescription() {
        ad.setDescription(" ");
        Integer scoreResult = hashDescriptionRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithRareDescription() {
        ad.setDescription(" ,");
        Integer scoreResult = hashDescriptionRule.apply(ad);
        assertEquals(Integer.valueOf(5), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithDescription() {
        ad.setDescription("kashlha");
        Integer scoreResult = hashDescriptionRule.apply(ad);
        assertEquals(Integer.valueOf(5), scoreResult);
    }
}
