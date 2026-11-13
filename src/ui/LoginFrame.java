package ui;

import dao.UserDAO;
import db.DBConnection;
import model.User;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * LoginFrame provides the initial authentication gateway for both
 * Canteen Administrators and Customers.
 */
public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnClear;
    private final UserDAO userDAO;

    public LoginFrame() {
        this.userDAO = new UserDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Alumni Directory & Networking Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 560);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Header Banner
        JPanel headerPanel = new JPanel(new GridLayout(3, 1, 0, 4));
        headerPanel.setBackground(UIStyle.COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(16, 20, 16, 20));

        JLabel lblInst = new JLabel("POORNIMA INSTITUTE OF ENGG. & TECHNOLOGY", SwingConstants.CENTER);
        lblInst.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblInst.setForeground(new Color(186, 230, 253)); // Sky blue accent

        JLabel lblTitle = new JLabel("Alumni Directory System", SwingConstants.CENTER);
        lblTitle.setFont(UIStyle.FONT_HEADER_TITLE);
        lblTitle.setForeground(UIStyle.COLOR_TEXT_WHITE);

        JLabel lblSub = new JLabel("Minor Project (NSP) - Session 2026-27", SwingConstants.CENTER);
        lblSub.setFont(UIStyle.FONT_SUBTITLE);
        lblSub.setForeground(new Color(203, 213, 225));

        headerPanel.add(lblInst);
        headerPanel.add(lblTitle);
        headerPanel.add(lblSub);
        add(headerPanel, BorderLayout.NORTH);

        // Center Content (Login Card)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(UIStyle.COLOR_BG);
        centerPanel.setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel loginCard = UIStyle.createCardPanel();
        loginCard.setLayout(new GridBagLayout());
        loginCard.setPreferredSize(new Dimension(380, 250));

        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                " Account Authentication ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                UIStyle.FONT_LABEL,
                UIStyle.COLOR_PRIMARY
        );
        loginCard.setBorder(new CompoundBorder(border, new EmptyBorder(12, 16, 12, 16)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 6, 8, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        loginCard.add(UIStyle.createLabel("Username:"), gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        txtUsername = new JTextField(15);
        UIStyle.styleTextField(txtUsername);
        loginCard.add(txtUsername, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        loginCard.add(UIStyle.createLabel("Password:"), gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        txtPassword = new JPasswordField(15);
        UIStyle.styleTextField(txtPassword);
        loginCard.add(txtPassword, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setOpaque(false);

        btnLogin = UIStyle.createPrimaryButton("Login");
        btnClear = UIStyle.createSecondaryButton("Clear");

        btnLogin.addActionListener(e -> handleLogin());
        btnClear.addActionListener(e -> handleClear());

        txtUsername.addActionListener(e -> handleLogin());
        txtPassword.addActionListener(e -> handleLogin());

        btnPanel.add(btnLogin);
        btnPanel.add(btnClear);
        loginCard.add(btnPanel, gbc);

        centerPanel.add(loginCard);
        add(centerPanel, BorderLayout.CENTER);

        // South Info Panel (Demo Credentials Hint for Viva)
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(UIStyle.COLOR_BG);
        southPanel.setBorder(new EmptyBorder(0, 20, 16, 20));

        JPanel hintCard = new JPanel(new GridLayout(2, 1, 2, 2));
        hintCard.setBackground(new Color(254, 243, 199)); // Amber-50
        hintCard.setBorder(new CompoundBorder(
                new LineBorder(new Color(245, 158, 11), 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));

        JLabel lblHint1 = new JLabel("Admin: admin / admin123  (Manage Alumni, Directory & Search)");
        lblHint1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblHint1.setForeground(new Color(146, 64, 14));

        JLabel lblHint2 = new JLabel("User: user1 / user123  (Browse Directory & Networking Profiles)");
        lblHint2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblHint2.setForeground(new Color(146, 64, 14));

        hintCard.add(lblHint1);
        hintCard.add(lblHint2);
        southPanel.add(hintCard, BorderLayout.CENTER);

        add(southPanel, BorderLayout.SOUTH);
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your username.",
                    "Input Validation", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your password.",
                    "Input Validation", JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return;
        }

        // Test DB Connection
        if (!DBConnection.testConnection()) {
            JOptionPane.showMessageDialog(this,
                    "Database Connection Failure!\nPlease ensure MySQL service is running and alumni_db is initialized.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userDAO.authenticate(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.\nPlease check your credentials and try again.",
                    "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
            return;
        }

        // Login Success
        JOptionPane.showMessageDialog(this,
                "Login Successful!\nWelcome, " + user.getFullName() + " (" + user.getRole() + ")",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);

        dispose(); // Close Login Frame

        SwingUtilities.invokeLater(() -> {
            if (user.isAdmin()) {
                AdminDashboardFrame adminDashboard = new AdminDashboardFrame(user);
                adminDashboard.setVisible(true);
            } else {
                UserDashboardFrame userDashboard = new UserDashboardFrame(user);
                userDashboard.setVisible(true);
            }
        });
    }

    private void handleClear() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }
}
