package com.x.domain;

import com.x.domain.model.MetricResult;

import java.util.List;
import java.util.Optional;

/**
 * Implementors will rank metric results.  For example. finding the warmest day
 **/

public interface MetricRanking {

    List<MetricResult> rank(List<MetricResult> metrics);
}
