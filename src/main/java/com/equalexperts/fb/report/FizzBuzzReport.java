package com.equalexperts.fb.report;

import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class FizzBuzzReport implements Report {

    static final String FIZZBUZZ = "fizzbuzz";
    static final int ORDER = 2;

    @Override
    public String generateReport(String input) {
        long matches = countWordOccurrencesOf(input, FIZZBUZZ);
        return format(REPORT_FORMAT, FIZZBUZZ, matches);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
