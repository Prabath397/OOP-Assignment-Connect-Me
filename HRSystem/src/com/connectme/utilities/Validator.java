package com.connectme.utilities;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[0-9]{10}$");
    private static final Pattern NIC_PATTERN = 
        Pattern.compile("^[0-9]{9}[vVxX]?$");

    public static boolean validateLoginFields(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    public static boolean validateDepartmentFields(String name, String location, String contact) {
        return !name.isEmpty() && !location.isEmpty() && validatePhone(contact);
    }

    public static boolean validateDesignationFields(String name, String departmentId, String salary) {
        try {
            return !name.isEmpty() && !departmentId.isEmpty() && 
                   Double.parseDouble(salary) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateEmployeeFields(String name, String nic, String phone, 
                                               String email, String designationId, 
                                               String departmentId) {
        return !name.isEmpty() && validateNIC(nic) && validatePhone(phone) && 
               validateEmail(email) && !designationId.isEmpty() && !departmentId.isEmpty();
    }

    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validatePhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean validateNIC(String nic) {
        return NIC_PATTERN.matcher(nic).matches();
    }
    
    
    // Add to Validator.java
public static boolean validateHRAssistantFields(String name, String email, String phone, String username, String password) {
    return !name.isEmpty() && validateEmail(email) && 
           validatePhone(phone) && !username.isEmpty() && !password.isEmpty();
}
}

