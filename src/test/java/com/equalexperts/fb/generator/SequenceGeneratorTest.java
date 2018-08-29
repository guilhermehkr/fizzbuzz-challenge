package com.equalexperts.fb.generator;

import com.equalexperts.fb.ParameterizedBaseTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@SpringBootTest(classes = SequenceGenerator.class)
public class SequenceGeneratorTest extends ParameterizedBaseTest {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    private int sequenceStart;
    private int sequenceEnd;
    private List<Integer> expectedSequence;

    public SequenceGeneratorTest(int sequenceStart, int sequenceEnd, List<Integer> expectedSequence) {
        this.sequenceStart = sequenceStart;
        this.sequenceEnd = sequenceEnd;
        this.expectedSequence = expectedSequence;
    }

    @Parameters
    public static Collection<Object[]> ranges() {
        Object[][] ranges = {
                {1, 0, emptyList()},
                {0, 0, singletonList(0)},
                {1, 10, asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
                {3, 7, asList(3, 4, 5, 6, 7)},
                {14, 21, asList(14, 15, 16, 17, 18, 19, 20, 21)},
        };
        return asList(ranges);
    }

    @Test
    public void shouldGenerateASequenceGivenStartAndEndInclusive() {

        List<Integer> sequence = sequenceGenerator.generateSequence(sequenceStart, sequenceEnd);

        Assertions.assertThat(sequence)
                .hasSize(expectedSequence.size())
                .containsExactlyElementsOf(expectedSequence);
    }
}