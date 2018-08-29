package com.equalexperts.fb.rule;

import com.equalexperts.fb.ParameterizedBaseTest;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = DefaultRule.class)
public class DefaultRuleTest extends ParameterizedBaseTest {

    @Autowired
    private DefaultRule defaultRule;

    private int input;
    private String expectedResult;

    public DefaultRuleTest(int input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {-20, "-20"},
                {0, "0"},
                {1, "1"},
                {4, "4"},
                {6, "6"},
                {14, "14"},
                {19, "19"},
                {28, "28"},
                {35, "35"},
                {44, "44"},
        };
        return asList(values);
    }

    @Test
    public void shouldReturnAlwaysTrueGivenAnyNumber() {
        assertThat(defaultRule.matches(input), is(true));
    }

    @Test
    public void shouldReturnTheIncomingParameterAsString() {
        String result = defaultRule.producesResult(input);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(defaultRule.getOrder(), is(Ordered.LOWEST_PRECEDENCE));
    }
}