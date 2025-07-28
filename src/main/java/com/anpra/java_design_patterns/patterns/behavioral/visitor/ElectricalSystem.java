package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Electrical system component of a construction site
 */
public class ElectricalSystem implements SiteElement {
    private final String elementId;
    private final String location;
    private final int voltage;
    private final List<String> circuits;
    private boolean isGrounded;
    private LocalDateTime lastMaintenanceDate;

    public ElectricalSystem(String elementId, String location, int voltage) {
        this.elementId = elementId;
        this.location = location;
        this.voltage = voltage;
        this.circuits = new ArrayList<>();
        this.isGrounded = false;
    }

    public void addCircuit(String circuit) {
        circuits.add(circuit);
    }

    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }

    public void setLastMaintenanceDate(LocalDateTime date) {
        this.lastMaintenanceDate = date;
    }

    public int getVoltage() {
        return voltage;
    }

    public List<String> getCircuits() {
        return new ArrayList<>(circuits);
    }

    public boolean isGrounded() {
        return isGrounded;
    }

    public LocalDateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    @Override
    public void accept(SiteInspectionVisitor visitor) {
        visitor.visitElectricalSystem(this);
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
