package com.equalexperts.fb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.equalexperts.fb.service.FizzBuzz.INVALID_PARAMETER_MESSAGE;
import static com.equalexperts.fb.service.FizzBuzz.INVERTED_SEQUENCE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
@SpringBootTest
public class FizzBuzzParameterTest {

    private FizzBuzz fizzBuzz = new FizzBuzz();

    private Integer sequenceStart;
    private Integer sequenceEnd;
    private String errorMessage;

    public FizzBuzzParameterTest(Integer sequenceStart, Integer sequenceEnd, String errorMessage) {
        this.sequenceStart = sequenceStart;
        this.sequenceEnd = sequenceEnd;
        this.errorMessage = errorMessage;
    }

    @Parameters
    public static Collection<Object[]> values() {
        Object[][] values = {
                {10, 0, INVERTED_SEQUENCE},
                {null, 1, INVALID_PARAMETER_MESSAGE},
                {1, null, INVALID_PARAMETER_MESSAGE},
                {null, null, INVALID_PARAMETER_MESSAGE},
        };
        return asList(values);
    }

    @Test
    public void shouldThrowAnExceptionWhenInputParameterIsInvalid() {

        try {
            fizzBuzz.play(sequenceStart, sequenceEnd);
            fail("Should've failed, parameters are invalid");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }
}