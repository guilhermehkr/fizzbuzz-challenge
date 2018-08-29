package com.equalexperts.fb.rule;

import com.equalexperts.fb.ParameterizedBaseTest;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;

import java.util.Collection;

import static com.equalexperts.fb.rule.ContainsThreeRule.CONTAINS_THREE_OUTPUT;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = ContainsThreeRule.class)
public class ContainsThreeRuleTest extends ParameterizedBaseTest {

    @Autowired
    private ContainsThreeRule containsThreeRule;

    private int input;
    private boolean expectedResult;

    public ContainsThreeRuleTest(int input, boolean expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {-3, true},
                {3, true},
                {4, false},
                {5, false},
                {6, false},
                {9, false},
                {13, true},
                {23, true},
                {33, true},

        };
        return asList(values);
    }

    @Test
    public void shouldReturnTrueWhenInputContainsNumberThreeAndFalseOtherwise() {
        assertThat(containsThreeRule.matches(input), is(expectedResult));
    }

    @Test
    public void shouldAlwaysReturnTheExpectedContainsThreeOutputGivenAnyInput() {
        String result = containsThreeRule.producesResult(input);
        assertThat(result, is(CONTAINS_THREE_OUTPUT));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(containsThreeRule.getOrder(), is(Ordered.HIGHEST_PRECEDENCE));
    }
}