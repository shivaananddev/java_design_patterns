package com.anpra.java_design_patterns.patterns.behavioral.command;

import java.util.concurrent.CompletableFuture;

/**
 * Base class for construction commands with common functionality
 */
public abstract class AbstractConstructionCommand implements ConstructionCommand {
    protected final String taskId;
    protected final String description;
    protected CommandResult lastResult;

    protected AbstractConstructionCommand(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    protected CommandResult createSuccessResult(String message) {
        return new CommandResult.Builder()
            .success(true)
            .message(message)
            .build();
    }

    protected CommandResult createErrorResult(Exception e) {
        return new CommandResult.Builder()
            .error(e)
            .message("Failed to execute command: " + e.getMessage())
            .build();
    }

    public CommandResult getLastResult() {
        return lastResult;
    }

    protected void setLastResult(CommandResult result) {
        this.lastResult = result;
    }

    // Template method for async execution
    public CompletableFuture<CommandResult> executeAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                execute();
                return getLastResult();
            } catch (Exception e) {
                CommandResult result = createErrorResult(e);
                setLastResult(result);
                return result;
            }
        });
    }
}
