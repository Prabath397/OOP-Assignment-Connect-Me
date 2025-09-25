package com.connectme.models;

public class Designation {
    private String id;
    private String name;
    private String departmentId;
    private double basicSalary;
    private String description;

    public Designation(String id, String name, String departmentId, double basicSalary, String description) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.basicSalary = basicSalary;
        this.description = description;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartmentId() { return departmentId; }
    public double getBasicSalary() { return basicSalary; }
    public String getDescription() { return description; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return id + "|" + name + "|" + departmentId + "|" + basicSalary + "|" + description;
    }
}