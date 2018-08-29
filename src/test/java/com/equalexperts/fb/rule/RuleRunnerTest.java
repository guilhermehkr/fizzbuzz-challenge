package com.equalexperts.fb.rule;

import com.equalexperts.fb.rule.factory.RuleFactory;
import com.equalexperts.fb.runner.Runner;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.equalexperts.fb.runner.Runner.SPACE;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuleRunner.class)
public class RuleRunnerTest {

    @Autowired
    private RuleRunner ruleRunner;

    @MockBean
    private RuleFactory ruleFactory;

    @MockBean
    private DefaultRule defaultRule;

    @Test
    public void shouldRunAllAvailableRulesAndAssertResult() {

        List<Integer> sequence = asList(1, 2, 3, 4);

        sequence.forEach(number -> {
            given(ruleFactory.getRule(number)).willReturn(defaultRule);
            given(defaultRule.producesResult(number)).willCallRealMethod();

        });

        String expectedResult = sequence
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SPACE));

        String result = ruleRunner.run(sequence);
        assertThat(result, is(expectedResult));

        sequence.forEach(number -> {
            verify(ruleFactory).getRule(number);
            verify(defaultRule).producesResult(number);
        });
    }
}