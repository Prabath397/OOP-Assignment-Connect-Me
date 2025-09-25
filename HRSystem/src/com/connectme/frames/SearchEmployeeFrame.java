package com.connectme.frames;

import com.connectme.models.Department;
import com.connectme.models.Designation;
import com.connectme.models.Employee;
import com.connectme.utilities.FileHandler;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchEmployeeFrame extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> cmbSearchCriteria;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Department> departments;
    private List<Designation> designations;
    
    public SearchEmployeeFrame() {
        setTitle("Search Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0x00, 0x00, 0x00)); // #800000 background
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblSearchCriteria = new JLabel("Search By:");
        lblSearchCriteria.setBounds(20, 20, 100, 20);
        lblSearchCriteria.setForeground(Color.WHITE);
        contentPane.add(lblSearchCriteria);
        
        cmbSearchCriteria = new JComboBox<>(new String[]{
            "Employee ID", "Employee Name", "NIC", 
            "Department ID", "Department Name", 
            "Designation ID", "Designation Name"
        });
        cmbSearchCriteria.setBounds(130, 20, 200, 20);
        contentPane.add(cmbSearchCriteria);
        
        JLabel lblSearch = new JLabel("Search For:");
        lblSearch.setBounds(20, 50, 100, 20);
        lblSearch.setForeground(Color.WHITE);
        contentPane.add(lblSearch);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(130, 50, 200, 20);
        contentPane.add(txtSearch);
        txtSearch.setColumns(10);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(350, 50, 80, 20);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchEmployees();
            }
        });
        contentPane.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(440, 50, 80, 20);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAllEmployees();
            }
        });
        contentPane.add(btnRefresh);
        
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
        table.setBackground(Color.WHITE); // White background for table
        table.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 850, 470);
        contentPane.add(scrollPane);
        
        departments = FileHandler.readDepartments();
        designations = FileHandler.readDesignations();
        loadAllEmployees();
    }
    
    private void searchEmployees() {
        String criteria = cmbSearchCriteria.getSelectedItem().toString();
        String searchText = txtSearch.getText().trim().toLowerCase();
        
        if (searchText.isEmpty()) {
            loadAllEmployees();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Employee> employees = FileHandler.readEmployees();
        
        for (Employee emp : employees) {
            String designationName = getDesignationName(emp.getDesignationId());
            String departmentName = getDepartmentName(emp.getDepartmentId());
            
            boolean match = false;
            switch (criteria) {
                case "Employee ID":
                    match = emp.getId().toLowerCase().contains(searchText);
                    break;
                case "Employee Name":
                    match = emp.getName().toLowerCase().contains(searchText);
                    break;
                case "NIC":
                    match = emp.getNic().toLowerCase().contains(searchText);
                    break;
                case "Department ID":
                    match = emp.getDepartmentId().toLowerCase().contains(searchText);
                    break;
                case "Department Name":
                    match = departmentName.toLowerCase().contains(searchText);
                    break;
                case "Designation ID":
                    match = emp.getDesignationId().toLowerCase().contains(searchText);
                    break;
                case "Designation Name":
                    match = designationName.toLowerCase().contains(searchText);
                    break;
            }
            
            if (match) {
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
    }
    
    private void loadAllEmployees() {
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