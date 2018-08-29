package com.equalexperts.fb.rule;

import org.springframework.stereotype.Component;

@Component
public class ThreesMultipleRule implements Rule {

    public static final String THREES_MULTIPLE_OUTPUT = "fizz";
    static final int ORDER = 3;

    @Override
    public boolean matches(int number) {
        return number != 0
                && number % 3 == 0;
    }

    @Override
    public String producesResult(int number) {
        return THREES_MULTIPLE_OUTPUT;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
