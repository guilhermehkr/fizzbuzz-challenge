package com.equalexperts.fb.report;

import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class IntegerReport implements Report {

    static final String INTEGER = "integer";
    static final int ORDER = 4;

    @Override
    public String generateReport(String input) {
        long matches = countNumbersOccurrences(input);
        return format(REPORT_FORMAT, INTEGER, matches);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
