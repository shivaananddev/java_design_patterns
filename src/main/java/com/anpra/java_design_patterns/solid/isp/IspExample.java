package com.anpra.java_design_patterns.solid.isp;

import org.springframework.stereotype.Component;

@Component
public class IspExample {
    public String execute() {
        Painter painter = new HousePainter();
        return painter.paint();
    }
}