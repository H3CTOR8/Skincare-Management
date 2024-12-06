package GUI4Database;

import Database.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignupGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sign Up");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 20, 80, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(150, 20, 200, 25);
        panel.add(idText);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 50, 200, 25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 80, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(150, 80, 200, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 110, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 110, 200, 25);
        panel.add(passwordText);

        JLabel skinIdLabel = new JLabel("Skin ID:");
        skinIdLabel.setBounds(10, 140, 80, 25);
        panel.add(skinIdLabel);

        JTextField skinIdText = new JTextField(20);
        skinIdText.setBounds(150, 140, 200, 25);
        panel.add(skinIdText);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(10, 170, 150, 25);
        panel.add(signupButton);

        //handling events such as: buttons, getText from user input
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();
                String name = nameText.getText();
                String email = emailText.getText();
                String password = new String(passwordText.getPassword());
                String skinId = skinIdText.getText();

                try {
                    Customer.signUp(id, name, email, password, skinId);
                    JOptionPane.showMessageDialog(panel, "Sign up successful! Welcome, " + name + "!");
                } catch (Customer.DuplicateEmailException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(panel, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
