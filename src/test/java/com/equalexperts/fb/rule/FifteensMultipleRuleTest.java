package com.equalexperts.fb.rule;

import com.equalexperts.fb.ParameterizedBaseTest;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.rule.FifteensMultipleRule.FIFTEENS_MULTIPLE_OUTPUT;
import static com.equalexperts.fb.rule.FifteensMultipleRule.ORDER;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = FifteensMultipleRule.class)
public class FifteensMultipleRuleTest extends ParameterizedBaseTest {

    @Autowired
    private FifteensMultipleRule fifteensMultipleRule;

    private int input;
    private boolean expectedResult;

    public FifteensMultipleRuleTest(int input, boolean expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {-75, true},
                {0, false},
                {15, true},
                {29, false},
                {30, true},
                {45, true},
                {105, true},
                {121, false}
        };
        return asList(values);
    }

    @Test
    public void shouldReturnTrueWhenInputIsMultipleOfFifteenAndFalseOtherwise() {
        boolean matches = fifteensMultipleRule.matches(input);
        assertThat(matches, is(expectedResult));
    }

    @Test
    public void shouldAlwaysReturnTheExpectedFifteensMultipleOutputGivenAnyInput() {
        String result = fifteensMultipleRule.producesResult(input);
        assertThat(result, is(FIFTEENS_MULTIPLE_OUTPUT));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(fifteensMultipleRule.getOrder(), is(ORDER));
    }
}