package com.connectme.utilities;
import com.connectme.models.Department;
import com.connectme.models.Designation;
import com.connectme.models.Employee;
import com.connectme.models.HRAssistant;
//import com.connectme.utilities.FileHandler;
import java.util.List;

public class IDGenerator {
    public static String generateNextDepartmentId() {
        List<Department> departments = FileHandler.readDepartments();
        if (departments.isEmpty()) {
            return "DEP_001";
        }
        String lastId = departments.get(departments.size() - 1).getId();
        int num = Integer.parseInt(lastId.split("_")[1]) + 1;
        return String.format("DEP_%03d", num);
    }

    public static String generateNextDesignationId() {
        List<Designation> designations = FileHandler.readDesignations();
        if (designations.isEmpty()) {
            return "DESIG_001";
        }
        String lastId = designations.get(designations.size() - 1).getId();
        int num = Integer.parseInt(lastId.split("_")[1]) + 1;
        return String.format("DESIG_%03d", num);
    }

    public static String generateNextEmployeeId() {
        List<Employee> employees = FileHandler.readEmployees();
        if (employees.isEmpty()) {
            return "EMP_001";
        }
        String lastId = employees.get(employees.size() - 1).getId();
        int num = Integer.parseInt(lastId.split("_")[1]) + 1;
        return String.format("EMP_%03d", num);
    }
    
    // Add to IDGenerator.java
public static String generateNextHRAssistantId() {
    List<HRAssistant> assistants = FileHandler.readHRAssistants();
    if (assistants.isEmpty()) {
        return "HRA_001";
    }
    String lastId = assistants.get(assistants.size() - 1).getId();
    int num = Integer.parseInt(lastId.split("_")[1]) + 1;
    return String.format("HRA_%03d", num);
}
}