package com.equalexperts.fb.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;

import java.util.Collection;

import static com.equalexperts.fb.report.BuzzReport.BUZZ;
import static com.equalexperts.fb.report.BuzzReport.ORDER;
import static com.equalexperts.fb.report.Report.REPORT_FORMAT;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@SpringBootTest
public class BuzzReportTest {

    private BuzzReport buzzReport = new BuzzReport();

    private String input;
    private String expectedResult;

    public BuzzReportTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {"fizz 2 lucky test 0 34 fizz", format(REPORT_FORMAT, BUZZ, 0)},
                {"fizz 2 buzz lucky test 0 34 fizz", format(REPORT_FORMAT, BUZZ, 1)},
                {"buzz 20 lucky 9 fizz buzz fizzbuzz", format(REPORT_FORMAT, BUZZ, 2)},
                {"fizzbuzz lucky fizz fizzbuzz fizzbuzz buzz 30 buzz buzz", format(REPORT_FORMAT, BUZZ, 3)}
        };
        return asList(values);
    }

    @Test
    public void shouldRunBuzzReportGivenInputAndAssertResult() {

        assertThat(buzzReport.generateReport(input), is(expectedResult));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(buzzReport.getOrder(), is(ORDER));
    }
}