package com.anpra.java_design_patterns.patterns.creational.abstractfactory;

/**
 * 🏗️ Analogy: Construction Materials Factory
 * Different types of materials for different types of construction (Residential vs Commercial)
 */
public interface MaterialFactory {
    Concrete createConcrete();
    Steel createSteel();
    Glass createGlass();
}

public interface Concrete {
    String getStrength();
    void pour();
}

public interface Steel {
    String getGrade();
    void install();
}

public interface Glass {
    String getThickness();
    void fit();
}

public class ResidentialMaterialFactory implements MaterialFactory {
    @Override
    public Concrete createConcrete() {
        return new ResidentialConcrete();
    }

    @Override
    public Steel createSteel() {
        return new ResidentialSteel();
    }

    @Override
    public Glass createGlass() {
        return new ResidentialGlass();
    }
}

public class CommercialMaterialFactory implements MaterialFactory {
    @Override
    public Concrete createConcrete() {
        return new CommercialConcrete();
    }

    @Override
    public Steel createSteel() {
        return new CommercialSteel();
    }

    @Override
    public Glass createGlass() {
        return new CommercialGlass();
    }
}

// Concrete implementations for Residential
class ResidentialConcrete implements Concrete {
    @Override
    public String getStrength() {
        return "3000 PSI";
    }

    @Override
    public void pour() {
        System.out.println("Pouring residential grade concrete");
    }
}

class ResidentialSteel implements Steel {
    @Override
    public String getGrade() {
        return "Grade 40";
    }

    @Override
    public void install() {
        System.out.println("Installing residential grade steel");
    }
}

class ResidentialGlass implements Glass {
    @Override
    public String getThickness() {
        return "6mm";
    }

    @Override
    public void fit() {
        System.out.println("Fitting residential grade glass");
    }
}

// Concrete implementations for Commercial
class CommercialConcrete implements Concrete {
    @Override
    public String getStrength() {
        return "5000 PSI";
    }

    @Override
    public void pour() {
        System.out.println("Pouring commercial grade concrete");
    }
}

class CommercialSteel implements Steel {
    @Override
    public String getGrade() {
        return "Grade 60";
    }

    @Override
    public void install() {
        System.out.println("Installing commercial grade steel");
    }
}

class CommercialGlass implements Glass {
    @Override
    public String getThickness() {
        return "12mm";
    }

    @Override
    public void fit() {
        System.out.println("Fitting commercial grade glass");
    }
}
