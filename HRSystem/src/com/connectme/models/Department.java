package com.connectme.models;

public class Department {
    private String id;
    private String name;
    private String location;
    private String contactNumber;
    private String description;

    public Department(String id, String name, String location, String contactNumber, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
        this.description = description;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getContactNumber() { return contactNumber; }
    public String getDescription() { return description; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return id + "|" + name + "|" + location + "|" + contactNumber + "|" + description;
    }
}