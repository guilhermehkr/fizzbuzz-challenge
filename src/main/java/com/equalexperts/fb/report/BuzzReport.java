package com.equalexperts.fb.report;

import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class BuzzReport implements Report {

    static final String BUZZ = "buzz";
    static final int ORDER = 1;

    @Override
    public String generateReport(String input) {
        long matches = countWordOccurrencesOf(input, BUZZ);
        return format(REPORT_FORMAT, BUZZ, matches);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
