package com.anpra.java_design_patterns.patterns.creational.singleton;

/**
 * 🏗️ Analogy: Construction Site Manager
 * There can only be one site manager for a construction project
 * Thread-safe singleton with lazy initialization
 */
public class ConstructionSiteManager {
    private static volatile ConstructionSiteManager instance;
    private final Map<String, Project> activeProjects;

    private ConstructionSiteManager() {
        activeProjects = new ConcurrentHashMap<>();
    }

    public static ConstructionSiteManager getInstance() {
        if (instance == null) {
            synchronized (ConstructionSiteManager.class) {
                if (instance == null) {
                    instance = new ConstructionSiteManager();
                }
            }
        }
        return instance;
    }

    public void addProject(String projectId, Project project) {
        activeProjects.put(projectId, project);
    }

    public Project getProject(String projectId) {
        return activeProjects.get(projectId);
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(activeProjects.values());
    }
}

public class Project {
    private final String id;
    private final String name;
    private ProjectStatus status;

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = ProjectStatus.NEW;
    }

    public void updateStatus(ProjectStatus status) {
        this.status = status;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public ProjectStatus getStatus() { return status; }
}

public enum ProjectStatus {
    NEW, IN_PROGRESS, COMPLETED, ON_HOLD
}
