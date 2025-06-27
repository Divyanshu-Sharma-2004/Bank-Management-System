package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount; // Input field for deposit amount
    JButton deposit, back; // Buttons for deposit and back
    String pinnumber; // Stores user's PIN

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        // Load and set ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // Label asking user to enter deposit amount
        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setBounds(185, 220, 700, 35);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Text field for entering amount
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 270, 320, 25);
        image.add(amount);

        // Button border style
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Deposit button
        deposit = new JButton("Deposit");
        deposit.setBounds(365, 350, 150, 30);
        deposit.setBorder(border);
        deposit.addActionListener(this);
        image.add(deposit);

        // Back button
        back = new JButton("Back");
        back.setBounds(365, 380, 150, 30);
        back.setBorder(border);
        back.addActionListener(this);
        image.add(back);

        // Frame setup
        setUndecorated(true); // Remove default window borders
        setSize(900, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            // Go back to transactions screen
            dispose();
            new Transaction(pinnumber);
        } else if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();

            // Validate input
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
                try {
                    // Insert deposit record into database
                    Conn c = new Conn();
                    String query = "INSERT INTO bank VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = c.c.prepareStatement(query);
                    ps.setString(1, pinnumber);
                    ps.setString(2, date.toString());
                    ps.setString(3, "Deposit");
                    ps.setString(4, number);

                    ps.executeUpdate();
                    ps.close();

                    // Show success message and return to transaction screen
                    JOptionPane.showMessageDialog(null, "Rs: " + number + " Deposited Successfully");
                    dispose();
                    new Transaction(pinnumber);
                } catch (Exception e) {
                    // Print any exception
                    System.out.println(e);
                }
            }
        }
    }
}
