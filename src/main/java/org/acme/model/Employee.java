package org.acme.model;

public class Employee {
    private Integer id;
    private User user;
    private User rt;
    private User manager;
    private String status;

    public Employee() {
    }

    public Employee(User collaborator, User rt, User manager) {
        this.user = collaborator;
        this.rt = rt;
        this.manager = manager;
    }

    public Employee(Integer id, User collaborator, User rt, User manager) {
        this.id = id;
        this.user = collaborator;
        this.rt = rt;
        this.manager = manager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "id=" + id +
                ", collaborator=" + user +
                ", rt=" + rt +
                ", manager=" + manager +
                '}';
    }
}
