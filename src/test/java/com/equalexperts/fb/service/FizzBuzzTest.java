package com.equalexperts.fb.service;

import com.equalexperts.fb.generator.SequenceGenerator;
import com.equalexperts.fb.report.ReportRunner;
import com.equalexperts.fb.rule.RuleRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.equalexperts.fb.runner.Runner.SPACE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FizzBuzz.class)
public class FizzBuzzTest {

    @Autowired
    private FizzBuzz fizzBuzz;

    @MockBean
    private SequenceGenerator sequenceGenerator;

    @MockBean
    private RuleRunner ruleRunner;

    @MockBean
    private ReportRunner reportRunner;

    private static final String RULE_RESULT = "1 2 3 4";
    private static final String REPORT_RESULT = "fizz: 0 buzz: 0 fizzbuzz: 0 lucky: 0 integer: 4";

    @Test
    public void shouldPlayFizzBuzzAndAssertOutputAndMockInteractions() {

        int sequenceStart = 1, sequenceEnd = 4;

        List<Integer> sequence = asList(1, 2, 3, 4);
        given(sequenceGenerator.generateSequence(sequenceStart, sequenceEnd)).willReturn(sequence);

        given(ruleRunner.run(sequence)).willReturn(RULE_RESULT);
        given(reportRunner.run(RULE_RESULT)).willReturn(REPORT_RESULT);

        String playResult = fizzBuzz.play(sequenceStart, sequenceEnd);

        assertThat(playResult, is(RULE_RESULT + SPACE + REPORT_RESULT));

        verify(sequenceGenerator).generateSequence(sequenceStart, sequenceEnd);
        verify(ruleRunner).run(sequence);
        verify(reportRunner).run(RULE_RESULT);
    }
}