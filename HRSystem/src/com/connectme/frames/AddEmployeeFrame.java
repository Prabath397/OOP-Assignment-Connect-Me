package com.connectme.frames;

import com.connectme.models.Department;
import com.connectme.models.Designation;
import com.connectme.models.Employee;
import com.connectme.utilities.FileHandler;
import com.connectme.utilities.IDGenerator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

public class AddEmployeeFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtEmpId;
    private JTextField txtName;
    private JTextField txtNIC;
    private JDateChooser dateChooser;
    private JComboBox<String> cmbGender;
    private ButtonGroup maritalStatusGroup;
    private JRadioButton rdMarried;
    private JRadioButton rdSingle;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JComboBox<String> cmbDesignation;
    private JComboBox<String> cmbDepartment;
    private JComboBox<String> cmbEmpType;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Department> departments;
    private List<Designation> designations;
    
    public AddEmployeeFrame() {
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0x80, 0x00, 0x00)); // Maroon background
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Employee ID
        JLabel lblEmpId = new JLabel("Employee ID:");
        lblEmpId.setBounds(20, 20, 100, 20);
        lblEmpId.setForeground(Color.WHITE);
        contentPane.add(lblEmpId);
        
        txtEmpId = new JTextField();
        txtEmpId.setBounds(130, 20, 150, 20);
        txtEmpId.setEditable(false);
        contentPane.add(txtEmpId);
        txtEmpId.setText(IDGenerator.generateNextEmployeeId());
        
        // Name
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(20, 50, 100, 20);
        lblName.setForeground(Color.WHITE);
        contentPane.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(130, 50, 200, 20);
        contentPane.add(txtName);
        
        // NIC
        JLabel lblNIC = new JLabel("NIC:");
        lblNIC.setBounds(20, 80, 100, 20);
        lblNIC.setForeground(Color.WHITE);
        contentPane.add(lblNIC);
        
        txtNIC = new JTextField();
        txtNIC.setBounds(130, 80, 200, 20);
        contentPane.add(txtNIC);
        
        // Date of Birth
        JLabel lblDOB = new JLabel("Date of Birth:");
        lblDOB.setBounds(20, 110, 100, 20);
        lblDOB.setForeground(Color.WHITE);
        contentPane.add(lblDOB);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(130, 110, 200, 20);
        contentPane.add(dateChooser);
        
        // Gender
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(20, 140, 100, 20);
        lblGender.setForeground(Color.WHITE);
        contentPane.add(lblGender);
        
        cmbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        cmbGender.setBounds(130, 140, 200, 20);
        contentPane.add(cmbGender);
        
        // Marital Status
        JLabel lblMaritalStatus = new JLabel("Marital Status:");
        lblMaritalStatus.setBounds(20, 170, 100, 20);
        lblMaritalStatus.setForeground(Color.WHITE);
        contentPane.add(lblMaritalStatus);
        
        rdMarried = new JRadioButton("Married");
        rdMarried.setBounds(130, 170, 80, 20);
        contentPane.add(rdMarried);
        
        rdSingle = new JRadioButton("Single");
        rdSingle.setBounds(220, 170, 80, 20);
        contentPane.add(rdSingle);
        
        maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(rdMarried);
        maritalStatusGroup.add(rdSingle);
        rdSingle.setSelected(true);
        
        // Address
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(20, 200, 100, 20);
        lblAddress.setForeground(Color.WHITE);
        contentPane.add(lblAddress);
        
        txtAddress = new JTextField();
        txtAddress.setBounds(130, 200, 300, 60);
        contentPane.add(txtAddress);
        
        // Phone
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(20, 270, 100, 20);
        lblPhone.setForeground(Color.WHITE);
        contentPane.add(lblPhone);
        
        txtPhone = new JTextField();
        txtPhone.setBounds(130, 270, 200, 20);
        contentPane.add(txtPhone);
        
        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 300, 100, 20);
        lblEmail.setForeground(Color.WHITE);
        contentPane.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(130, 300, 200, 20);
        contentPane.add(txtEmail);
        
        // Designation
        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setBounds(20, 330, 100, 20);
        lblDesignation.setForeground(Color.WHITE);
        contentPane.add(lblDesignation);
        
        cmbDesignation = new JComboBox<>();
        cmbDesignation.setBounds(130, 330, 200, 20);
        contentPane.add(cmbDesignation);
        
        // Department
        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setBounds(20, 360, 100, 20);
        lblDepartment.setForeground(Color.WHITE);
        contentPane.add(lblDepartment);
        
        cmbDepartment = new JComboBox<>();
        cmbDepartment.setBounds(130, 360, 200, 20);
        contentPane.add(cmbDepartment);
        
        // Employee Type
        JLabel lblEmpType = new JLabel("Employee Type:");
        lblEmpType.setBounds(20, 390, 100, 20);
        lblEmpType.setForeground(Color.WHITE);
        contentPane.add(lblEmpType);
        
        cmbEmpType = new JComboBox<>(new String[]{"Permanent", "Intern"});
        cmbEmpType.setBounds(130, 390, 200, 20);
        contentPane.add(cmbEmpType);
        
        loadDepartmentsAndDesignations();
        
        // Buttons
        int buttonWidth = 100;
        int buttonHeight = 25;
        int buttonX = getWidth() - buttonWidth - 30;
        int buttonStartY = 20;
        int buttonSpacing = 5;
        
        // Add Button
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(buttonX, buttonStartY, buttonWidth, buttonHeight);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        contentPane.add(btnAdd);
        
        // Edit Button
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(buttonX, buttonStartY + buttonHeight + buttonSpacing, 
                         buttonWidth, buttonHeight);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editEmployee();
            }
        });
        contentPane.add(btnEdit);
        
        // Delete Button
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(buttonX, buttonStartY + 2*(buttonHeight + buttonSpacing), 
                          buttonWidth, buttonHeight);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
        contentPane.add(btnDelete);
        
        // Clear Button
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(buttonX, buttonStartY + 3*(buttonHeight + buttonSpacing), 
                          buttonWidth, buttonHeight);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        contentPane.add(btnClear);
        
        // Table setup
        String[] columnNames = {"Employee ID", "Name", "NIC", "DOB", "Gender", 
                              "Marital Status", "Phone", "Email", "Designation", 
                              "Department", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 470, 850, 180);
        contentPane.add(scrollPane);
        
        loadEmployees();
    }
    
    private void loadDepartmentsAndDesignations() {
        departments = FileHandler.readDepartments();
        designations = FileHandler.readDesignations();
        
        DefaultComboBoxModel<String> deptModel = new DefaultComboBoxModel<>();
        for (Department dept : departments) {
            deptModel.addElement(dept.getId() + " - " + dept.getName());
        }
        cmbDepartment.setModel(deptModel);
        
        DefaultComboBoxModel<String> desigModel = new DefaultComboBoxModel<>();
        for (Designation desig : designations) {
            desigModel.addElement(desig.getId() + " - " + desig.getName());
        }
        cmbDesignation.setModel(desigModel);
    }
    
    private void addEmployee() {
        String id = txtEmpId.getText().trim();
        String name = txtName.getText().trim();
        String nic = txtNIC.getText().trim();
        Date dob = dateChooser.getDate();
        String gender = (String) cmbGender.getSelectedItem();
        String maritalStatus = rdMarried.isSelected() ? "Married" : "Single";
        String address = txtAddress.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (cmbDesignation.getSelectedItem() == null || cmbDepartment.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select both designation and department", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String designation = cmbDesignation.getSelectedItem().toString().split(" - ")[0];
        String department = cmbDepartment.getSelectedItem().toString().split(" - ")[0];
        String empType = (String) cmbEmpType.getSelectedItem();

        // Validate required fields
        if (name.isEmpty() || nic.isEmpty() || phone.isEmpty() || email.isEmpty() || 
            address.isEmpty() || dob == null) {
            JOptionPane.showMessageDialog(this, 
                "Please fill all required fields (Name, NIC, Phone, Email, Address, DOB)", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate field formats
        if (!nic.matches("^[0-9]+[vVxX]?$")) {
            JOptionPane.showMessageDialog(this, "Invalid NIC format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phone.matches("^[0-9]{10}$")) {
            JOptionPane.showMessageDialog(this, "Invalid phone number (10 digits required)", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(id, name, nic, dob, gender, maritalStatus, 
                                      address, phone, email, designation, department, empType);
        
        FileHandler.addEmployee(employee);
        JOptionPane.showMessageDialog(this, "Employee added successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        clearFields();
        loadEmployees();
        txtEmpId.setText(IDGenerator.generateNextEmployeeId());
    }
    
    private void editEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = txtName.getText().trim();
        String nic = txtNIC.getText().trim();
        Date dob = dateChooser.getDate();
        String gender = (String) cmbGender.getSelectedItem();
        String maritalStatus = rdMarried.isSelected() ? "Married" : "Single";
        String address = txtAddress.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (cmbDesignation.getSelectedItem() == null || cmbDepartment.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select both designation and department", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String designation = cmbDesignation.getSelectedItem().toString().split(" - ")[0];
        String department = cmbDepartment.getSelectedItem().toString().split(" - ")[0];
        String empType = (String) cmbEmpType.getSelectedItem();

        if (name.isEmpty() || nic.isEmpty() || phone.isEmpty() || email.isEmpty() || 
            address.isEmpty() || dob == null) {
            JOptionPane.showMessageDialog(this, 
                "Please fill all required fields (Name, NIC, Phone, Email, Address, DOB)", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(id, name, nic, dob, gender, maritalStatus, 
                                      address, phone, email, designation, department, empType);
        FileHandler.updateEmployee(employee);
        JOptionPane.showMessageDialog(this, "Employee updated successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        clearFields();
        loadEmployees();
        txtEmpId.setText(IDGenerator.generateNextEmployeeId());
    }
    
    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this employee?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            FileHandler.deleteEmployee(id);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployees();
            txtEmpId.setText(IDGenerator.generateNextEmployeeId());
        }
    }
    
    private void clearFields() {
        txtName.setText("");
        txtNIC.setText("");
        dateChooser.setDate(null);
        cmbGender.setSelectedIndex(0);
        rdSingle.setSelected(true);
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        if (cmbDesignation.getItemCount() > 0) {
            cmbDesignation.setSelectedIndex(0);
        }
        if (cmbDepartment.getItemCount() > 0) {
            cmbDepartment.setSelectedIndex(0);
        }
        cmbEmpType.setSelectedIndex(0);
    }
    
    private void loadEmployees() {
        tableModel.setRowCount(0);
        List<Employee> employees = FileHandler.readEmployees();
        for (Employee emp : employees) {
            String designationName = getDesignationName(emp.getDesignationId());
            String departmentName = getDepartmentName(emp.getDepartmentId());
            Object[] row = {
                emp.getId(),
                emp.getName(),
                emp.getNic(),
                emp.getDob(),
                emp.getGender(),
                emp.getMaritalStatus(),
                emp.getPhone(),
                emp.getEmail(),
                designationName,
                departmentName,
                emp.getEmployeeType()
            };
            tableModel.addRow(row);
        }
    }
    
    private String getDesignationName(String designationId) {
        for (Designation desig : designations) {
            if (desig.getId().equals(designationId)) {
                return desig.getName();
            }
        }
        return "Unknown";
    }
    
    private String getDepartmentName(String departmentId) {
        for (Department dept : departments) {
            if (dept.getId().equals(departmentId)) {
                return dept.getName();
            }
        }
        return "Unknown";
    }
}