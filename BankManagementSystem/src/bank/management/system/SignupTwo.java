package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class SignupTwo extends JFrame implements ActionListener {

    // UI Components
    JTextField panTF, aadhaarTF;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, category, occupation, education, income;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        // Header
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 40, 400, 30);
        add(additionalDetails);

        // Religion
        JLabel religionL = new JLabel("Religion:");
        religionL.setFont(new Font("Raleway", Font.BOLD, 20));
        religionL.setBounds(100, 90, 100, 30);
        add(religionL);

        String valReligion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 90, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        // Category
        JLabel categoryL = new JLabel("Category:");
        categoryL.setFont(new Font("Raleway", Font.BOLD, 20));
        categoryL.setBounds(100, 140, 200, 30);
        add(categoryL);

        String valCategory[] = {"General", "OBC", "SC", "ST", "OTHER"};
        category = new JComboBox(valCategory);
        category.setBounds(300, 140, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        // Income
        JLabel incomeL = new JLabel("Income:");
        incomeL.setFont(new Font("Raleway", Font.BOLD, 20));
        incomeL.setBounds(100, 190, 200, 30);
        add(incomeL);

        String valIncome[] = {"Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "> 10,00,000"};
        income = new JComboBox(valIncome);
        income.setBounds(300, 190, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        // Education
        JLabel educationL = new JLabel("Educational");
        educationL.setFont(new Font("Raleway", Font.BOLD, 20));
        educationL.setBounds(100, 240, 200, 30);
        add(educationL);

        JLabel qualificationL = new JLabel("Qualification:");
        qualificationL.setFont(new Font("Raleway", Font.BOLD, 20));
        qualificationL.setBounds(100, 265, 200, 30);
        add(qualificationL);

        String valEducation[] = {"Non Graduate", "Graduate", "Post-Graduation", "Doctrate", "Other"};
        education = new JComboBox(valEducation);
        education.setBounds(300, 265, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        // Occupation
        JLabel occupationL = new JLabel("Occupation");
        occupationL.setFont(new Font("Raleway", Font.BOLD, 20));
        occupationL.setBounds(100, 340, 200, 30);
        add(occupationL);

        String valOccupation[] = {"Salaried", "Self-Employed", "Bussiness", "Student", "Retired", "Other"};
        occupation = new JComboBox(valOccupation);
        occupation.setBounds(300, 340, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        // PAN Number
        JLabel panL = new JLabel("PAN Number:");
        panL.setFont(new Font("Raleway", Font.BOLD, 20));
        panL.setBounds(100, 390, 200, 30);
        add(panL);

        panTF = new JTextField();
        panTF.setFont(new Font("Raleway", Font.BOLD, 14));
        panTF.setBounds(300, 390, 400, 30);
        add(panTF);

        // Aadhaar Number
        JLabel aadhaarL = new JLabel("Aadhar Number:");
        aadhaarL.setFont(new Font("Raleway", Font.BOLD, 20));
        aadhaarL.setBounds(100, 440, 200, 30);
        add(aadhaarL);

        aadhaarTF = new JTextField();
        aadhaarTF.setFont(new Font("Raleway", Font.BOLD, 14));
        aadhaarTF.setBounds(300, 440, 400, 30);
        add(aadhaarTF);

        // Senior Citizen
        JLabel seniorL = new JLabel("Senior Citizen:");
        seniorL.setFont(new Font("Raleway", Font.BOLD, 20));
        seniorL.setBounds(100, 490, 200, 30);
        add(seniorL);

        syes = new JRadioButton("Yes");
        syes.setBounds(300, 490, 60, 30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(400, 490, 120, 30);
        sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup srCitizenGroup = new ButtonGroup();
        srCitizenGroup.add(syes);
        srCitizenGroup.add(sno);

        // Existing Account
        JLabel existingAccL = new JLabel("Existing Account:");
        existingAccL.setFont(new Font("Raleway", Font.BOLD, 20));
        existingAccL.setBounds(100, 540, 200, 30);
        add(existingAccL);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 540, 60, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(400, 540, 120, 30);
        eno.setBackground(Color.WHITE);
        add(eno);

        ButtonGroup existingAccGroup = new ButtonGroup();
        existingAccGroup.add(eyes);
        existingAccGroup.add(eno);

        // Next Button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 590, 80, 30);
        next.addActionListener(this);
        add(next);

        // Frame properties
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 680);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        // Retrieve form data
        String sreligion = religion.getSelectedItem().toString();
        String scategory = category.getSelectedItem().toString();
        String sincome = income.getSelectedItem().toString();
        String seducation = education.getSelectedItem().toString();
        String soccupation = occupation.getSelectedItem().toString();

        String span = panTF.getText();
        String saadhaar = aadhaarTF.getText();

        // Radio Button selections
        String seniorcitizen = null;
        if (syes.isSelected()) {
            seniorcitizen = "Yes";
        } else if (sno.isSelected()) {
            seniorcitizen = "No";
        }

        String existingaccount = null;
        if (eyes.isSelected()) {
            existingaccount = "Yes";
        } else if (eno.isSelected()) {
            existingaccount = "No";
        }

        try {
            // Validation
            if (
                sreligion.equals("") ||
                scategory.equals("") ||
                sincome.equals("") ||
                seducation.equals("") ||
                soccupation.equals("") ||
                span.equals("") ||
                saadhaar.equals("") ||
                (!syes.isSelected() && !sno.isSelected()) ||
                (!eyes.isSelected() && !eno.isSelected())
            ) {
                JOptionPane.showMessageDialog(null, "Please fill all the details correctly.");
            } else {

                // Insert into database
                Conn c = new Conn();
                String query = "INSERT INTO signuptwo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = c.c.prepareStatement(query);

                ps.setString(1, formno);
                ps.setString(2, sreligion);
                ps.setString(3, scategory);
                ps.setString(4, sincome);
                ps.setString(5, seducation);
                ps.setString(6, soccupation);
                ps.setString(7, span);
                ps.setString(8, saadhaar);
                ps.setString(9, seniorcitizen);
                ps.setString(10, existingaccount);

                ps.executeUpdate();
                ps.close();

                // Move to next page
                dispose();
                new SignupThree(formno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
