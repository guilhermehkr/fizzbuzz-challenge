package com.equalexperts.fb.service;

import com.equalexperts.fb.ParameterizedBaseTest;
import com.equalexperts.fb.generator.SequenceGenerator;
import com.equalexperts.fb.report.*;
import com.equalexperts.fb.rule.*;
import com.equalexperts.fb.rule.factory.RuleFactory;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = {
        FizzBuzz.class,
        SequenceGenerator.class,
        RuleRunner.class,
        RuleFactory.class,
        ContainsThreeRule.class,
        FifteensMultipleRule.class,
        FivesMultipleRule.class,
        ThreesMultipleRule.class,
        DefaultRule.class,
        ReportRunner.class,
        FizzReport.class,
        BuzzReport.class,
        FizzBuzzReport.class,
        LuckyReport.class,
        IntegerReport.class
})
public class FizzBuzzIntegrationTest extends ParameterizedBaseTest {

    @Autowired
    private FizzBuzz fizzBuzz;

    private int sequenceStart;
    private int sequenceEnd;
    private String expectedOutput;

    public FizzBuzzIntegrationTest(int sequenceStart, int sequenceEnd, String expectedOutput) {
        this.sequenceStart = sequenceStart;
        this.sequenceEnd = sequenceEnd;
        this.expectedOutput = expectedOutput;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {0, 0, "0 fizz: 0 buzz: 0 fizzbuzz: 0 lucky: 0 integer: 1"},
                {1, 20, "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10"},
                {36, 45, "lucky lucky lucky lucky buzz 41 fizz lucky 44 fizzbuzz fizz: 1 buzz: 1 fizzbuzz: 1 lucky: 5 integer: 2"},
                {-10, 0, "buzz fizz -8 -7 fizz buzz -4 lucky -2 -1 0 fizz: 2 buzz: 2 fizzbuzz: 0 lucky: 1 integer: 6"},
                {50, 60, "buzz fizz 52 lucky fizz buzz 56 fizz 58 59 fizzbuzz fizz: 3 buzz: 2 fizzbuzz: 1 lucky: 1 integer: 4"},
                {29, 40, "29 lucky lucky lucky lucky lucky lucky lucky lucky lucky lucky buzz fizz: 0 buzz: 1 fizzbuzz: 0 lucky: 10 integer: 1"}

        };
        return asList(values);
    }

    @Test
    public void shouldPlayAndAssertOutputGivenRange() {

        String output = fizzBuzz.play(sequenceStart, sequenceEnd);
        assertThat(output, is(expectedOutput));
    }
}