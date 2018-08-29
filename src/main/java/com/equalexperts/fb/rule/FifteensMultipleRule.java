package com.equalexperts.fb.rule;

import org.springframework.stereotype.Component;

@Component
public class FifteensMultipleRule implements Rule {

    static final String FIFTEENS_MULTIPLE_OUTPUT = "fizzbuzz";
    static final int ORDER = 1;

    @Override
    public boolean matches(int number) {
        return number != 0
                && number % 15 == 0;
    }

    @Override
    public String producesResult(int number) {
        return FIFTEENS_MULTIPLE_OUTPUT;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
