package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pinTF, repinTF;
    JButton change, back;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;

        // Background ATM image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // Title label
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 220, 700, 35);
        image.add(text);

        // New PIN label
        JLabel pintext = new JLabel("New PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 260, 180, 25);
        image.add(pintext);

        // New PIN field
        pinTF = new JPasswordField();
        pinTF.setFont(new Font("Raleway", Font.BOLD, 16));
        pinTF.setBounds(330, 260, 180, 25);
        image.add(pinTF);

        // Confirm New PIN label
        JLabel repintext = new JLabel("Re-Enter New PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 300, 180, 25);
        image.add(repintext);

        // Confirm New PIN field
        repinTF = new JPasswordField();
        repinTF.setFont(new Font("Raleway", Font.BOLD, 16));
        repinTF.setBounds(330, 300, 180, 25);
        image.add(repinTF);

        // Create border for buttons
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Change PIN button
        change = new JButton("CHANGE");
        change.setBounds(365, 350, 150, 30);
        change.setBorder(border);
        change.addActionListener(this);
        image.add(change);

        // Back button
        back = new JButton("BACK");
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pinTF.getText(); // New PIN
                String rpin = repinTF.getText(); // Re-entered PIN

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                // Establish database connection
                Conn c = new Conn();

                // Update PIN in bank table
                String query1 = "UPDATE bank SET pin = ? WHERE pin = ?";
                PreparedStatement ps = c.c.prepareStatement(query1);
                ps.setString(1, rpin);
                ps.setString(2, pinnumber);
                ps.executeUpdate();

                // Update PIN in login table
                String query2 = "UPDATE login SET pin = ? WHERE pin = ?";
                PreparedStatement ps2 = c.c.prepareStatement(query2);
                ps2.setString(1, rpin);
                ps2.setString(2, pinnumber);
                ps2.executeUpdate();

                // Update PIN in signupthree table
                String query3 = "UPDATE signupthree SET pin = ? WHERE pin = ?";
                PreparedStatement ps3 = c.c.prepareStatement(query3);
                ps3.setString(1, rpin);
                ps3.setString(2, pinnumber);
                ps3.executeUpdate();

                // Close all prepared statements
                ps.close();
                ps2.close();
                ps3.close();

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully");

                dispose(); // Close current window
                new Transaction(rpin); // Open Transaction window with new PIN

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            dispose(); // Go back to transaction window
            new Transaction(pinnumber);
        }
    }
}
