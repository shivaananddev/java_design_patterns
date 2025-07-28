package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Plumbing system component of a construction site
 */
public class PlumbingSystem implements SiteElement {
    private final String elementId;
    private final String location;
    private final List<String> pipes;
    private double pressure;
    private boolean isLeakTested;
    private LocalDateTime lastPressureTest;

    public PlumbingSystem(String elementId, String location) {
        this.elementId = elementId;
        this.location = location;
        this.pipes = new ArrayList<>();
        this.isLeakTested = false;
    }

    public void addPipe(String pipe) {
        pipes.add(pipe);
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setLeakTested(boolean tested) {
        this.isLeakTested = tested;
    }

    public void setLastPressureTest(LocalDateTime date) {
        this.lastPressureTest = date;
    }

    public List<String> getPipes() {
        return new ArrayList<>(pipes);
    }

    public double getPressure() {
        return pressure;
    }

    public boolean isLeakTested() {
        return isLeakTested;
    }

    public LocalDateTime getLastPressureTest() {
        return lastPressureTest;
    }

    @Override
    public void accept(SiteInspectionVisitor visitor) {
        visitor.visitPlumbingSystem(this);
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getElementId() {
        return elementId;
    }
}
