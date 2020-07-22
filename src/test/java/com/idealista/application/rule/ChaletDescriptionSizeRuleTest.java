package com.idealista.application.rule;

import com.idealista.application.rule.condition.BooleanCondition;
import com.idealista.application.rule.impl.ChaletDescriptionSizeRule;
import com.idealista.application.rule.condition.impl.GreaterCondition;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.impl.BooleanSimpleScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.application.rule.scoring.impl.ScoringByAdTypologyFactory;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.TypologyEnum;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ChaletDescriptionSizeRuleTest {

    private BooleanCondition condition = Mockito.mock(GreaterCondition.class);
    private ScoringByAdTypeFactory scoringByTypologyFacade = Mockito.mock(ScoringByAdTypologyFactory.class);
    private BooleanScore simpleScore = Mockito.mock(BooleanSimpleScore.class);
    private ChaletDescriptionSizeRule chaletDescriptionSizeScoringRule =
            new ChaletDescriptionSizeRule(scoringByTypologyFacade, condition);

    private Ad ad;

    @Before
    public void setup() {
        ad = new Ad();
        ad.setTypology(TypologyEnum.CHALET);
        when(scoringByTypologyFacade.getScoringByType(TypologyEnum.CHALET)).thenReturn(simpleScore);
        when(condition.evaluate(0)).thenReturn(false);
        when(condition.evaluate(5)).thenReturn(true);
        when(simpleScore.score(true)).thenReturn(20);
        when(simpleScore.score(false)).thenReturn(0);


    }

    @Test
    public void shouldCalculateScoringWithoutDescription() {
        Integer scoreResult = chaletDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithEmptyDescription() {
        ad.setDescription("");
        Integer scoreResult = chaletDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithDescriptionSizeGreater() {
        ad.setDescription("alksas ldfhhdfs lahflakdf lakjflkdfa lakfsjlasf");
        Integer scoreResult = chaletDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(20), scoreResult);
    }


}
