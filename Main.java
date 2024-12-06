package Database;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI4Database.LoginGUI;
import GUI4Database.SignupGUI;

public final class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { //SwingUtilities.invokeLater is for ensuring the event by running it on thread so one is not working
            try {
                Customer.loadFromFile(); //loading infos
            } catch (IOException e) {
                System.err.println("Failed to load customer data: " + e.getMessage()); //if there's no existing of the given infos
            }
            showMainMenu(); //either way, show the main display
        });
    }

    //the main display on screen
    private static void showMainMenu() {
        JFrame mainFrame = new JFrame("Main Menu"); //create main window
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default setting when user exit the application

        JPanel panel = new JPanel(); //JPanel is an event that organize and hold all of the display button and label
        mainFrame.add(panel);
        placeComponents(panel); //this method help define all of the GUI components that have been implementing in the panel

        mainFrame.setVisible(true);
    }

    //a method that label and set buttons for the application
    private static void placeComponents(JPanel panel) {
    panel.setLayout(null);

    JLabel titleLabel = new JLabel("Welcome to Skincare management for Customer");
    titleLabel.setBounds(90, 20, 220, 25);
    panel.add(titleLabel);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(50, 80, 120, 25);
    panel.add(loginButton);

    JButton signupButton = new JButton("Sign Up");
    signupButton.setBounds(200, 80, 120, 25);
    panel.add(signupButton);

    JButton exitButton = new JButton("Exit");
    exitButton.setBounds(125, 150, 120, 25);
    panel.add(exitButton);

    //action listener to lauch the login and signup
    //action listener for handling events
    //SwingUtilities.invokeLater is for ensuring the event by running it on thread so one is not working
    //it will run on another thread
    loginButton.addActionListener(_ -> {
        SwingUtilities.invokeLater(() -> {
            try {
                LoginGUI.main(new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error firing up login: " + ex.getMessage());
            }
        });
    });

    signupButton.addActionListener(_ -> {
        SwingUtilities.invokeLater(() -> {
            try {
                SignupGUI.main(new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error firing up signup: " + ex.getMessage());
            }
        });
    });

    //this method implemented in since I want to save data to the csv file when exiting 
    exitButton.addActionListener(_ -> {
        try {
            Customer.saveToFile();
        } catch (IOException ex) { //in case there's an error of saving infos while exiting
            System.err.println("Failed to save customer data: " + ex.getMessage());
        }
        System.exit(0); //exit the application
    });
    }
}



