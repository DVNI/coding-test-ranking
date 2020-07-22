package com.idealista.application.rule.condition.impl;

import com.idealista.application.formatter.TextSplitter;
import com.idealista.application.rule.condition.MatchCounterCondition;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WordsMatchCondition implements MatchCounterCondition {

    private final List<String> lookedWords;

    @Override
    public Integer evaluate(String text) {
        List<String> formatLookedWords = lookedWords.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> splitText = TextSplitter.split(text);
        return splitText.stream()
                .map(String::toUpperCase)
                .distinct()
                .filter(formatLookedWords::contains)
                .collect(Collectors.toSet()).size();
    }
}
