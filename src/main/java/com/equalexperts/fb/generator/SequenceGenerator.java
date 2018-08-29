package com.equalexperts.fb.generator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
public class SequenceGenerator {

    public List<Integer> generateSequence(int sequenceStartInclusive, int sequenceEndInclusive) {
        return IntStream.rangeClosed(sequenceStartInclusive, sequenceEndInclusive)
                .boxed()
                .collect(toList());
    }
}
