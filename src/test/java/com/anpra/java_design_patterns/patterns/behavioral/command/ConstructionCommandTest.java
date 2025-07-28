package com.anpra.java_design_patterns.patterns.behavioral.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionCommandTest {
    private ConstructionManager manager;
    private ConstructionTeam team;
    
    @BeforeEach
    void setUp() {
        manager = new ConstructionManager();
        team = new ConstructionTeam("Test Team");
    }
    
    @Test
    void testCommandExecution() {
        manager.addCommand(new BuildFoundationCommand(team));
        manager.addCommand(new ErectWallsCommand(team));
        
        manager.executeNextCommand();
        assertEquals(1, team.getCompletedTasks().size());
        assertEquals("Foundation", team.getCompletedTasks().get(0));
        
        manager.executeNextCommand();
        assertEquals(2, team.getCompletedTasks().size());
        assertEquals("Walls", team.getCompletedTasks().get(1));
    }
    
    @Test
    void testUndoCommand() {
        manager.addCommand(new BuildFoundationCommand(team));
        manager.executeNextCommand();
        
        assertEquals(1, team.getCompletedTasks().size());
        
        manager.undoLastCommand();
        assertTrue(team.getCompletedTasks().isEmpty());
    }
    
    @Test
    void testExecuteAllCommands() {
        manager.addCommand(new BuildFoundationCommand(team));
        manager.addCommand(new ErectWallsCommand(team));
        manager.addCommand(new InstallRoofCommand(team));
        
        manager.executeAllCommands();
        
        assertEquals(3, team.getCompletedTasks().size());
        assertEquals(0, manager.getPendingCommandsCount());
        assertEquals(3, manager.getCompletedCommandsCount());
    }
    
    @Test
    void testCommandSequence() {
        manager.addCommand(new BuildFoundationCommand(team));
        manager.addCommand(new ErectWallsCommand(team));
        manager.addCommand(new InstallRoofCommand(team));
        
        manager.executeAllCommands();
        
        List<String> tasks = team.getCompletedTasks();
        assertEquals("Foundation", tasks.get(0));
        assertEquals("Walls", tasks.get(1));
        assertEquals("Roof", tasks.get(2));
    }
}
