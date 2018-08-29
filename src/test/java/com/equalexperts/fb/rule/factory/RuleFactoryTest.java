package com.equalexperts.fb.rule.factory;

import com.equalexperts.fb.rule.DefaultRule;
import com.equalexperts.fb.rule.Rule;
import com.equalexperts.fb.rule.ThreesMultipleRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.equalexperts.fb.rule.ThreesMultipleRule.THREES_MULTIPLE_OUTPUT;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuleFactory.class)
public class RuleFactoryTest {

    @Autowired
    private RuleFactory ruleFactory;

    @MockBean
    private DefaultRule defaultRule;

    @MockBean
    private ThreesMultipleRule threesMultipleRule;

    private static final String DUMMY = "dummy";

    @Before
    public void setup() {
        ruleFactory.setRules(asList(threesMultipleRule, new DummyRule(), defaultRule));
    }

    @Test
    public void shouldGetDummyRule() {
        int input = 0;

        given(threesMultipleRule.matches(input)).willReturn(false);
        given(defaultRule.matches(input)).willReturn(true);

        Rule rule = ruleFactory.getRule(input);

        assertThat(rule.producesResult(input), is(DUMMY));
        verify(defaultRule, never()).matches(input);
    }

    @Test
    public void shouldGetThreesMultipleRule() {
        int input = 3;

        given(threesMultipleRule.matches(input)).willReturn(true);
        given(threesMultipleRule.producesResult(input)).willReturn(THREES_MULTIPLE_OUTPUT);

        Rule rule = ruleFactory.getRule(input);

        assertThat(rule.producesResult(input), is(THREES_MULTIPLE_OUTPUT));
        verify(defaultRule, never()).matches(input);
    }

    @Test
    public void shouldGetDefaultRuleWhenNoneRuleMatches() {
        int input = 2;
        String result = "2";

        given(threesMultipleRule.matches(input)).willReturn(false);
        given(defaultRule.matches(input)).willReturn(false);

        given(defaultRule.producesResult(input)).willReturn(result);

        Rule rule = ruleFactory.getRule(input);

        assertThat(rule.producesResult(input), is(result));
        verify(threesMultipleRule).matches(input);
        verify(defaultRule).matches(input);
    }


    private class DummyRule implements Rule {

        @Override
        public boolean matches(int value) {
            return value == 0;
        }

        @Override
        public String producesResult(int value) {
            return DUMMY;
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }
}