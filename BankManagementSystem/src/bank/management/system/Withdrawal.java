package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.border.Border;

import java.util.*;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber;

    // Constructor
    Withdrawal(String pinnumber) {
        this.pinnumber = pinnumber;

        // ATM background setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // Instruction text
        JLabel text = new JLabel("Enter the amount you want to Withdraw");
        text.setForeground(Color.WHITE);
        text.setBounds(185, 220, 700, 35);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Amount input field
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 270, 320, 25);
        image.add(amount);

        // Button border style
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Withdraw button
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(365, 350, 150, 30);
        withdraw.setBorder(border);
        withdraw.addActionListener(this);
        image.add(withdraw);

        // Back button
        back = new JButton("Back");
        back.setBounds(365, 380, 150, 30);
        back.setBorder(border);
        back.addActionListener(this);
        image.add(back);

        // Frame settings
        setUndecorated(true);
        setSize(900, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action event handling
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            // Navigate back to transaction screen
            dispose();
            new Transaction(pinnumber);
        } else if (ae.getSource() == withdraw) {
            String number = amount.getText();
            Date date = new Date();

            if (number.equals("")) {
                // Input validation
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
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

                    ps.close();
                    
                    // If balance is insufficient, show message
                    if (balance < Integer.parseInt(number)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    
                    // Insert withdrawal into database
                    String query2 = "INSERT INTO bank VALUES (?, ?, ?, ?)";
                    PreparedStatement ps2 = c.c.prepareStatement(query2);
                    ps2.setString(1, pinnumber);
                    ps2.setString(2, date.toString());
                    ps2.setString(3, "Withdraw");
                    ps2.setString(4, number);

                    ps2.executeUpdate();
                    ps2.close();

                    // Success message
                    JOptionPane.showMessageDialog(null, "Rs: " + number + " Withdraw Successfully");

                    // Return to transaction menu
                    dispose();
                    new Transaction(pinnumber);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
