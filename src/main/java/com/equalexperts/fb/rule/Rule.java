package com.equalexperts.fb.rule;

import org.springframework.core.Ordered;

public interface Rule extends Ordered {

    boolean matches(int number);

    String producesResult(int number);
}
