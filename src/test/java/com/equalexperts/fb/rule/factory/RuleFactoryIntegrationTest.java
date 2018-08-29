package com.equalexperts.fb.rule.factory;

import com.equalexperts.fb.rule.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.apache.commons.lang3.reflect.FieldUtils.readField;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        RuleFactory.class,
        ContainsThreeRule.class,
        FifteensMultipleRule.class,
        FivesMultipleRule.class,
        ThreesMultipleRule.class,
        DefaultRule.class
})
public class RuleFactoryIntegrationTest {

    @Autowired
    private RuleFactory ruleFactory;

    private static final String RULES_FIELD_NAME = "rules";

    private Class[] rulesInPriorityOrder = {
            ContainsThreeRule.class,
            FifteensMultipleRule.class,
            FivesMultipleRule.class,
            ThreesMultipleRule.class,
            DefaultRule.class
    };

    @Test
    public void shouldRulesBeInjectedInPriorityOrder() throws IllegalAccessException {

        List<Rule> injectedRules = (List<Rule>) readField(ruleFactory, RULES_FIELD_NAME, true);
        assertThat(injectedRules.size(), is(rulesInPriorityOrder.length));

        for (int index = 0; index < injectedRules.size(); index++) {
            Class<? extends Rule> injectedRule = injectedRules.get(index).getClass();
            Class<? extends Rule> expectedRule = rulesInPriorityOrder[index];

            assertEquals(injectedRule, expectedRule);
        }
    }
}