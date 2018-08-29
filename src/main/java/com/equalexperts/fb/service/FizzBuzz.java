package com.equalexperts.fb.service;

import com.equalexperts.fb.exception.IllegalParameterException;
import com.equalexperts.fb.generator.SequenceGenerator;
import com.equalexperts.fb.report.ReportRunner;
import com.equalexperts.fb.rule.RuleRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.equalexperts.fb.runner.Runner.SPACE;

@Service
public class FizzBuzz {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private RuleRunner ruleRunner;

    @Autowired
    private ReportRunner reportRunner;

    static final String INVALID_PARAMETER_MESSAGE = "Sequence limits are required, please provide";
    static final String INVERTED_SEQUENCE = "Sequence limits are inverted, please provide numbers in ascending order";

    public String play(Integer sequenceStart, Integer sequenceEnd) {
        validateInputParameters(sequenceStart, sequenceEnd);

        List<Integer> sequence = sequenceGenerator.generateSequence(sequenceStart, sequenceEnd);
        String result = ruleRunner.run(sequence);
        String reports = reportRunner.run(result);

        return result + SPACE + reports;
    }

    private void validateInputParameters(Integer sequenceStart, Integer sequenceEnd) {
        if (sequenceStart == null || sequenceEnd == null) {
            throw new IllegalParameterException(INVALID_PARAMETER_MESSAGE);
        }

        if (sequenceStart > sequenceEnd) {
            throw new IllegalParameterException(INVERTED_SEQUENCE);
        }
    }
}
