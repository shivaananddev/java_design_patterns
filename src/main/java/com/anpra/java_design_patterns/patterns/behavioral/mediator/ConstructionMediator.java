package com.anpra.java_design_patterns.patterns.behavioral.mediator;

import java.util.*;

/**
 * 🏗️ Analogy: Construction Site Coordination Center
 * Like a central coordination office that manages communication between different construction teams
 */
public interface ConstructionMediator {
    void registerTeam(ConstructionTeam team);
    void sendMessage(String message, ConstructionTeam sender);
    void requestResource(String resource, ConstructionTeam requester);
    void completeTask(String task, ConstructionTeam team);
}

public abstract class ConstructionTeam {
    protected ConstructionMediator mediator;
    protected String teamName;
    protected Set<String> resources;
    protected List<String> completedTasks;

    public ConstructionTeam(String teamName) {
        this.teamName = teamName;
        this.resources = new HashSet<>();
        this.completedTasks = new ArrayList<>();
    }

    public void setMediator(ConstructionMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receiveMessage(String message);
    public abstract void receiveResource(String resource);
    
    public String getTeamName() {
        return teamName;
    }

    public Set<String> getResources() {
        return new HashSet<>(resources);
    }

    public List<String> getCompletedTasks() {
        return new ArrayList<>(completedTasks);
    }
}

public class ConstructionSiteMediator implements ConstructionMediator {
    private final Map<String, ConstructionTeam> teams;
    private final List<String> communicationLog;

    public ConstructionSiteMediator() {
        this.teams = new HashMap<>();
        this.communicationLog = new ArrayList<>();
    }

    @Override
    public void registerTeam(ConstructionTeam team) {
        teams.put(team.getTeamName(), team);
        team.setMediator(this);
        logCommunication("Team registered: " + team.getTeamName());
    }

    @Override
    public void sendMessage(String message, ConstructionTeam sender) {
        logCommunication(sender.getTeamName() + " sends message: " + message);
        teams.values().stream()
            .filter(team -> !team.equals(sender))
            .forEach(team -> team.receiveMessage(
                "[From " + sender.getTeamName() + "]: " + message));
    }

    @Override
    public void requestResource(String resource, ConstructionTeam requester) {
        logCommunication(requester.getTeamName() + " requests resource: " + resource);
        teams.values().stream()
            .filter(team -> team.getResources().contains(resource))
            .findFirst()
            .ifPresent(team -> {
                team.getResources().remove(resource);
                requester.receiveResource(resource);
                logCommunication("Resource " + resource + " transferred from " + 
                               team.getTeamName() + " to " + requester.getTeamName());
            });
    }

    @Override
    public void completeTask(String task, ConstructionTeam team) {
        team.completedTasks.add(task);
        logCommunication(team.getTeamName() + " completed task: " + task);
        notifyTeamsOfCompletion(task, team);
    }

    private void notifyTeamsOfCompletion(String task, ConstructionTeam completingTeam) {
        teams.values().stream()
            .filter(team -> !team.equals(completingTeam))
            .forEach(team -> team.receiveMessage(
                completingTeam.getTeamName() + " has completed: " + task));
    }

    private void logCommunication(String message) {
        communicationLog.add(message);
    }

    public List<String> getCommunicationLog() {
        return new ArrayList<>(communicationLog);
    }
}

public class ElectricalTeam extends ConstructionTeam {
    public ElectricalTeam() {
        super("Electrical Team");
        resources.add("wire");
        resources.add("electrical tools");
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("Electrical Team received: " + message);
    }

    @Override
    public void receiveResource(String resource) {
        resources.add(resource);
        System.out.println("Electrical Team received resource: " + resource);
    }

    public void installWiring() {
        mediator.completeTask("Wiring Installation", this);
    }
}

public class PlumbingTeam extends ConstructionTeam {
    public PlumbingTeam() {
        super("Plumbing Team");
        resources.add("pipes");
        resources.add("plumbing tools");
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("Plumbing Team received: " + message);
    }

    @Override
    public void receiveResource(String resource) {
        resources.add(resource);
        System.out.println("Plumbing Team received resource: " + resource);
    }

    public void installPlumbing() {
        mediator.completeTask("Plumbing Installation", this);
    }
}
