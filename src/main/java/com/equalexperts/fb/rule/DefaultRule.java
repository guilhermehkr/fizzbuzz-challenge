package com.equalexperts.fb.rule;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class DefaultRule implements Rule {

    @Override
    public boolean matches(int number) {
        return true;
    }

    @Override
    public String producesResult(int number) {
        return String.valueOf(number);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
