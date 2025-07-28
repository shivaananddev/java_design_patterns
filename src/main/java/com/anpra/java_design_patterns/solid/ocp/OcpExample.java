package com.anpra.java_design_patterns.solid.ocp;

import org.springframework.stereotype.Component;

@Component
public class OcpExample {
    public String execute() {
        HouseComponent plumber = new PlumbingComponent();
        HouseComponent electrician = new ElectricalComponent();

        return plumber.install() + " | " + electrician.install();
    }
}