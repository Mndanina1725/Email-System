import javax.swing.*;
import java.awt.*;

public class EmailSystemApp {
    private JFrame frame;
    private User currentUser;

    public EmailSystemApp() {
        frame = new JFrame("Email System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Show login screen
        showLoginScreen();

        frame.setVisible(true);
    }

    private void showLoginScreen() {
        JPanel panel = new JPanel(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 100, 30);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 150, 100, 30);
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            currentUser = User.login(username, password);
            if (currentUser != null) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                showEmailScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        registerButton.addActionListener(e -> showRegistrationScreen());

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private void showRegistrationScreen() {
        JPanel panel = new JPanel(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        panel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 30);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 30);
        panel.add(emailField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 200, 100, 30);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            User user = new User(username, password, email);
            if (user.register()) {
                JOptionPane.showMessageDialog(frame, "Registration successful!");
                showLoginScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Registration failed.");
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private void showEmailScreen() {
        JPanel panel = new JPanel(null);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(50, 50, 100, 30);
        panel.add(toLabel);

        JTextField toField = new JTextField();
        toField.setBounds(150, 50, 200, 30);
        panel.add(toField);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(50, 100, 100, 30);
        panel.add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(150, 100, 200, 30);
        panel.add(subjectField);

        JLabel contentLabel = new JLabel("Content:");
        contentLabel.setBounds(50, 150, 100, 30);
        panel.add(contentLabel);

        JTextArea contentArea = new JTextArea();
        contentArea.setBounds(150, 150, 200, 100);
        panel.add(contentArea);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(150, 270, 100, 30);
        panel.add(sendButton);

        sendButton.addActionListener(e -> {
            String to = toField.getText();
            String subject = subjectField.getText();
            String content = contentArea.getText();
            if (EmailService.sendEmail(to, subject, content)) {
                JOptionPane.showMessageDialog(frame, "Email sent!");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to send email.");
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }

    public static void main(String[] args) {
        new EmailSystemApp();
    }
}

