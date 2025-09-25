package com.connectme.frames;

import com.connectme.models.Department;
import com.connectme.models.Designation;
import com.connectme.utilities.FileHandler;
import com.connectme.utilities.IDGenerator;
import com.connectme.utilities.Validator;
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

public class AddDesignationFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtDesigId;
    private JTextField txtName;
    private JComboBox<String> cmbDepartment;
    private JTextField txtSalary;
    private JTextField txtDescription;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Department> departments;
    
    public AddDesignationFrame() {
        setTitle("Add Designation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0x00, 0x00, 0x00)); // #800000 background
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblDesigId = new JLabel("Designation ID:");
        lblDesigId.setBounds(20, 20, 100, 20);
        lblDesigId.setForeground(Color.WHITE); // White text
        contentPane.add(lblDesigId);
        
        txtDesigId = new JTextField();
        txtDesigId.setBounds(130, 20, 150, 20);
        txtDesigId.setEditable(false);
        contentPane.add(txtDesigId);
        txtDesigId.setText(IDGenerator.generateNextDesignationId());
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 50, 100, 20);
        lblName.setForeground(Color.WHITE); // White text
        contentPane.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(130, 50, 200, 20);
        contentPane.add(txtName);
        
        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setBounds(20, 80, 100, 20);
        lblDepartment.setForeground(Color.WHITE); // White text
        contentPane.add(lblDepartment);
        
        cmbDepartment = new JComboBox<>();
        cmbDepartment.setBounds(130, 80, 200, 20);
        contentPane.add(cmbDepartment);
        loadDepartments();
        
        JLabel lblSalary = new JLabel("Basic Salary:");
        lblSalary.setBounds(20, 110, 100, 20);
        lblSalary.setForeground(Color.WHITE); // White text
        contentPane.add(lblSalary);
        
        txtSalary = new JTextField();
        txtSalary.setBounds(130, 110, 200, 20);
        contentPane.add(txtSalary);
        
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(20, 140, 100, 20);
        lblDescription.setForeground(Color.WHITE); // White text
        contentPane.add(lblDescription);
        
        txtDescription = new JTextField();
        txtDescription.setBounds(130, 140, 300, 60);
        contentPane.add(txtDescription);
        
        // Button panel for vertical alignment in upper right corner
        int buttonWidth = 100;
        int buttonHeight = 25;
        int buttonX = getWidth() - buttonWidth - 30; // Right margin of 30px
        int buttonStartY = 20;
        int buttonSpacing = 5;
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(buttonX, buttonStartY, buttonWidth, buttonHeight);
        btnAdd.setBackground(Color.WHITE); 
        btnAdd.setForeground(Color.BLACK); 
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDesignation();
            }
        });
        contentPane.add(btnAdd);
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(buttonX, buttonStartY + buttonHeight + buttonSpacing, 
                         buttonWidth, buttonHeight);
        btnEdit.setBackground(Color.WHITE); 
        btnEdit.setForeground(Color.BLACK); 
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editDesignation();
            }
        });
        contentPane.add(btnEdit);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(buttonX, buttonStartY + 2*(buttonHeight + buttonSpacing), 
                          buttonWidth, buttonHeight);
        btnDelete.setBackground(Color.WHITE); 
        btnDelete.setForeground(Color.BLACK); 
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteDesignation();
            }
        });
        contentPane.add(btnDelete);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(buttonX, buttonStartY + 3*(buttonHeight + buttonSpacing), 
                          buttonWidth, buttonHeight);
        btnClear.setBackground(Color.WHITE); 
        btnClear.setForeground(Color.BLACK); 
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        contentPane.add(btnClear);
        
        // Table setup
        String[] columnNames = {"Designation ID", "Name", "Department", "Basic Salary", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE); // White background for table
        table.setForeground(Color.BLACK); // Black text for table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 260, 740, 280);
        contentPane.add(scrollPane);
        
        loadDesignations();
    }
    
    private void loadDepartments() {
        departments = FileHandler.readDepartments();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Department dept : departments) {
            model.addElement(dept.getId() + " - " + dept.getName());
        }
        cmbDepartment.setModel(model);
    }
    
    private void addDesignation() {
        String id = txtDesigId.getText().trim();
        String name = txtName.getText().trim();
        String department = cmbDepartment.getSelectedItem().toString().split(" - ")[0];
        String salary = txtSalary.getText().trim();
        String description = txtDescription.getText().trim();
        
        if (!Validator.validateDesignationFields(name, department, salary)) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields with valid data", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double basicSalary = Double.parseDouble(salary);
            Designation designation = new Designation(id, name, department, basicSalary, description);
            FileHandler.addDesignation(designation);
            JOptionPane.showMessageDialog(this, "Designation added successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            clearFields();
            loadDesignations();
            txtDesigId.setText(IDGenerator.generateNextDesignationId());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid salary amount", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editDesignation() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a designation to edit", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = txtName.getText().trim();
        String department = cmbDepartment.getSelectedItem().toString().split(" - ")[0];
        String salary = txtSalary.getText().trim();
        String description = txtDescription.getText().trim();
        
        if (!Validator.validateDesignationFields(name, department, salary)) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields with valid data", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double basicSalary = Double.parseDouble(salary);
            Designation designation = new Designation(id, name, department, basicSalary, description);
            FileHandler.updateDesignation(designation);
            JOptionPane.showMessageDialog(this, "Designation updated successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            clearFields();
            loadDesignations();
            txtDesigId.setText(IDGenerator.generateNextDesignationId());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid salary amount", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteDesignation() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a designation to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this designation?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            FileHandler.deleteDesignation(id);
            JOptionPane.showMessageDialog(this, "Designation deleted successfully", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            loadDesignations();
            txtDesigId.setText(IDGenerator.generateNextDesignationId());
        }
    }
    
    private void clearFields() {
        txtName.setText("");
        txtSalary.setText("");
        txtDescription.setText("");
        if (cmbDepartment.getItemCount() > 0) {
            cmbDepartment.setSelectedIndex(0);
        }
    }
    
    private void loadDesignations() {
        tableModel.setRowCount(0);
        List<Designation> designations = FileHandler.readDesignations();
        for (Designation desig : designations) {
            String departmentName = getDepartmentName(desig.getDepartmentId());
            Object[] row = {
                desig.getId(),
                desig.getName(),
                departmentName,
                desig.getBasicSalary(),
                desig.getDescription()
            };
            tableModel.addRow(row);
        }
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