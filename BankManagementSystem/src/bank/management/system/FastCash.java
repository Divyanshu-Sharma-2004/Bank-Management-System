package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton rs100, rs500, rs1000, rs2000, rs5000, rs10000, exit; // Withdrawal buttons
    String pinnumber; // Store user PIN

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;

        // Load and scale ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // Instruction text label
        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setForeground(Color.WHITE);
        text.setBounds(220, 220, 700, 35);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Create button border style
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Withdraw buttons with respective amounts
        rs100 = new JButton("100");
        rs100.setBounds(160, 320, 150, 30);
        rs100.setBorder(border);
        rs100.addActionListener(this);
        image.add(rs100);

        rs500 = new JButton("500");
        rs500.setBounds(365, 320, 150, 30);
        rs500.setBorder(border);
        rs500.addActionListener(this);
        image.add(rs500);

        rs1000 = new JButton("1000");
        rs1000.setBounds(160, 350, 150, 30);
        rs1000.setBorder(border);
        rs1000.addActionListener(this);
        image.add(rs1000);

        rs2000 = new JButton("2000");
        rs2000.setBounds(365, 350, 150, 30);
        rs2000.setBorder(border);
        rs2000.addActionListener(this);
        image.add(rs2000);

        rs5000 = new JButton("5000");
        rs5000.setBounds(160, 380, 150, 30);
        rs5000.setBorder(border);
        rs5000.addActionListener(this);
        image.add(rs5000);

        rs10000 = new JButton("10000");
        rs10000.setBounds(365, 380, 150, 30);
        rs10000.setBorder(border);
        rs10000.addActionListener(this);
        image.add(rs10000);

        // Exit button to go back to transaction window
        exit = new JButton("BACK");
        exit.setBounds(705, 505, 70, 45);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setBorder(border);
        exit.addActionListener(this);
        image.add(exit);

        // Frame setup
        setUndecorated(true); // Remove title bar
        setSize(900, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            // Close window and return to transaction screen
            dispose();
            new Transaction(pinnumber);
        } else {
            // Get amount from clicked button
            String amount = ((JButton) ae.getSource()).getText();

            try {
                Conn c = new Conn();
                String query = "SELECT * FROM bank WHERE pin = ?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, pinnumber);

                ResultSet rs = ps.executeQuery();
                int balance = 0;

                // Calculate total balance
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                // If balance is insufficient, show message
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // If enough balance, record withdrawal
                Date date = new Date();
                String query2 = "INSERT INTO bank VALUES (?, ?, ?, ?)";
                PreparedStatement ps2 = c.c.prepareStatement(query2);
                ps2.setString(1, pinnumber);
                ps2.setString(2, date.toString());
                ps2.setString(3, "Withdraw");
                ps2.setString(4, amount);

                ps2.executeUpdate();

                ps.close();
                ps2.close();

                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                // Return to transaction screen
                dispose();
                new Transaction(pinnumber);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
