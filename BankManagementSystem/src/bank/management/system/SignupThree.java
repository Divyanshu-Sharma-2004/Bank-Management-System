package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {

    // Declare UI components
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;

        // Page title
        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 20, 400, 40);
        add(l1);

        // Account type label
        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 90, 200, 30);
        add(type);

        // Radio buttons for account type
        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Reccuring Deposit Account");

        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setFont(new Font("Raleway", Font.BOLD, 16));

        r1.setBackground(Color.WHITE);
        r2.setBackground(Color.WHITE);
        r3.setBackground(Color.WHITE);
        r4.setBackground(Color.WHITE);

        r1.setBounds(100, 130, 250, 20);
        r2.setBounds(350, 130, 250, 20);
        r3.setBounds(100, 170, 250, 20);
        r4.setBounds(350, 170, 250, 20);

        add(r1);
        add(r2);
        add(r3);
        add(r4);

        // Group radio buttons
        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        // Card number section
        JLabel card = new JLabel("Card Number");
        card.setFont(new Font("Raleway", Font.BOLD, 22));
        card.setBounds(100, 220, 200, 30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-4104");
        number.setFont(new Font("Raleway", Font.BOLD, 22));
        number.setBounds(330, 220, 300, 30);
        add(number);

        JLabel carddetail = new JLabel("Your 16 Digit, Card Number");
        carddetail.setFont(new Font("Raleway", Font.BOLD, 12));
        carddetail.setBounds(100, 250, 300, 20);
        add(carddetail);

        // PIN section
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100, 290, 200, 30);
        add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Raleway", Font.BOLD, 22));
        pnumber.setBounds(330, 290, 300, 30);
        add(pnumber);

        JLabel pindetail = new JLabel("Your 4 Digit, Password");
        pindetail.setFont(new Font("Raleway", Font.BOLD, 12));
        pindetail.setBounds(100, 320, 300, 20);
        add(pindetail);

        // Services section
        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 370, 200, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("Email & SMS Alert");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declares that the above entered details are ccorrect to the best of my knowledge");

        c1.setBackground(Color.WHITE);
        c2.setBackground(Color.WHITE);
        c3.setBackground(Color.WHITE);
        c4.setBackground(Color.WHITE);
        c5.setBackground(Color.WHITE);
        c6.setBackground(Color.WHITE);
        c7.setBackground(Color.WHITE);

        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c7.setFont(new Font("Raleway", Font.BOLD, 12));

        c1.setBounds(100, 410, 200, 30);
        c2.setBounds(350, 410, 200, 30);
        c3.setBounds(100, 450, 200, 30);
        c4.setBounds(350, 450, 200, 30);
        c5.setBounds(100, 490, 200, 30);
        c6.setBounds(350, 490, 200, 30);
        c7.setBounds(100, 560, 600, 30);

        add(c1);
        add(c2);
        add(c3);
        add(c4);
        add(c5);
        add(c6);
        add(c7);

        // Buttons
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 600, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 600, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Frame setup
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {

            // Get selected account type
            String accountType = null;
            if (r1.isSelected()) accountType = "Saving Account";
            else if (r2.isSelected()) accountType = "Fixed Deposit Account";
            else if (r3.isSelected()) accountType = "Current Account";
            else if (r4.isSelected()) accountType = "Recurring Deposit Account";

            // Generate random card number and pin
            Random random = new Random();
            String cardnumber = "" + Math.abs(random.nextLong() % 90000000L + 5040936000000000L);
            String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            // Gather selected facilities
            String facility = "";
            if (c1.isSelected()) facility += "ATM Card, ";
            if (c2.isSelected()) facility += "Internet Banking, ";
            if (c3.isSelected()) facility += "Mobile Baning, ";
            if (c4.isSelected()) facility += "Email & SMS Alert, ";
            if (c5.isSelected()) facility += "Cheque Book, ";
            if (c6.isSelected()) facility += "E-Statement";

            try {
                // Validate input
                if (accountType.equals("") || cardnumber.equals("") || pinnumber.equals("") || facility.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the details correctly.");
                } else {

                    // Insert into signupthree
                    Conn c = new Conn();
                    String query = "INSERT INTO signupthree VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement ps = c.c.prepareStatement(query);
                    ps.setString(1, formno);
                    ps.setString(2, accountType);
                    ps.setString(3, cardnumber);
                    ps.setString(4, pinnumber);
                    ps.setString(5, facility);

                    // Insert into login
                    String query2 = "INSERT INTO login VALUES (?, ?, ?)";
                    PreparedStatement ps2 = c.c.prepareStatement(query2);
                    ps2.setString(1, formno);
                    ps2.setString(2, cardnumber);
                    ps2.setString(3, pinnumber);

                    // Execute and close
                    ps.executeUpdate();
                    ps2.executeUpdate();

                    ps.close();
                    ps2.close();

                    // Show success dialog and move to deposit
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\n Pin: " + pinnumber);
                    dispose();
                    new Login();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            dispose();
            new Login();
        }
    }
}
