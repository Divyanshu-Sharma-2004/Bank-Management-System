package bank.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class MiniStatement extends JFrame implements ActionListener {

    String pinnumber;
    DefaultTableModel model;
    JTable table;
    JButton exit;

    MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;

        // Bank title label
        JLabel title = new JLabel("INDIAN BANK");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(210, 10, 200, 30);
        add(title);

        // Table setup with column names
        String[] columnNames = {"Date", "Type", "Amount"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Set width for 'Date' column

        // Scroll pane to hold the table
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 60, 500, 200);
        add(scroll);

        // Label to show masked card number
        JLabel card = new JLabel();
        card.setFont(new Font("System", Font.PLAIN, 14));
        card.setBounds(50, 280, 350, 20);
        add(card);

        // Fetch card number from login table
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM login WHERE pin = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, pinnumber);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cardNum = rs.getString("cardnumber");
                card.setText("Card Number: " + cardNum.substring(0, 4) + "-XXXX-XXXX-" + cardNum.substring(12));
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Fetch transaction data from bank table and fill in the table
        try {
            Conn c = new Conn();
            String query2 = "SELECT * FROM bank WHERE pin = ?";
            PreparedStatement ps2 = c.c.prepareStatement(query2);
            ps2.setString(1, pinnumber);

            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                model.addRow(new Object[]{date, type, amount});
            }
            ps2.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Label to show current balance
        JLabel balance = new JLabel();
        balance.setBounds(50, 310, 250, 20);
        add(balance);

        // Calculate and display current balance
        int bal = 0;
        try {
            Conn c = new Conn();
            String query3 = "SELECT * FROM bank WHERE pin = ?";
            PreparedStatement ps3 = c.c.prepareStatement(query3);
            ps3.setString(1, pinnumber);

            ResultSet rs = ps3.executeQuery();
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }

            balance.setText("Your Current Balance is: Rs " + bal);
            ps3.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Back button setup
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        exit = new JButton("BACK");
        exit.setBounds(250, 380, 90, 30);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setBorder(border);
        exit.addActionListener(this);
        add(exit);

        // Frame settings
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action for back button
    public void actionPerformed(ActionEvent ae) {
        dispose();
        new Transaction(pinnumber);
    }
}
