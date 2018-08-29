package com.equalexperts.fb.rule;

import org.springframework.stereotype.Component;

@Component
public class FivesMultipleRule implements Rule {

    static final String FIVES_MULTIPLE_OUTPUT = "buzz";
    static final int ORDER = 2;

    @Override
    public boolean matches(int number) {
        return number != 0
                && number % 5 == 0;
    }

    @Override
    public String producesResult(int number) {
        return FIVES_MULTIPLE_OUTPUT;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
