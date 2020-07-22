package com.idealista.application.rule.scoring;

public interface Score {
    Integer score(Boolean ... evaluations);
}
