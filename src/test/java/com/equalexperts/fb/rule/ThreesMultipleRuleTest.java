package com.equalexperts.fb.rule;

import com.equalexperts.fb.ParameterizedBaseTest;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.rule.ThreesMultipleRule.ORDER;
import static com.equalexperts.fb.rule.ThreesMultipleRule.THREES_MULTIPLE_OUTPUT;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@SpringBootTest(classes = ThreesMultipleRule.class)
public class ThreesMultipleRuleTest extends ParameterizedBaseTest {

    @Autowired
    private ThreesMultipleRule threesMultipleRule;

    private int input;
    private boolean expectedResult;

    public ThreesMultipleRuleTest(int input, boolean expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {-3, true},
                {0, false},
                {3, true},
                {5, false},
                {9, true},
                {10, false},
                {14, false},
                {18, true},
                {20, false},
                {24, true},
                {26, false}
        };
        return asList(values);
    }

    @Test
    public void shouldReturnTrueWhenInputIsMultipleOfFiveAndFalseOtherwise() {
        assertThat(threesMultipleRule.matches(input), is(expectedResult));
    }

    @Test
    public void shouldAlwaysReturnTheExpectedFivesMultipleOutputGivenAnyInput() {
        String result = threesMultipleRule.producesResult(input);
        assertThat(result, is(THREES_MULTIPLE_OUTPUT));
    }

    @Test
    public void shouldAssertBeanOrder() {
        assertThat(threesMultipleRule.getOrder(), is(ORDER));
    }
}