package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * ChangePasswordDialog provides a secure, modal dialog for users to update
 * their account passwords with verification of their current password.
 */
public class ChangePasswordDialog extends JDialog {

    private final User currentUser;
    private final UserDAO userDAO;

    private JPasswordField txtCurrentPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;

    public ChangePasswordDialog(Frame parent, User user) {
        super(parent, "Change Password - " + user.getUsername(), true);
        this.currentUser = user;
        this.userDAO = new UserDAO();

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 15));
        setResizable(false);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyle.COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel lblTitle = new JLabel("Change Account Password");
        lblTitle.setFont(UIStyle.FONT_SECTION_TITLE);
        lblTitle.setForeground(UIStyle.COLOR_TEXT_WHITE);

        JLabel lblUser = new JLabel("User: " + currentUser.getFullName() + " (" + currentUser.getUsername() + ")");
        lblUser.setFont(UIStyle.FONT_SUBTITLE);
        lblUser.setForeground(new Color(203, 213, 225));

        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(lblUser, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Current Password
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(UIStyle.createLabel("Current Password:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCurrentPassword = new JPasswordField(15);
        UIStyle.styleTextField(txtCurrentPassword);
        formPanel.add(txtCurrentPassword, gbc);

        // New Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(UIStyle.createLabel("New Password:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNewPassword = new JPasswordField(15);
        UIStyle.styleTextField(txtNewPassword);
        formPanel.add(txtNewPassword, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(UIStyle.createLabel("Confirm New Password:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtConfirmPassword = new JPasswordField(15);
        UIStyle.styleTextField(txtConfirmPassword);
        formPanel.add(txtConfirmPassword, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        buttonPanel.setBackground(UIStyle.COLOR_BG);

        JButton btnUpdate = UIStyle.createPrimaryButton("Update Password");
        JButton btnCancel = UIStyle.createSecondaryButton("Cancel");

        btnUpdate.addActionListener(e -> handleChangePassword());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    private void handleChangePassword() {
        String currentPwd = new String(txtCurrentPassword.getPassword()).trim();
        String newPwd = new String(txtNewPassword.getPassword()).trim();
        String confirmPwd = new String(txtConfirmPassword.getPassword()).trim();

        if (currentPwd.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your current password.",
                    "Input Validation", JOptionPane.WARNING_MESSAGE);
            txtCurrentPassword.requestFocus();
            return;
        }

        if (newPwd.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a new password.",
                    "Input Validation", JOptionPane.WARNING_MESSAGE);
            txtNewPassword.requestFocus();
            return;
        }

        if (newPwd.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "New password must be at least 4 characters long.",
                    "Input Validation", JOptionPane.WARNING_MESSAGE);
            txtNewPassword.requestFocus();
            return;
        }

        if (!newPwd.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(this,
                    "New password and confirm password do not match.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtConfirmPassword.requestFocus();
            return;
        }

        boolean success = userDAO.changePassword(currentUser.getUserId(), currentPwd, newPwd);
        if (success) {
            currentUser.setPassword(newPwd);
            JOptionPane.showMessageDialog(this,
                    "Password updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Current password incorrect. Please try again.",
                    "Authentication Error", JOptionPane.ERROR_MESSAGE);
            txtCurrentPassword.setText("");
            txtCurrentPassword.requestFocus();
        }
    }
}
