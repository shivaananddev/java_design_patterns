package com.anpra.java_design_patterns.patterns.behavioral.command;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Command result containing execution details
 */
public class CommandResult {
    private final String commandId;
    private final boolean success;
    private final String message;
    private final LocalDateTime executionTime;
    private final Exception error;

    private CommandResult(Builder builder) {
        this.commandId = builder.commandId;
        this.success = builder.success;
        this.message = builder.message;
        this.executionTime = builder.executionTime;
        this.error = builder.error;
    }

    public String getCommandId() { return commandId; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public LocalDateTime getExecutionTime() { return executionTime; }
    public Exception getError() { return error; }

    public static class Builder {
        private final String commandId;
        private boolean success;
        private String message;
        private LocalDateTime executionTime;
        private Exception error;

        public Builder() {
            this.commandId = UUID.randomUUID().toString();
            this.executionTime = LocalDateTime.now();
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder error(Exception error) {
            this.error = error;
            this.success = false;
            return this;
        }

        public CommandResult build() {
            return new CommandResult(this);
        }
    }
}
