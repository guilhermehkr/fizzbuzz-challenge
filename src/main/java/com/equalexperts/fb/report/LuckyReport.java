package com.equalexperts.fb.report;

import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class LuckyReport implements Report {

    static final String LUCKY = "lucky";
    static final int ORDER = 3;

    @Override
    public String generateReport(String input) {
        long matches = countWordOccurrencesOf(input, LUCKY);
        return format(REPORT_FORMAT, LUCKY, matches);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
