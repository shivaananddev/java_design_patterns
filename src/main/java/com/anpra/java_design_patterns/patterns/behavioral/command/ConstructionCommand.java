package com.anpra.java_design_patterns.patterns.behavioral.command;

/**
 * 🏗️ Analogy: Construction Site Work Orders
 * Like work orders issued to different construction teams that can be executed, tracked, and undone
 */
public interface ConstructionCommand {
    void execute();
    void undo();
}

// Receiver
public class ConstructionTeam {
    private final String teamName;
    private final List<String> completedTasks = new ArrayList<>();
    
    public ConstructionTeam(String teamName) {
        this.teamName = teamName;
    }
    
    public void buildFoundation() {
        System.out.println(teamName + " building foundation");
        completedTasks.add("Foundation");
    }
    
    public void erectWalls() {
        System.out.println(teamName + " erecting walls");
        completedTasks.add("Walls");
    }
    
    public void installRoof() {
        System.out.println(teamName + " installing roof");
        completedTasks.add("Roof");
    }
    
    public void removeLastTask() {
        if (!completedTasks.isEmpty()) {
            String task = completedTasks.remove(completedTasks.size() - 1);
            System.out.println(teamName + " removed task: " + task);
        }
    }
    
    public List<String> getCompletedTasks() {
        return new ArrayList<>(completedTasks);
    }
}

// Concrete Commands
public class BuildFoundationCommand implements ConstructionCommand {
    private final ConstructionTeam team;
    
    public BuildFoundationCommand(ConstructionTeam team) {
        this.team = team;
    }
    
    @Override
    public void execute() {
        team.buildFoundation();
    }
    
    @Override
    public void undo() {
        team.removeLastTask();
    }
}

public class ErectWallsCommand implements ConstructionCommand {
    private final ConstructionTeam team;
    
    public ErectWallsCommand(ConstructionTeam team) {
        this.team = team;
    }
    
    @Override
    public void execute() {
        team.erectWalls();
    }
    
    @Override
    public void undo() {
        team.removeLastTask();
    }
}

public class InstallRoofCommand implements ConstructionCommand {
    private final ConstructionTeam team;
    
    public InstallRoofCommand(ConstructionTeam team) {
        this.team = team;
    }
    
    @Override
    public void execute() {
        team.installRoof();
    }
    
    @Override
    public void undo() {
        team.removeLastTask();
    }
}

// Invoker
public class ConstructionManager {
    private final Queue<ConstructionCommand> pendingCommands = new LinkedList<>();
    private final Stack<ConstructionCommand> completedCommands = new Stack<>();
    
    public void addCommand(ConstructionCommand command) {
        pendingCommands.offer(command);
    }
    
    public void executeNextCommand() {
        if (!pendingCommands.isEmpty()) {
            ConstructionCommand command = pendingCommands.poll();
            command.execute();
            completedCommands.push(command);
        }
    }
    
    public void executeAllCommands() {
        while (!pendingCommands.isEmpty()) {
            executeNextCommand();
        }
    }
    
    public void undoLastCommand() {
        if (!completedCommands.isEmpty()) {
            ConstructionCommand command = completedCommands.pop();
            command.undo();
        }
    }
    
    public int getPendingCommandsCount() {
        return pendingCommands.size();
    }
    
    public int getCompletedCommandsCount() {
        return completedCommands.size();
    }
}
