package com.equalexperts.fb.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.report.IntegerReport.INTEGER;
import static com.equalexperts.fb.report.IntegerReport.ORDER;
import static com.equalexperts.fb.report.Report.REPORT_FORMAT;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@SpringBootTest
public class IntegerReportTest {

    private IntegerReport integerReport = new IntegerReport();

    private String input;
    private String expectedResult;

    public IntegerReportTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {"fizz lucky test fizz", format(REPORT_FORMAT, INTEGER, 0)},
                {"fizz 2 buzz lucky test 0 34 fizz", format(REPORT_FORMAT, INTEGER, 3)},
                {"buzz -20 lucky -9 fizz buzz fizzbuzz 30 40 500", format(REPORT_FORMAT, INTEGER, 5)}
        };
        return asList(values);
    }

    @Test
    public void shouldRunIntegerReportGivenInputAndAssertResult() {

        assertThat(integerReport.generateReport(input), is(expectedResult));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(integerReport.getOrder(), is(ORDER));
    }
}