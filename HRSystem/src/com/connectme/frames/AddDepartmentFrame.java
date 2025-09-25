package com.connectme.frames;

import com.connectme.models.Department;
import com.connectme.utilities.FileHandler;
import com.connectme.utilities.IDGenerator;
import com.connectme.utilities.Validator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddDepartmentFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtDeptId;
    private JTextField txtName;
    private JTextField txtLocation;
    private JTextField txtContact;
    private JTextField txtDescription;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public AddDepartmentFrame() {
        setTitle("Add Department");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0x00, 0x00, 0x00)); 
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblDeptId = new JLabel("Department ID:");
        lblDeptId.setBounds(20, 20, 100, 20);
        lblDeptId.setForeground(Color.WHITE);
        contentPane.add(lblDeptId);
        
        txtDeptId = new JTextField();
        txtDeptId.setBounds(130, 20, 150, 20);
        txtDeptId.setEditable(false);
        contentPane.add(txtDeptId);
        txtDeptId.setText(IDGenerator.generateNextDepartmentId());
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 50, 100, 20);
        lblName.setForeground(Color.WHITE);
        contentPane.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(130, 50, 200, 20);
        contentPane.add(txtName);
        
        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(20, 80, 100, 20);
        lblLocation.setForeground(Color.WHITE);
        contentPane.add(lblLocation);
        
        txtLocation = new JTextField();
        txtLocation.setBounds(130, 80, 200, 20);
        contentPane.add(txtLocation);
        
        JLabel lblContact = new JLabel("Contact Number:");
        lblContact.setBounds(20, 110, 100, 20);
        lblContact.setForeground(Color.WHITE);
        contentPane.add(lblContact);
        
        txtContact = new JTextField();
        txtContact.setBounds(130, 110, 200, 20);
        contentPane.add(txtContact);
        
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(20, 140, 100, 20);
        lblDescription.setForeground(Color.WHITE);
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
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDepartment();
            }
        });
        contentPane.add(btnAdd);
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(buttonX, buttonStartY + buttonHeight + buttonSpacing, 
                         buttonWidth, buttonHeight);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editDepartment();
            }
        });
        contentPane.add(btnEdit);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(buttonX, buttonStartY + 2*(buttonHeight + buttonSpacing), 
                          buttonWidth, buttonHeight);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteDepartment();
            }
        });
        contentPane.add(btnDelete);
        
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
        String[] columnNames = {"Department ID", "Name", "Location", "Contact", "Description"};
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
        scrollPane.setBounds(20, 260, 740, 280);
        contentPane.add(scrollPane);
        
        loadDepartments();
    }
    
    private void addDepartment() {
        String id = txtDeptId.getText().trim();
        String name = txtName.getText().trim();
        String location = txtLocation.getText().trim();
        String contact = txtContact.getText().trim();
        String description = txtDescription.getText().trim();
        
        if (!Validator.validateDepartmentFields(name, location, contact)) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields with valid data", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Department department = new Department(id, name, location, contact, description);
        FileHandler.addDepartment(department);
        JOptionPane.showMessageDialog(this, "Department added successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        clearFields();
        loadDepartments();
        txtDeptId.setText(IDGenerator.generateNextDepartmentId());
    }
    
    private void editDepartment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a department to edit", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = txtName.getText().trim();
        String location = txtLocation.getText().trim();
        String contact = txtContact.getText().trim();
        String description = txtDescription.getText().trim();
        
        if (!Validator.validateDepartmentFields(name, location, contact)) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields with valid data", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Department department = new Department(id, name, location, contact, description);
        FileHandler.updateDepartment(department);
        JOptionPane.showMessageDialog(this, "Department updated successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        clearFields();
        loadDepartments();
        txtDeptId.setText(IDGenerator.generateNextDepartmentId());
    }
    
    private void deleteDepartment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a department to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this department?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            FileHandler.deleteDepartment(id);
            JOptionPane.showMessageDialog(this, "Department deleted successfully", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            loadDepartments();
            txtDeptId.setText(IDGenerator.generateNextDepartmentId());
        }
    }
    
    private void clearFields() {
        txtName.setText("");
        txtLocation.setText("");
        txtContact.setText("");
        txtDescription.setText("");
    }
    
    private void loadDepartments() {
        tableModel.setRowCount(0);
        List<Department> departments = FileHandler.readDepartments();
        for (Department dept : departments) {
            Object[] row = {
                dept.getId(),
                dept.getName(),
                dept.getLocation(),
                dept.getContactNumber(),
                dept.getDescription()
            };
            tableModel.addRow(row);
        }
    }
}