package com.connectme.utilities;

import com.connectme.models.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FileHandler {
    private static final String DEPT_FILE = "departments.txt";
    private static final String DESIG_FILE = "designations.txt";
    private static final String EMP_FILE = "employees.txt";
    private static final String USER_FILE = "users.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Add to FileHandler.java
private static final String HR_ASSISTANTS_FILE = "hr_assistants.txt";

    // Initialize files if they don't exist
    static {
        ensureFileExists(DEPT_FILE);
        ensureFileExists(DESIG_FILE);
        ensureFileExists(EMP_FILE);
        ensureFileExists(USER_FILE);
        ensureFileExists(HR_ASSISTANTS_FILE);
    }

    private static void ensureFileExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Add default admin user if users file is created
                if (filename.equals(USER_FILE)) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
                        writer.write("admin|admin123|HR Manager");
                        writer.newLine();
                        writer.write("assistant|assistant123|HR Assistant");
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creating data file: " + filename, 
                    "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Department operations
    public static void addDepartment(Department department) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEPT_FILE, true))) {
            writer.write(department.toString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving department data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Department> readDepartments() {
        List<Department> departments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DEPT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    departments.add(new Department(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading department data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return departments;
    }

    public static void updateDepartment(Department department) {
        List<Department> departments = readDepartments();
        departments.removeIf(dept -> dept.getId().equals(department.getId()));
        departments.add(department);
        saveAllDepartments(departments);
    }

    public static void deleteDepartment(String id) {
        List<Department> departments = readDepartments();
        departments.removeIf(dept -> dept.getId().equals(id));
        saveAllDepartments(departments);
    }

    private static void saveAllDepartments(List<Department> departments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEPT_FILE))) {
            for (Department dept : departments) {
                writer.write(dept.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving department data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Designation operations (similar pattern)
    public static void addDesignation(Designation designation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DESIG_FILE, true))) {
            writer.write(designation.toString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving designation data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Designation> readDesignations() {
        List<Designation> designations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DESIG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    designations.add(new Designation(
                        parts[0], parts[1], parts[2], 
                        Double.parseDouble(parts[3]), parts[4]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error reading designation data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return designations;
    }

    public static void updateDesignation(Designation designation) {
        List<Designation> designations = readDesignations();
        designations.removeIf(desig -> desig.getId().equals(designation.getId()));
        designations.add(designation);
        saveAllDesignations(designations);
    }

    public static void deleteDesignation(String id) {
        List<Designation> designations = readDesignations();
        designations.removeIf(desig -> desig.getId().equals(id));
        saveAllDesignations(designations);
    }

    private static void saveAllDesignations(List<Designation> designations) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DESIG_FILE))) {
            for (Designation desig : designations) {
                writer.write(desig.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving designation data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Employee operations
    public static void addEmployee(Employee employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMP_FILE, true))) {
            writer.write(employee.toString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving employee data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EMP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 12) {
                    Date dob = new Date(Long.parseLong(parts[3]));
                    employees.add(new Employee(
                        parts[0], parts[1], parts[2], dob, parts[4], 
                        parts[5], parts[6], parts[7], parts[8], 
                        parts[9], parts[10], parts[11]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error reading employee data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return employees;
    }

    public static void updateEmployee(Employee employee) {
        List<Employee> employees = readEmployees();
        employees.removeIf(emp -> emp.getId().equals(employee.getId()));
        employees.add(employee);
        saveAllEmployees(employees);
    }

    public static void deleteEmployee(String id) {
        List<Employee> employees = readEmployees();
        employees.removeIf(emp -> emp.getId().equals(id));
        saveAllEmployees(employees);
    }

    private static void saveAllEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMP_FILE))) {
            for (Employee emp : employees) {
                writer.write(emp.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving employee data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // User operations
    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }

    public static void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user data", 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    




// HR Assistant operations
public static void addHRAssistant(HRAssistant assistant) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(HR_ASSISTANTS_FILE, true))) {
        writer.write(assistant.toString());
        writer.newLine();
        
        // Also add to users file for login
        addUser(new User(assistant.getUsername(), assistant.getPassword(), "HR Assistant"));
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error saving HR Assistant data", 
            "File Error", JOptionPane.ERROR_MESSAGE);
    }
}

public static List<HRAssistant> readHRAssistants() {
    List<HRAssistant> assistants = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(HR_ASSISTANTS_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 6) {
                assistants.add(new HRAssistant(
                    parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error reading HR Assistant data", 
            "File Error", JOptionPane.ERROR_MESSAGE);
    }
    return assistants;
}

public static void updateHRAssistant(HRAssistant assistant) {
    List<HRAssistant> assistants = readHRAssistants();
    assistants.removeIf(a -> a.getId().equals(assistant.getId()));
    assistants.add(assistant);
    saveAllHRAssistants(assistants);
    
    // Also update user credentials
    List<User> users = readUsers();
    users.removeIf(u -> u.getUsername().equals(assistant.getUsername()));
    users.add(new User(assistant.getUsername(), assistant.getPassword(), "HR Assistant"));
    saveAllUsers(users);
}

public static void deleteHRAssistant(String id) {
    List<HRAssistant> assistants = readHRAssistants();
    HRAssistant toDelete = assistants.stream()
        .filter(a -> a.getId().equals(id))
        .findFirst()
        .orElse(null);
    
    if (toDelete != null) {
        assistants.removeIf(a -> a.getId().equals(id));
        saveAllHRAssistants(assistants);
        
        // Also remove from users file
        List<User> users = readUsers();
        users.removeIf(u -> u.getUsername().equals(toDelete.getUsername()));
        saveAllUsers(users);
    }
}

private static void saveAllHRAssistants(List<HRAssistant> assistants) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(HR_ASSISTANTS_FILE))) {
        for (HRAssistant a : assistants) {
            writer.write(a.toString());
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error saving HR Assistant data", 
            "File Error", JOptionPane.ERROR_MESSAGE);
    }
}

private static void saveAllUsers(List<User> users) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
        for (User u : users) {
            writer.write(u.toString());
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error saving user data", 
            "File Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
}