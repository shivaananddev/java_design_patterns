package com.anpra.java_design_patterns.patterns.behavioral.mediator;

/**
 * Abstract base class for site components
 */
public abstract class AbstractSiteComponent implements SiteComponent {
    protected final ConstructionMediator mediator;
    protected final String name;

    protected AbstractSiteComponent(ConstructionMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        mediator.registerComponent(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void send(String message) {
        mediator.sendMessage(name, message);
    }

    public void sendTo(String recipient, String message) {
        mediator.sendMessageTo(name, recipient, message);
    }
}
