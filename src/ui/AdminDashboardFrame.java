package ui;

import dao.AlumniDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * AdminDashboardFrame serves as the primary management console for the
 * Alumni Coordinator / Administrator.
 */
public class AdminDashboardFrame extends JFrame {

    private final User adminUser;
    private final AlumniDAO alumniDAO;

    private JTabbedPane tabbedPane;
    private ManageAlumniPanel managePanel;
    private AlumniDirectoryPanel directoryPanel;

    private JLabel lblTotalAlumni;
    private JLabel lblTotalDepts;
    private JLabel lblTotalCities;

    public AdminDashboardFrame(User user) {
        this.adminUser = user;
        this.alumniDAO = new AlumniDAO();
        initComponents();
        updateSummaryMetrics();
    }

    private void initComponents() {
        setTitle("Alumni Directory & Networking System - Admin Dashboard [" + adminUser.getFullName() + "]");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1100, 720);
        setMinimumSize(new Dimension(950, 600));
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

        // Header Banner & Metrics Container
        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.setOpaque(false);

        String subtitle = "Logged in as: " + adminUser.getFullName() + " (" + adminUser.getUsername() + ") | Role: " + adminUser.getRole();
        northContainer.add(UIStyle.createBannerPanel("Alumni Directory Management Portal - Admin", subtitle), BorderLayout.NORTH);

        northContainer.add(createTopMetricsBar(), BorderLayout.SOUTH);
        add(northContainer, BorderLayout.NORTH);

        // Tabbed Navigation
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(UIStyle.FONT_BUTTON);
        tabbedPane.setBackground(Color.WHITE);

        managePanel = new ManageAlumniPanel();
        directoryPanel = new AlumniDirectoryPanel();

        tabbedPane.addTab(" Alumni Management (CRUD) ", managePanel);
        tabbedPane.addTab(" Alumni Directory & Networking ", directoryPanel);

        tabbedPane.addChangeListener(e -> {
            updateSummaryMetrics();
            int idx = tabbedPane.getSelectedIndex();
            if (idx == 0) {
                managePanel.refreshData();
            } else if (idx == 1) {
                directoryPanel.refreshData();
            }
        });

        add(tabbedPane, BorderLayout.CENTER);

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

    private JPanel createTopMetricsBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 6));
        bar.setBackground(UIStyle.COLOR_BG);
        bar.setBorder(new EmptyBorder(4, 16, 2, 16));

        // Card 1: Total Alumni
        JPanel card1 = UIStyle.createCardPanel();
        card1.setLayout(new GridLayout(2, 1));
        JLabel lblT1 = new JLabel("Total Alumni", SwingConstants.CENTER);
        lblT1.setFont(UIStyle.FONT_SUBTITLE);
        lblT1.setForeground(UIStyle.COLOR_TEXT_MUTED);
        lblTotalAlumni = new JLabel("0", SwingConstants.CENTER);
        lblTotalAlumni.setFont(UIStyle.FONT_SECTION_TITLE);
        lblTotalAlumni.setForeground(UIStyle.COLOR_PRIMARY);
        card1.add(lblT1);
        card1.add(lblTotalAlumni);

        // Card 2: Departments
        JPanel card2 = UIStyle.createCardPanel();
        card2.setLayout(new GridLayout(2, 1));
        JLabel lblT2 = new JLabel("Departments", SwingConstants.CENTER);
        lblT2.setFont(UIStyle.FONT_SUBTITLE);
        lblT2.setForeground(UIStyle.COLOR_TEXT_MUTED);
        lblTotalDepts = new JLabel("0", SwingConstants.CENTER);
        lblTotalDepts.setFont(UIStyle.FONT_SECTION_TITLE);
        lblTotalDepts.setForeground(UIStyle.COLOR_ACCENT);
        card2.add(lblT2);
        card2.add(lblTotalDepts);

        // Card 3: Cities
        JPanel card3 = UIStyle.createCardPanel();
        card3.setLayout(new GridLayout(2, 1));
        JLabel lblT3 = new JLabel("Cities", SwingConstants.CENTER);
        lblT3.setFont(UIStyle.FONT_SUBTITLE);
        lblT3.setForeground(UIStyle.COLOR_TEXT_MUTED);
        lblTotalCities = new JLabel("0", SwingConstants.CENTER);
        lblTotalCities.setFont(UIStyle.FONT_SECTION_TITLE);
        lblTotalCities.setForeground(UIStyle.COLOR_SUCCESS);
        card3.add(lblT3);
        card3.add(lblTotalCities);

        bar.add(card1);
        bar.add(card2);
        bar.add(card3);

        return bar;
    }

    private void updateSummaryMetrics() {
        lblTotalAlumni.setText(String.valueOf(alumniDAO.getTotalAlumniCount()));
        lblTotalDepts.setText(String.valueOf(alumniDAO.getDistinctDepartmentsCount()));
        lblTotalCities.setText(String.valueOf(alumniDAO.getDistinctCitiesCount()));
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

        // Modules Menu
        JMenu modulesMenu = new JMenu("Navigation");
        JMenuItem itemManage = new JMenuItem("Alumni Management");
        JMenuItem itemDir = new JMenuItem("Alumni Directory");

        itemManage.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        itemDir.addActionListener(e -> tabbedPane.setSelectedIndex(1));

        modulesMenu.add(itemManage);
        modulesMenu.add(itemDir);

        // Account Menu
        JMenu accountMenu = new JMenu("Account");
        JMenuItem itemChangePwd = new JMenuItem("Change Password");
        itemChangePwd.addActionListener(e -> openChangePassword());
        accountMenu.add(itemChangePwd);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem itemAbout = new JMenuItem("About Project");
        itemAbout.addActionListener(e -> showAboutDialog());
        helpMenu.add(itemAbout);

        menuBar.add(fileMenu);
        menuBar.add(modulesMenu);
        menuBar.add(accountMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void openChangePassword() {
        ChangePasswordDialog dialog = new ChangePasswordDialog(this, adminUser);
        dialog.setVisible(true);
    }

    private void showAboutDialog() {
        String info = "Alumni Directory & Networking Management System\n"
                    + "PIET Minor Project (NSP) - B.Tech II Year (III Sem)\n\n"
                    + "Student Name: ARPEET UPADHAYAY\n"
                    + "Student ID: PIET25CR007\n"
                    + "Section: 3CSD | Session: 2026-27\n"
                    + "Institution: Poornima Institute of Engineering & Technology\n\n"
                    + "Tech Stack: Java, Java Swing, JDBC, MySQL Server 8.4";
        JOptionPane.showMessageDialog(this, info, "About Project", JOptionPane.INFORMATION_MESSAGE);
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
