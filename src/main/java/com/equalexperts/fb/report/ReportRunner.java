package com.equalexperts.fb.report;

import com.equalexperts.fb.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.springframework.util.Assert.notEmpty;

@Component
public class ReportRunner implements Runner<String, String> {

    private List<Report> reports;

    public String run(String input) {

        return reports
                .stream()
                .map(report -> report.generateReport(input))
                .collect(joining(SPACE));
    }

    @Autowired
    public void setReports(List<Report> injectedReports) {
        notEmpty(injectedReports, "Reports not injected");
        reports = new ArrayList<>(injectedReports);
    }
}
