package com.equalexperts.fb.rule;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import static java.lang.String.valueOf;

@Component
public class ContainsThreeRule implements Rule {

    static final String CONTAINS_THREE_OUTPUT = "lucky";

    @Override
    public boolean matches(int number) {
        return valueOf(number)
                .contains("3");
    }

    @Override
    public String producesResult(int number) {
        return CONTAINS_THREE_OUTPUT;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
