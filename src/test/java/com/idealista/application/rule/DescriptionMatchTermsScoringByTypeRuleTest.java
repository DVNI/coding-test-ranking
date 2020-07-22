package com.idealista.application.rule;

import com.idealista.application.rule.condition.impl.WordsMatchCondition;
import com.idealista.application.rule.impl.DescriptionMatchTermsRule;
import com.idealista.application.rule.scoring.CounterScore;
import com.idealista.application.rule.scoring.impl.CounterSimpleScore;
import com.idealista.domain.data.Ad;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DescriptionMatchTermsScoringByTypeRuleTest {

    private CounterScore matchWordSimpleScore = Mockito.mock(CounterSimpleScore.class);
    private final static List<String> lookedWords = Arrays.asList("LUMINOSO", "NUEVO", "CÉNTRICO", "REFORMADO", "ÁTICO" );
    private WordsMatchCondition matchCondition = new WordsMatchCondition(lookedWords);
    private Ad ad;
    private DescriptionMatchTermsRule descriptionMatchTermsScoringRule =
            new DescriptionMatchTermsRule(matchWordSimpleScore, matchCondition);

    @Before
    public void setup() {
        ad = new Ad();
        when(matchWordSimpleScore.score(Integer.valueOf(0))).thenReturn(0);
        when(matchWordSimpleScore.score(Integer.valueOf(1))).thenReturn(5);
        when(matchWordSimpleScore.score(Integer.valueOf(2))).thenReturn(10);
    }

    @Test
    public void shouldCalculateScoringWithoutDescription() {
        Integer scoreResult = descriptionMatchTermsScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithEmptyDescription() {
        ad.setDescription("");
        Integer scoreResult = descriptionMatchTermsScoringRule.apply(ad);
        assertEquals(Integer.valueOf(0), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithMatchInDescription() {
        ad.setDescription("LUMINOSO");
        Integer scoreResult = descriptionMatchTermsScoringRule.apply(ad);
        assertEquals(Integer.valueOf(5), scoreResult);
    }

    @Test
    public void shouldCalculateScoringWithFilledDescription() {
        ad.setDescription(" LUMINOSO,REFORMADO ");
        Integer scoreResult = descriptionMatchTermsScoringRule.apply(ad);
        assertEquals(Integer.valueOf(10), scoreResult);
    }
}
