package com.equalexperts.fb.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.report.FizzBuzzReport.ORDER;
import static com.equalexperts.fb.report.FizzBuzzReport.FIZZBUZZ;
import static com.equalexperts.fb.report.Report.REPORT_FORMAT;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@SpringBootTest
public class FizzBuzzReportTest {

    private FizzBuzzReport fizzbuzzReport = new FizzBuzzReport();

    private String input;
    private String expectedResult;

    public FizzBuzzReportTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {"fizz 2 buzz lucky test 0 34 fizz", format(REPORT_FORMAT, FIZZBUZZ, 0)},
                {"buzz 20 lucky 9 fizz buzz fizzbuzz", format(REPORT_FORMAT, FIZZBUZZ, 1)},
                {"fizzbuzz fizz lucky fizz fizzbuzz fizzbuzz buzz 30", format(REPORT_FORMAT, FIZZBUZZ, 3)}
        };
        return asList(values);
    }

    @Test
    public void shouldRunFizzBuzzReportGivenInputAndAssertResult() {

        assertThat(fizzbuzzReport.generateReport(input), is(expectedResult));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(fizzbuzzReport.getOrder(), is(ORDER));
    }
}