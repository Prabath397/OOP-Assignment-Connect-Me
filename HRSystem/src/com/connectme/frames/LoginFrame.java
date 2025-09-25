package com.connectme.frames;

import com.connectme.models.User;
import com.connectme.utilities.FileHandler;
import com.connectme.utilities.Validator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;

    public LoginFrame() {
        setTitle("Connect Me - HR Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("HR Management System Login");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 11, 414, 20);
        contentPane.add(lblTitle);
        
        JLabel lblRole = new JLabel("Role:");
        lblRole.setBounds(50, 50, 100, 20);
        contentPane.add(lblRole);
        
        cmbRole = new JComboBox<>(new String[]{"HR Manager", "HR Assistant"});
        cmbRole.setBounds(160, 50, 200, 20);
        contentPane.add(cmbRole);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 100, 20);
        contentPane.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(160, 80, 200, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 110, 100, 20);
        contentPane.add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 110, 200, 20);
        contentPane.add(txtPassword);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnLogin.setBounds(160, 150, 100, 25);
        contentPane.add(btnLogin);
        
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        btnClear.setBounds(270, 150, 90, 25);
        contentPane.add(btnClear);
    }
    
    private void login() {
        String role = cmbRole.getSelectedItem().toString();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (!Validator.validateLoginFields(username, password)) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<User> users = FileHandler.readUsers();
        boolean authenticated = false;
        
        for (User user : users) {
            if (user.getUsername().equals(username) && 
                user.getPassword().equals(password) && 
                user.getRole().equals(role)) {
                authenticated = true;
                break;
            }
        }
        
        if (authenticated) {
            JOptionPane.showMessageDialog(this, "Login successful!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
            if (role.equals("HR Manager")) {
                new HRManagerDashboardFrame().setVisible(true);
            } else {
                new HRAssistantDashboardFrame().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", 
                "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        cmbRole.setSelectedIndex(0);
    }
}