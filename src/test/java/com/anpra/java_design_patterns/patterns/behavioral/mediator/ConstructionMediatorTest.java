package com.anpra.java_design_patterns.patterns.behavioral.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionMediatorTest {
    private ConstructionSiteMediator mediator;
    private ElectricalTeam electricalTeam;
    private PlumbingTeam plumbingTeam;

    @BeforeEach
    void setUp() {
        mediator = new ConstructionSiteMediator();
        electricalTeam = new ElectricalTeam();
        plumbingTeam = new PlumbingTeam();
        
        mediator.registerTeam(electricalTeam);
        mediator.registerTeam(plumbingTeam);
    }

    @Test
    void testTeamRegistration() {
        List<String> log = mediator.getCommunicationLog();
        assertTrue(log.stream().anyMatch(msg -> msg.contains("Team registered: Electrical Team")));
        assertTrue(log.stream().anyMatch(msg -> msg.contains("Team registered: Plumbing Team")));
    }

    @Test
    void testMessageCommunication() {
        mediator.sendMessage("Need access to north wall", electricalTeam);
        
        List<String> log = mediator.getCommunicationLog();
        assertTrue(log.stream().anyMatch(msg -> 
            msg.contains("Electrical Team sends message: Need access to north wall")));
    }

    @Test
    void testResourceRequest() {
        mediator.requestResource("pipes", electricalTeam);
        
        assertTrue(plumbingTeam.getResources().contains("plumbing tools"));
        List<String> log = mediator.getCommunicationLog();
        assertTrue(log.stream().anyMatch(msg -> 
            msg.contains("Electrical Team requests resource: pipes")));
    }

    @Test
    void testTaskCompletion() {
        electricalTeam.installWiring();
        
        List<String> log = mediator.getCommunicationLog();
        assertTrue(log.stream().anyMatch(msg -> 
            msg.contains("Electrical Team completed task: Wiring Installation")));
        assertTrue(electricalTeam.getCompletedTasks().contains("Wiring Installation"));
    }

    @Test
    void testMultipleTasksCompletion() {
        electricalTeam.installWiring();
        plumbingTeam.installPlumbing();
        
        assertEquals(1, electricalTeam.getCompletedTasks().size());
        assertEquals(1, plumbingTeam.getCompletedTasks().size());
        
        List<String> log = mediator.getCommunicationLog();
        assertTrue(log.stream().anyMatch(msg -> 
            msg.contains("Plumbing Installation")));
        assertTrue(log.stream().anyMatch(msg -> 
            msg.contains("Wiring Installation")));
    }

    @Test
    void testTeamResources() {
        assertTrue(electricalTeam.getResources().contains("electrical tools"));
        assertTrue(plumbingTeam.getResources().contains("plumbing tools"));
    }
}
