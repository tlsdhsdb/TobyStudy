package com.example.toby;

import java.math.BigDecimal;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, HashMap<String, BigDecimal> rates) {

}
