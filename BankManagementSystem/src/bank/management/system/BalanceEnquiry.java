package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pinnumber;
    JButton back;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        // Load and set background ATM image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // Create border for button
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Back button
        back = new JButton("BACK");
        back.setBounds(365, 380, 150, 30);
        back.setBorder(border);
        back.addActionListener(this);
        image.add(back);

        // Variable to store balance
        int balance = 0;

        try {
            // Connect to database and calculate balance
            Conn c = new Conn();
            String query = "SELECT * FROM bank WHERE pin = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, pinnumber);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Show balance on the screen
        JLabel text = new JLabel("Your Current Account balance is Rs : " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 250, 400, 30);
        image.add(text);

        // Frame settings
        setUndecorated(true);
        setSize(900, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // On back button click, go to transaction screen
        dispose();
        new Transaction(pinnumber);
    }
}
