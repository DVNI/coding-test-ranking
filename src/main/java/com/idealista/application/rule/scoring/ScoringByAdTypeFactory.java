package com.idealista.application.rule.scoring;


import com.idealista.domain.data.EnumType;

public abstract class ScoringByAdTypeFactory {
    public abstract BooleanScore getScoringByType(EnumType enumType);
}
