package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Component interface defining basic building functionality
 */
public interface Building {
    String getDescription();
    BigDecimal getCost();
    int getSquareFootage();
    void addFeature(String feature);
    boolean hasFeature(String feature);
}
