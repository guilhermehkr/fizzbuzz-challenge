package com.equalexperts.fb.rule.factory;

import com.equalexperts.fb.rule.DefaultRule;
import com.equalexperts.fb.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.notEmpty;

@Repository
public class RuleFactory {

    private List<Rule> rules;

    @Autowired
    private DefaultRule defaultRule;

    public Rule getRule(final int number) {
        return rules
                .stream()
                .filter(rule -> rule.matches(number))
                .findFirst()
                .orElse(defaultRule);
    }

    @Autowired
    public void setRules(List<Rule> injectedRules) {
        notEmpty(injectedRules, "Rules not injected");
        rules = new ArrayList<>(injectedRules);
    }
}
