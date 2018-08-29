package com.equalexperts.fb.rule;

import com.equalexperts.fb.rule.factory.RuleFactory;
import com.equalexperts.fb.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Component
public class RuleRunner implements Runner<List<Integer>, String> {

    @Autowired
    private RuleFactory ruleFactory;

    @Override
    public String run(List<Integer> sequence) {
        return sequence
                .stream()
                .map(number ->
                        ruleFactory.getRule(number)
                                .producesResult(number)
                ).collect(joining(SPACE));
    }
}
