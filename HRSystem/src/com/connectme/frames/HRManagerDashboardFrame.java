package com.connectme.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class HRManagerDashboardFrame extends HRDashboardBase {
    private JButton btnAddDepartment;
    private JButton btnAddDesignation;
    private JButton btnAddEmployee;
    private JButton btnManageAssistants; 

    public HRManagerDashboardFrame() {
        setTitle("Connect Me - HR Manager Dashboard");
        
        // Existing buttons
        btnAddDepartment = new JButton("Add New Department");
        btnAddDepartment.setBounds(50, 150, 200, 30);
        btnAddDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddDepartment();
            }
        });
        contentPane.add(btnAddDepartment);
        
        btnAddDesignation = new JButton("Add New Designation");
        btnAddDesignation.setBounds(50, 200, 200, 30);
        btnAddDesignation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddDesignation();
            }
        });
        contentPane.add(btnAddDesignation);
        
        btnAddEmployee = new JButton("Add New Employee");
        btnAddEmployee.setBounds(50, 250, 200, 30);
        btnAddEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddEmployee();
            }
        });
        contentPane.add(btnAddEmployee);
        
      
        btnManageAssistants = new JButton("Manage HR Assistants");
        btnManageAssistants.setBounds(50, 300, 200, 30);
        btnManageAssistants.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageHRAssistantsFrame().setVisible(true);
            }
        });
        contentPane.add(btnManageAssistants);
        
        // Existing search employee button
        btnSearchEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchEmployeeFrame().setVisible(true);
            }
        });
    }
    
    private void openAddDepartment() {
        new AddDepartmentFrame().setVisible(true);
    }
    
    private void openAddDesignation() {
        new AddDesignationFrame().setVisible(true);
    }
    
    private void openAddEmployee() {
        new AddEmployeeFrame().setVisible(true);
    }
}