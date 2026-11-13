package ui;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * UserDashboardFrame provides students and campus members with a clean,
 * read-only interface to search the alumni directory and view networking contact details.
 */
public class UserDashboardFrame extends JFrame {

    private final User currentUser;
    private AlumniDirectoryPanel directoryPanel;

    public UserDashboardFrame(User user) {
        this.currentUser = user;
        initComponents();
    }

    private void initComponents() {
        setTitle("Alumni Directory & Networking System - Student Portal [" + currentUser.getFullName() + "]");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1050, 700);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleLogout();
            }
        });

        // Menu Bar
        setJMenuBar(createMenuBar());

        setLayout(new BorderLayout());

        // Header Banner
        String subtitle = "Welcome, " + currentUser.getFullName() + " (" + currentUser.getUsername() + ") | Student Networking Portal";
        add(UIStyle.createBannerPanel("Alumni Directory & Networking Portal", subtitle), BorderLayout.NORTH);

        // Directory Panel (Read-only view with profile card)
        directoryPanel = new AlumniDirectoryPanel();
        add(directoryPanel, BorderLayout.CENTER);

        // Footer Bar
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(UIStyle.COLOR_HEADER_BG);
        footer.setBorder(new EmptyBorder(6, 16, 6, 16));

        JLabel lblFooterLeft = new JLabel("Poornima Institute of Engineering & Technology (PIET) | Session 2026-27 | 3CSD");
        lblFooterLeft.setFont(UIStyle.FONT_SUBTITLE);
        lblFooterLeft.setForeground(new Color(148, 163, 184));

        JLabel lblFooterRight = new JLabel("Student: ARPEET UPADHAYAY (PIET25CR007)");
        lblFooterRight.setFont(UIStyle.FONT_SUBTITLE);
        lblFooterRight.setForeground(new Color(186, 230, 253));

        footer.add(lblFooterLeft, BorderLayout.WEST);
        footer.add(lblFooterRight, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem itemLogout = new JMenuItem("Logout");
        JMenuItem itemExit = new JMenuItem("Exit Application");

        itemLogout.addActionListener(e -> handleLogout());
        itemExit.addActionListener(e -> System.exit(0));

        fileMenu.add(itemLogout);
        fileMenu.addSeparator();
        fileMenu.add(itemExit);

        // Navigation Menu
        JMenu navMenu = new JMenu("Navigation");
        JMenuItem itemRefresh = new JMenuItem("Refresh Directory");
        itemRefresh.addActionListener(e -> directoryPanel.refreshData());
        navMenu.add(itemRefresh);

        // Account Menu
        JMenu accountMenu = new JMenu("Account");
        JMenuItem itemChangePwd = new JMenuItem("Change Password");
        itemChangePwd.addActionListener(e -> openChangePassword());
        accountMenu.add(itemChangePwd);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem itemAbout = new JMenuItem("About System");
        itemAbout.addActionListener(e -> showAboutDialog());
        helpMenu.add(itemAbout);

        menuBar.add(fileMenu);
        menuBar.add(navMenu);
        menuBar.add(accountMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void openChangePassword() {
        ChangePasswordDialog dialog = new ChangePasswordDialog(this, currentUser);
        dialog.setVisible(true);
    }

    private void showAboutDialog() {
        String info = "Alumni Directory & Networking Management System\n"
                    + "PIET Minor Project (NSP) - B.Tech II Year (III Sem)\n\n"
                    + "Student Name: ARPEET UPADHAYAY\n"
                    + "Student ID: PIET25CR007\n"
                    + "Section: 3CSD | Session: 2026-27\n"
                    + "Institution: Poornima Institute of Engineering & Technology\n\n"
                    + "Features: Searchable Directory, Networking Profiles, Career Mentorship.";
        JOptionPane.showMessageDialog(this, info, "About System", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            });
        }
    }
}
