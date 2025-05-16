package org.acme.model;

public class Collaborator {
    private Integer id;
    private User collaborator;
    private User rt;
    private User manager;

    public Collaborator() {
    }

    public Collaborator(User collaborator, User rt, User manager) {
        this.collaborator = collaborator;
        this.rt = rt;
        this.manager = manager;
    }

    public Collaborator(Integer id, User collaborator, User rt, User manager) {
        this.id = id;
        this.collaborator = collaborator;
        this.rt = rt;
        this.manager = manager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(User collaborator) {
        this.collaborator = collaborator;
    }

    public User getRt() {
        return rt;
    }

    public void setRt(User rt) {
        this.rt = rt;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "id=" + id +
                ", collaborator=" + collaborator +
                ", rt=" + rt +
                ", manager=" + manager +
                '}';
    }
}
