package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Transaction extends JFrame implements ActionListener {

    // Buttons for different transaction types
    JButton deposit, withdrawal, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;

    Transaction(String pinnumber) {
        this.pinnumber = pinnumber;

        // ATM background image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // Transaction label
        JLabel text = new JLabel("Please select your Transaction");
        text.setForeground(Color.WHITE);
        text.setBounds(220, 220, 700, 35);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Button border style
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Transaction buttons
        deposit = new JButton("Deposit");
        deposit.setBounds(160, 320, 150, 30);
        deposit.setBorder(border);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawal = new JButton("Cash Withdrawal");
        withdrawal.setBounds(365, 320, 150, 30);
        withdrawal.setBorder(border);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(160, 350, 150, 30);
        fastcash.setBorder(border);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(365, 350, 150, 30);
        ministatement.setBorder(border);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(160, 380, 150, 30);
        pinchange.setBorder(border);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(365, 380, 150, 30);
        balanceenquiry.setBorder(border);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        // Exit button
        exit = new JButton("EXIT");
        exit.setBounds(705, 505, 70, 45);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setBorder(border);
        exit.addActionListener(this);
        image.add(exit);

        // Frame settings
        setUndecorated(true); // Remove window borders
        setSize(900, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action listener for all buttons
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            dispose();
            new Deposit(pinnumber);
        } else if (ae.getSource() == withdrawal) {
            dispose();
            new Withdrawal(pinnumber);
        } else if (ae.getSource() == fastcash) {
            dispose();
            new FastCash(pinnumber);
        } else if (ae.getSource() == pinchange) {
            dispose();
            new PinChange(pinnumber);
        } else if (ae.getSource() == balanceenquiry) {
            dispose();
            new BalanceEnquiry(pinnumber);
        } else if (ae.getSource() == ministatement) {
            dispose();
            new MiniStatement(pinnumber);
        }
    }
}
