package com.equalexperts.fb.report;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;

import java.util.Collection;

import static com.equalexperts.fb.report.FizzReport.FIZZ;
import static com.equalexperts.fb.report.Report.REPORT_FORMAT;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@SpringBootTest
public class FizzReportTest {

    private FizzReport fizzReport = new FizzReport();

    private String input;
    private String expectedResult;

    public FizzReportTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {"1 2 buzz lucky test 0 34 89", format(REPORT_FORMAT, FIZZ, 0)},
                {"fizz 2 buzz lucky test 0 34 fizz", format(REPORT_FORMAT, FIZZ, 2)},
                {"20 lucky fizz buzz fizzbuzz", format(REPORT_FORMAT, FIZZ, 1)},
                {"fizzbuzz lucky fizz buzz fizzbuzz fizzbuzz buzz fizz buzz fizz", format(REPORT_FORMAT, FIZZ, 3)}
        };
        return asList(values);
    }

    @Test
    public void shouldRunFizzReportGivenInputAndAssertResult() {

        assertThat(fizzReport.generateReport(input), is(expectedResult));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(fizzReport.getOrder(), is(Ordered.HIGHEST_PRECEDENCE));
    }
}