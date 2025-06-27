package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear; // Buttons for login actions
    JTextField cardTF; // TextField for card number input
    JPasswordField pinTF; // PasswordField for PIN input

    Login() {
        // Set frame title
        setTitle("AUTOMATED TELLER MACHINE");

        // Use absolute layout
        setLayout(null);

        // Load and display logo image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        // Welcome text
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        // Card Number label
        JLabel cardNo = new JLabel("Card No:");
        cardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        cardNo.setBounds(120, 150, 400, 30);
        add(cardNo);

        // Card Number input field
        cardTF = new JTextField();
        cardTF.setBounds(300, 150, 230, 30);
        cardTF.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTF);

        // PIN label
        JLabel pin = new JLabel("Pin:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        // PIN input field
        pinTF = new JPasswordField();
        pinTF.setBounds(300, 220, 230, 30);
        pinTF.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTF);

        // Login button
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        // Clear button
        clear = new JButton("Clear");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        // Signup button
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        // Set background color
        getContentPane().setBackground(Color.WHITE);

        // Set frame size and position
        setSize(800, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Clear fields if 'Clear' is clicked
        if (e.getSource() == clear) {
            cardTF.setText("");
            pinTF.setText("");
        }

        // Attempt login if 'Sign In' is clicked
        else if (e.getSource() == login) {
            String cardnumber = cardTF.getText();
            String pinnumber = pinTF.getText();

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM login WHERE cardnumber = ? AND pin = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, cardnumber);
                ps.setString(2, pinnumber);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    dispose(); // Close login window
                    new Transaction(pinnumber); // Open transaction window
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }

                ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }

        // Open signup form if 'Sign Up' is clicked
        else if (e.getSource() == signup) {
            dispose();
            new SignupOne();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
