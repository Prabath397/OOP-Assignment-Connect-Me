package com.connectme.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRAssistantDashboardFrame extends HRDashboardBase {
    public HRAssistantDashboardFrame() {
        setTitle("Connect Me - HR Assistant Dashboard");
        
        btnSearchEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchEmployee();
            }
        });
    }
    
    private void openSearchEmployee() {
        new SearchEmployeeFrame().setVisible(true);
    }
}