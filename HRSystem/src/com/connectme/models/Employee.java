package com.connectme.models;

import java.util.Date;

public class Employee {
    private String id;
    private String name;
    private String nic;
    private Date dob;
    private String gender;
    private String maritalStatus;
    private String address;
    private String phone;
    private String email;
    private String designationId;
    private String departmentId;
    private String employeeType;

    public Employee(String id, String name, String nic, Date dob, String gender, 
                   String maritalStatus, String address, String phone, String email, 
                   String designationId, String departmentId, String employeeType) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.dob = dob;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.designationId = designationId;
        this.departmentId = departmentId;
        this.employeeType = employeeType;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getNic() { return nic; }
    public Date getDob() { return dob; }
    public String getGender() { return gender; }
    public String getMaritalStatus() { return maritalStatus; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getDesignationId() { return designationId; }
    public String getDepartmentId() { return departmentId; }
    public String getEmployeeType() { return employeeType; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setNic(String nic) { this.nic = nic; }
    public void setDob(Date dob) { this.dob = dob; }
    public void setGender(String gender) { this.gender = gender; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setDesignationId(String designationId) { this.designationId = designationId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setEmployeeType(String employeeType) { this.employeeType = employeeType; }

    @Override
    public String toString() {
        return id + "|" + name + "|" + nic + "|" + dob.getTime() + "|" + gender + "|" + 
               maritalStatus + "|" + address + "|" + phone + "|" + email + "|" + 
               designationId + "|" + departmentId + "|" + employeeType;
    }
}