package com.equalexperts.fb.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.report.LuckyReport.LUCKY;
import static com.equalexperts.fb.report.LuckyReport.ORDER;
import static com.equalexperts.fb.report.Report.REPORT_FORMAT;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@SpringBootTest
public class LuckyReportTest {

    private LuckyReport luckyReport = new LuckyReport();

    private String input;
    private String expectedResult;

    public LuckyReportTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {"fizz 2 buzz test 0 34 fizz", format(REPORT_FORMAT, LUCKY, 0)},
                {"buzz 20 lucky 9 fizz buzz fizzbuzz", format(REPORT_FORMAT, LUCKY, 1)},
                {"lucky 8 fizz 10 lucky fizzbuzz lucky 30 20 7", format(REPORT_FORMAT, LUCKY, 3)}
        };
        return asList(values);
    }

    @Test
    public void shouldRunLuckyReportGivenInputAndAssertResult() {

        assertThat(luckyReport.generateReport(input), is(expectedResult));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(luckyReport.getOrder(), is(ORDER));
    }
}