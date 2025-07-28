package com.anpra.java_design_patterns.patterns.structural.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionSiteProxyTest {
    private ConstructionSiteSecurityProxy securityProxy;
    private final String authorizedPerson = "John Worker";
    private final String unauthorizedPerson = "Unknown Person";
    
    @BeforeEach
    void setUp() {
        securityProxy = new ConstructionSiteSecurityProxy("Test Site");
        securityProxy.addAuthorizedPerson(authorizedPerson);
    }
    
    @Test
    void testAuthorizedAccess() {
        assertDoesNotThrow(() -> {
            securityProxy.enter(authorizedPerson);
            securityProxy.work(authorizedPerson);
            securityProxy.exit(authorizedPerson);
        });
    }
    
    @Test
    void testUnauthorizedAccess() {
        SecurityException exception = assertThrows(SecurityException.class,
            () -> securityProxy.enter(unauthorizedPerson));
        assertEquals("Unauthorized access attempt by: " + unauthorizedPerson,
            exception.getMessage());
    }
    
    @Test
    void testAccessLog() {
        securityProxy.enter(authorizedPerson);
        securityProxy.work(authorizedPerson);
        
        Map<String, LocalDateTime> log = securityProxy.getAccessLog();
        assertTrue(log.containsKey(authorizedPerson + "-enter"));
        assertTrue(log.containsKey(authorizedPerson + "-work"));
    }
    
    @Test
    void testPersonOnSite() {
        securityProxy.enter(authorizedPerson);
        assertTrue(securityProxy.isPersonOnSite(authorizedPerson));
        
        securityProxy.exit(authorizedPerson);
        assertFalse(securityProxy.isPersonOnSite(authorizedPerson));
    }
}
