package com.idealista.application.rule.scoring.impl;

import com.idealista.application.rule.scoring.BooleanScore;
import com.idealista.application.rule.scoring.ScoringByAdTypeFactory;
import com.idealista.domain.data.EnumType;
import com.idealista.domain.data.TypologyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScoringByAdTypologyFactory extends ScoringByAdTypeFactory {

    private final BooleanScore chaletDescriptionSizeSimpleScore;
    private final BooleanScore flatDescriptionSizeScore;
    private final BooleanScore garageDescriptionSizeSimpleScore;

    @Override
    public BooleanScore getScoringByType(EnumType typologyEnum) {
        return typologyEnum.equals(TypologyEnum.CHALET) ? chaletDescriptionSizeSimpleScore
                : typologyEnum.equals(TypologyEnum.FLAT) ? flatDescriptionSizeScore :
                typologyEnum.equals(TypologyEnum.GARAGE) ? garageDescriptionSizeSimpleScore : null;
    }
}
