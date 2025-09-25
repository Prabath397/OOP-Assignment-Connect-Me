package com.connectme.frames;

import com.connectme.models.HRAssistant;
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

public class ManageHRAssistantsFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public ManageHRAssistantsFrame() {
        setTitle("Manage HR Assistants");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0x80, 0x00, 0x00)); // #800000 background
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblId = new JLabel("Assistant ID:");
        lblId.setBounds(20, 20, 100, 20);
        lblId.setForeground(Color.WHITE);
        contentPane.add(lblId);
        
        txtId = new JTextField();
        txtId.setBounds(130, 20, 150, 20);
        txtId.setEditable(false);
        contentPane.add(txtId);
        txtId.setText(IDGenerator.generateNextHRAssistantId());
        
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(20, 50, 100, 20);
        lblName.setForeground(Color.WHITE);
        contentPane.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(130, 50, 200, 20);
        contentPane.add(txtName);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 80, 100, 20);
        lblEmail.setForeground(Color.WHITE);
        contentPane.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(130, 80, 200, 20);
        contentPane.add(txtEmail);
        
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(20, 110, 100, 20);
        lblPhone.setForeground(Color.WHITE);
        contentPane.add(lblPhone);
        
        txtPhone = new JTextField();
        txtPhone.setBounds(130, 110, 200, 20);
        contentPane.add(txtPhone);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 140, 100, 20);
        lblUsername.setForeground(Color.WHITE);
        contentPane.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(130, 140, 200, 20);
        contentPane.add(txtUsername);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 170, 100, 20);
        lblPassword.setForeground(Color.WHITE);
        contentPane.add(lblPassword);
        
        txtPassword = new JTextField();
        txtPassword.setBounds(130, 170, 200, 20);
        contentPane.add(txtPassword);
        
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
                addHRAssistant();
            }
        });
        contentPane.add(btnAdd);
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(buttonX, buttonStartY + buttonHeight + buttonSpacing, 
                         buttonWidth, buttonHeight);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editHRAssistant();
            }
        });
        contentPane.add(btnEdit);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(buttonX, buttonStartY + 2*(buttonHeight + buttonSpacing), 
                          buttonWidth, buttonHeight);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteHRAssistant();
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
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Username"};
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
        scrollPane.setBounds(20, 250, 740, 300);
        contentPane.add(scrollPane);
        
        loadHRAssistants();
    }
    
    private void addHRAssistant() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        
        if (!Validator.validateHRAssistantFields(name, email, phone, username, password)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields with valid data", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        HRAssistant assistant = new HRAssistant(id, name, email, phone, username, password);
        FileHandler.addHRAssistant(assistant);
        JOptionPane.showMessageDialog(this, "HR Assistant added successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        clearFields();
        loadHRAssistants();
        txtId.setText(IDGenerator.generateNextHRAssistantId());
    }
    
    private void editHRAssistant() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an HR Assistant to edit", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        
        if (!Validator.validateHRAssistantFields(name, email, phone, username, password)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields with valid data", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        HRAssistant assistant = new HRAssistant(id, name, email, phone, username, password);
        FileHandler.updateHRAssistant(assistant);
        JOptionPane.showMessageDialog(this, "HR Assistant updated successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        clearFields();
        loadHRAssistants();
        txtId.setText(IDGenerator.generateNextHRAssistantId());
    }
    
    private void deleteHRAssistant() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an HR Assistant to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this HR Assistant?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            FileHandler.deleteHRAssistant(id);
            JOptionPane.showMessageDialog(this, "HR Assistant deleted successfully", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            loadHRAssistants();
            txtId.setText(IDGenerator.generateNextHRAssistantId());
        }
    }
    
    private void clearFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }
    
    private void loadHRAssistants() {
        tableModel.setRowCount(0);
        List<HRAssistant> assistants = FileHandler.readHRAssistants();
        for (HRAssistant a : assistants) {
            Object[] row = {
                a.getId(),
                a.getName(),
                a.getEmail(),
                a.getPhone(),
                a.getUsername()
            };
            tableModel.addRow(row);
        }
    }
}