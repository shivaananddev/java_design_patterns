package com.anpra.java_design_patterns.patterns.behavioral.command;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Command invoker managing command execution and history
 */
public class ConstructionCommandInvoker {
    private static final Logger LOGGER = Logger.getLogger(ConstructionCommandInvoker.class.getName());
    private final Deque<ConstructionCommand> commandHistory = new LinkedList<>();
    private final Deque<ConstructionCommand> undoHistory = new LinkedList<>();
    private final Map<String, ConstructionCommand> activeCommands = new HashMap<>();

    public CommandResult executeCommand(ConstructionCommand command) {
        try {
            command.execute();
            commandHistory.push(command);
            activeCommands.put(command.getDescription(), command);
            LOGGER.info("Executed command: " + command.getDescription());
            return ((AbstractConstructionCommand) command).getLastResult();
        } catch (Exception e) {
            LOGGER.severe("Command execution failed: " + e.getMessage());
            throw e;
        }
    }

    public CompletableFuture<List<CommandResult>> executeCommandsAsync(List<ConstructionCommand> commands) {
        List<CompletableFuture<CommandResult>> futures = commands.stream()
            .map(cmd -> ((AbstractConstructionCommand) cmd).executeAsync())
            .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }

    public CommandResult undoLastCommand() {
        if (commandHistory.isEmpty()) {
            return new CommandResult.Builder()
                .success(false)
                .message("No commands to undo")
                .build();
        }

        ConstructionCommand command = commandHistory.pop();
        try {
            command.undo();
            undoHistory.push(command);
            activeCommands.remove(command.getDescription());
            LOGGER.info("Undone command: " + command.getDescription());
            return ((AbstractConstructionCommand) command).getLastResult();
        } catch (Exception e) {
            LOGGER.severe("Command undo failed: " + e.getMessage());
            throw e;
        }
    }

    public List<String> getCommandHistory() {
        return commandHistory.stream()
            .map(ConstructionCommand::getDescription)
            .collect(Collectors.toList());
    }

    public Map<String, CommandResult> getActiveCommandStatus() {
        return activeCommands.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> ((AbstractConstructionCommand) e.getValue()).getLastResult()
            ));
    }

    public void clearHistory() {
        commandHistory.clear();
        undoHistory.clear();
        activeCommands.clear();
        LOGGER.info("Command history cleared");
    }
}
