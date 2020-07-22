package com.idealista.application.formatter;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextSplitter {
    public static List<String> split(String text) {
        return text != null ?
                Arrays.asList(text.split("(?<=[,.])|(?=[,.])|\\s+")) : Collections.emptyList();
    }
}