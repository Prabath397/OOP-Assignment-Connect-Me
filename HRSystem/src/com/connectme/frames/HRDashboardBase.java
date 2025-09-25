package com.connectme.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HRDashboardBase extends JFrame {
    protected JPanel contentPane;
    protected JButton btnSearchEmployee;
    protected JButton btnLogout;

    public HRDashboardBase() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        btnSearchEmployee = new JButton("Search Employee");
        btnSearchEmployee.setBounds(50, 50, 200, 30);
        contentPane.add(btnSearchEmployee);
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(50, 100, 200, 30);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        contentPane.add(btnLogout);
    }
    
    protected void logout() {
        this.dispose();
        new LoginFrame().setVisible(true);
    }
}