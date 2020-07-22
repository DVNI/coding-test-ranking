package com.idealista.application.rule.scoring;

public interface BooleanScore {
    Integer score(Boolean ... evaluations);
}
