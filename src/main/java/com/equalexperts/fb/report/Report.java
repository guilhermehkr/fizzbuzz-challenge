package com.equalexperts.fb.report;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;

import static com.equalexperts.fb.runner.Runner.SPACE;
import static java.util.Arrays.stream;

public interface Report extends Ordered {

    String REPORT_FORMAT = "%s: %d";

    String generateReport(String input);

    default long countWordOccurrencesOf(String input, String word) {

        return stream(input.split(SPACE))
                .filter(fragment -> fragment.equals(word))
                .count();
    }

    default long countNumbersOccurrences(String input) {

        return stream(input.split(SPACE))
                .map(fragment -> fragment.replace("-", ""))
                .filter(StringUtils::isNumeric)
                .count();
    }
}
