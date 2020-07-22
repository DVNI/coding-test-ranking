package com.idealista.application.rule;

import com.idealista.application.rule.condition.impl.CloseIntervalCondition;
import com.idealista.application.rule.condition.impl.GreaterOrEqualCondition;
import com.idealista.application.rule.impl.FlatDescriptionSizeRule;
import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.Ad;
import com.idealista.domain.data.TypologyEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlatDescriptionSizeRuleTest {


    private CloseIntervalCondition closeIntervalCondition = Mockito.mock(CloseIntervalCondition.class);
    private GreaterOrEqualCondition greaterOrEqualCondition  = Mockito.mock(GreaterOrEqualCondition.class);
    private ScoringByAdTypeFactory scoringByTypologyFacade = Mockito.mock(ScoringByAdTypeFactory.class);
    private BooleanScore score = Mockito.mock(BooleanScore.class);
    private FlatDescriptionSizeRule flatDescriptionSizeScoringRule =
            new FlatDescriptionSizeRule(scoringByTypologyFacade, closeIntervalCondition, greaterOrEqualCondition);

    private Ad ad;

    @Before
    public void setup() {
        ad = new Ad();
        ad.setTypology(TypologyEnum.FLAT);
        when(scoringByTypologyFacade.getScoringByType(TypologyEnum.FLAT)).thenReturn(score);

        when(greaterOrEqualCondition.evaluate(4)).thenReturn(false);
        when(closeIntervalCondition.evaluate(4)).thenReturn(false);

        when(greaterOrEqualCondition.evaluate(7)).thenReturn(false);
        when(closeIntervalCondition.evaluate(7)).thenReturn(true);

        when(greaterOrEqualCondition.evaluate(10)).thenReturn(true);
        when(closeIntervalCondition.evaluate(10)).thenReturn(false);

        when(score.score(false, false)).thenReturn(0);
        when(score.score(false, true)).thenReturn(10);
        when(score.score(true, false)).thenReturn(20);
    }

    @Test
    public void shouldCalculateScoringWithoutDescription() {
        Integer scoreResult = flatDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithEmptyDescription() {
        ad.setDescription("");
        Integer scoreResult = flatDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithDescriptionSizeIsLower() {
        ad.setDescription("alksas ldfhhdfs lahflakdf lakfsjlasf");
        Integer scoreResult = flatDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithDescriptionSizeIsInInterval() {
        ad.setDescription("alksas ldfhhdfs lahflakdf lakjflkdfa:lakfsjlasf alskfjlfsa, aoyas");
        Integer scoreResult = flatDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(10), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithDescriptionSizeIsEqual() {
        ad.setDescription("alksas ldfhhdfs lahflakdf lakjflkdfa:lakfsjlasf alskfjlfsa, aoyas hgsfd sdfhsflkd sffsd");
        Integer scoreResult = flatDescriptionSizeScoringRule.apply(ad);
        assertEquals(Integer.valueOf(20), scoreResult);
    }
}
