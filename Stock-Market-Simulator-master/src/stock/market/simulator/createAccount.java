package stock.market.simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class createAccount {

    accountProfile accProfile;

    JTextField accountName;
    JTextField balance;

    boolean created;
    JFrame frame;

     public createAccount() {
        created = false;
        ButtonListener listener = new ButtonListener();

        frame = new JFrame("Create Account");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblaccName = new JLabel("NAME: ");
        accountName = new JTextField("");

        JLabel lblbalance = new JLabel("BALANCE: ");
        balance = new JTextField("");

        Button btn = new Button("Create");

        panel.add(lblaccName);
        panel.add(accountName);

        panel.add(lblbalance);
        panel.add(balance);

        panel.add(new JLabel(""));

        btn.addActionListener(listener);
        panel.add(btn);

        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }
    
     public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent et) {

            String btnName = et.getActionCommand();

            try {
                if (btnName.equals("Create")) {

                    String accName = accountName.getText();
                    double accBalance = Double.parseDouble(balance.getText());

                    accProfile = new accountProfile(accName, accBalance);

                    created = true;

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

     public accountProfile getAccountProfile() {
        return accProfile;
    }

     public boolean getCreated() {
        return created;
    }

     public void terminate() {
        frame.dispose();
    }
}
