package com.equalexperts.fb.rule;

import com.equalexperts.fb.ParameterizedBaseTest;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.rule.FivesMultipleRule.FIVES_MULTIPLE_OUTPUT;
import static com.equalexperts.fb.rule.FivesMultipleRule.ORDER;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = FivesMultipleRule.class)
public class FivesMultipleRuleTest extends ParameterizedBaseTest {

    @Autowired
    private FivesMultipleRule fivesMultipleRule;

    private int input;
    private boolean expectedResult;

    public FivesMultipleRuleTest(int input, boolean expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {-5, true},
                {0, false},
                {3, false},
                {5, true},
                {9, false},
                {10, true},
                {14, false},
                {20, true},
                {25, true},
                {26, false}
        };
        return asList(values);
    }

    @Test
    public void shouldReturnTrueWhenInputIsMultipleOfFiveAndFalseOtherwise() {
        assertThat(fivesMultipleRule.matches(input), is(expectedResult));
    }

    @Test
    public void shouldAlwaysReturnTheExpectedFivesMultipleOutputGivenAnyInput() {
        String result = fivesMultipleRule.producesResult(input);
        assertThat(result, is(FIVES_MULTIPLE_OUTPUT));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(fivesMultipleRule.getOrder(), is(ORDER));
    }
}