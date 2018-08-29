package com.equalexperts.fb.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.apache.commons.lang3.reflect.FieldUtils.readField;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ReportRunner.class,
        FizzReport.class,
        BuzzReport.class,
        FizzBuzzReport.class,
        LuckyReport.class,
        IntegerReport.class
})
public class ReportRunnerIntegrationTest {

    @Autowired
    private ReportRunner reportRunner;

    private static final String REPORTS_FIELD_NAME = "reports";

    private Class[] reportsInPriorityOrder = {
            FizzReport.class,
            BuzzReport.class,
            FizzBuzzReport.class,
            LuckyReport.class,
            IntegerReport.class
    };

    @Test
    public void shouldRulesBeInjectedInPriorityOrder() throws IllegalAccessException {

        List<Report> injectedReports = (List<Report>) readField(reportRunner, REPORTS_FIELD_NAME, true);
        assertThat(injectedReports.size(), is(reportsInPriorityOrder.length));

        for (int index = 0; index < injectedReports.size(); index++) {
            Class<? extends Report> injectedRule = injectedReports.get(index).getClass();
            Class<? extends Report> expectedRule = reportsInPriorityOrder[index];

            assertEquals(injectedRule, expectedRule);
        }
    }
}