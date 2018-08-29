package com.equalexperts.fb.runner;

public interface Runner<Input, Output> {

    String SPACE = " ";

    Output run(Input input);
}
