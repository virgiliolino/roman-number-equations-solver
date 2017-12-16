package com.roman.domain.sentence.matcher;

import com.roman.domain.sentence.ExpressionMatcher;

public class DefinitionMatcher extends ExpressionMatcher {
    public DefinitionMatcher() {
        super("(\\w+)\\s+(is)\\s+(\\w+)");
    }
}
