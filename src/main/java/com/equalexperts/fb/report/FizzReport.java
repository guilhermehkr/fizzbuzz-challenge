package com.equalexperts.fb.report;

import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class FizzReport implements Report {

    static final String FIZZ = "fizz";

    @Override
    public String generateReport(String input) {
        long matches = countWordOccurrencesOf(input, FIZZ);
        return format(REPORT_FORMAT, FIZZ, matches);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
