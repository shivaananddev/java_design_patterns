package com.anpra.java_design_patterns.patterns.structural.adapter;

/**
 * 🏗️ Analogy: International Power Tools Adapter
 * Like using American power tools on European construction sites using a power adapter
 */
public interface ModernPowerTool {
    void useModernTool();
    boolean isCharged();
}

// Legacy system (like American 110V tool)
public class LegacyPowerTool {
    private final String voltage;
    
    public LegacyPowerTool() {
        this.voltage = "110V";
    }
    
    public void plugInAndUse() {
        System.out.println("Using legacy " + voltage + " power tool");
    }
    
    public boolean checkPower() {
        return true;
    }
}

// Modern system (like European 220V tool)
public class ModernConstructionTool implements ModernPowerTool {
    private final String voltage;
    
    public ModernConstructionTool() {
        this.voltage = "220V";
    }
    
    @Override
    public void useModernTool() {
        System.out.println("Using modern " + voltage + " construction tool");
    }
    
    @Override
    public boolean isCharged() {
        return true;
    }
}

// Adapter to make legacy tools work with modern system
public class PowerToolAdapter implements ModernPowerTool {
    private final LegacyPowerTool legacyTool;
    
    public PowerToolAdapter(LegacyPowerTool legacyTool) {
        this.legacyTool = legacyTool;
    }
    
    @Override
    public void useModernTool() {
        System.out.println("Adapting voltage...");
        legacyTool.plugInAndUse();
    }
    
    @Override
    public boolean isCharged() {
        return legacyTool.checkPower();
    }
}
