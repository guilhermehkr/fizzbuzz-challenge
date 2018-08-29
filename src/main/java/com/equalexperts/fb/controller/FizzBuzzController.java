package com.equalexperts.fb.controller;

import com.equalexperts.fb.service.FizzBuzz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FizzBuzzController {

    static final String ROOT_PATH = "/";
    public static final String SEQUENCE_START_PARAMETER_NAME = "sequenceStart";
    static final String SEQUENCE_END_PARAMETER_NAME = "sequenceEnd";

    @Autowired
    private FizzBuzz fizzBuzz;

    @GetMapping(ROOT_PATH)
    public String playFizzBuzz(@RequestParam(name = SEQUENCE_START_PARAMETER_NAME) Integer sequenceStart,
                               @RequestParam(name = SEQUENCE_END_PARAMETER_NAME) Integer sequenceEnd) {

        return fizzBuzz.play(sequenceStart, sequenceEnd);
    }
}
