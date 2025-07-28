package com.anpra.java_design_patterns.patterns.structural.proxy;

/**
 * 🏗️ Analogy: Construction Site Access Control
 * Like a security checkpoint that controls access to a construction site
 */
public interface ConstructionSite {
    void enter(String person);
    void work(String person);
    void exit(String person);
}

// Real subject
public class RealConstructionSite implements ConstructionSite {
    private final String location;
    
    public RealConstructionSite(String location) {
        this.location = location;
        System.out.println("Setting up construction site at: " + location);
    }
    
    @Override
    public void enter(String person) {
        System.out.println(person + " has entered the construction site");
    }
    
    @Override
    public void work(String person) {
        System.out.println(person + " is working at the construction site");
    }
    
    @Override
    public void exit(String person) {
        System.out.println(person + " has left the construction site");
    }
}

// Protection Proxy
public class ConstructionSiteSecurityProxy implements ConstructionSite {
    private final RealConstructionSite realSite;
    private final Set<String> authorizedPersonnel;
    private final Map<String, LocalDateTime> accessLog;
    
    public ConstructionSiteSecurityProxy(String location) {
        this.realSite = new RealConstructionSite(location);
        this.authorizedPersonnel = new HashSet<>();
        this.accessLog = new HashMap<>();
    }
    
    public void addAuthorizedPerson(String person) {
        authorizedPersonnel.add(person);
    }
    
    private boolean isAuthorized(String person) {
        return authorizedPersonnel.contains(person);
    }
    
    private void logAccess(String person, String action) {
        accessLog.put(person + "-" + action, LocalDateTime.now());
    }
    
    @Override
    public void enter(String person) {
        if (!isAuthorized(person)) {
            throw new SecurityException("Unauthorized access attempt by: " + person);
        }
        
        logAccess(person, "enter");
        System.out.println("Security Check Passed: " + person);
        realSite.enter(person);
    }
    
    @Override
    public void work(String person) {
        if (!isAuthorized(person)) {
            throw new SecurityException("Unauthorized work attempt by: " + person);
        }
        
        logAccess(person, "work");
        realSite.work(person);
    }
    
    @Override
    public void exit(String person) {
        if (!isAuthorized(person)) {
            throw new SecurityException("Unauthorized exit by: " + person);
        }
        
        logAccess(person, "exit");
        realSite.exit(person);
    }
    
    // Additional security methods
    public Map<String, LocalDateTime> getAccessLog() {
        return new HashMap<>(accessLog);
    }
    
    public boolean isPersonOnSite(String person) {
        String lastAction = accessLog.keySet().stream()
            .filter(key -> key.startsWith(person + "-"))
            .max(Comparator.comparing(key -> accessLog.get(key)))
            .map(key -> key.substring(key.indexOf("-") + 1))
            .orElse("");
            
        return "enter".equals(lastAction) || "work".equals(lastAction);
    }
}
