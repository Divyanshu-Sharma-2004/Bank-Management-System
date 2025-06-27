package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.*;

public class SignupOne extends JFrame implements ActionListener {

    // Declare all UI components and variables
    int random;
    JTextField nameTF, fnameTF, emailTF, addressTF, cityTF, stateTF, pinTF;
    JButton next;
    JRadioButton male, female, other, married, unmarried;
    JDateChooser dateChooser;

    SignupOne() {

        // Generate random 4-digit form number
        Random ran = new Random();
        random = 1000 + ran.nextInt(9000);

        // Heading label with form number
        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 10, 600, 40);
        add(formno);

        // Subheading for personal details
        JLabel personDetails = new JLabel("Page 1: Personal Details");
        personDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personDetails.setBounds(290, 60, 400, 30);
        add(personDetails);

        // Name field and label
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 110, 100, 30);
        add(name);

        nameTF = new JTextField();
        nameTF.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTF.setBounds(300, 110, 400, 30);
        add(nameTF);

        // Father's name field and label
        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 160, 200, 30);
        add(fname);

        fnameTF = new JTextField();
        fnameTF.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTF.setBounds(300, 160, 400, 30);
        add(fnameTF);

        // Date of Birth field and label
        JLabel dob = new JLabel("Date Of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 210, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 210, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);

        // Gender radio buttons
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 260, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 260, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 260, 120, 30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        // Email field and label
        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 310, 200, 30);
        add(email);

        emailTF = new JTextField();
        emailTF.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTF.setBounds(300, 310, 400, 30);
        add(emailTF);

        // Marital status radio buttons
        JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 360, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 360, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 360, 100, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBounds(630, 360, 100, 30);
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

        // Address field and label
        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 410, 200, 30);
        add(address);

        addressTF = new JTextField();
        addressTF.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTF.setBounds(300, 410, 400, 30);
        add(addressTF);

        // City field and label
        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 460, 200, 30);
        add(city);

        cityTF = new JTextField();
        cityTF.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTF.setBounds(300, 460, 400, 30);
        add(cityTF);

        // State field and label
        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 510, 200, 30);
        add(state);

        stateTF = new JTextField();
        stateTF.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTF.setBounds(300, 510, 400, 30);
        add(stateTF);

        // Pincode field and label
        JLabel pincode = new JLabel("Pin Code:");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 560, 200, 30);
        add(pincode);

        pinTF = new JTextField();
        pinTF.setFont(new Font("Raleway", Font.BOLD, 14));
        pinTF.setBounds(300, 560, 400, 30);
        add(pinTF);

        // Next button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 610, 80, 30);
        next.addActionListener(this);
        add(next);

        // Frame settings
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random; // Convert form number to string
        String name = nameTF.getText();
        String fname = fnameTF.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;

        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String email = emailTF.getText();
        String marital = null;

        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        } else if (other.isSelected()) {
            marital = "Other";
        }

        String address = addressTF.getText();
        String city = cityTF.getText();
        String state = stateTF.getText();
        String pin = pinTF.getText();

        try {
            // Validate all fields before insertion
            if (
                name.equals("") || fname.equals("") || dob.equals("") || email.equals("") ||
                address.equals("") || city.equals("") || state.equals("") || pin.equals("") ||
                (!male.isSelected() && !female.isSelected()) ||
                (!married.isSelected() && !unmarried.isSelected() && !other.isSelected())
            ) {
                JOptionPane.showMessageDialog(null, "Fill all the details");
            } else {
                // Insert into database
                Conn c = new Conn();
                String query = "INSERT INTO signup VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, formno);
                ps.setString(2, name);
                ps.setString(3, fname);
                ps.setString(4, dob);
                ps.setString(5, gender);
                ps.setString(6, email);
                ps.setString(7, marital);
                ps.setString(8, address);
                ps.setString(9, city);
                ps.setString(10, state);
                ps.setString(11, pin);
                ps.executeUpdate();

                // Proceed to next page
                dispose();
                new SignupTwo(formno);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
