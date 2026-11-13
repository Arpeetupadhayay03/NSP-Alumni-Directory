package ui;

import dao.AlumniDAO;
import model.Alumni;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * AlumniDirectoryPanel provides students and administrators with a read-only,
 * searchable directory of college alumni along with networking profile cards.
 */
public class AlumniDirectoryPanel extends JPanel {

    private final AlumniDAO alumniDAO;

    // Search Controls
    private JTextField txtSearchName;
    private JComboBox<String> cmbDepartment;
    private JTextField txtSearchOrg;
    private JTextField txtSearchCity;
    private JButton btnSearch;
    private JButton btnReset;

    // Table
    private JTable directoryTable;
    private DefaultTableModel directoryModel;

    // Profile Card Components
    private JLabel lblProfileName;
    private JLabel lblProfileRole;
    private JLabel lblProfileDeptYear;
    private JLabel lblProfileCity;
    private JLabel lblProfileEmail;
    private JLabel lblProfilePhone;
    private JLabel lblStatus;

    public AlumniDirectoryPanel() {
        this.alumniDAO = new AlumniDAO();
        initComponents();
        loadAlumni(alumniDAO.getAllAlumni());
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyle.COLOR_BG);
        setBorder(new EmptyBorder(12, 12, 12, 12));

        // Top Section: Multi-field Search Filter
        add(createSearchPanel(), BorderLayout.NORTH);

        // Center Split: Directory Table (Left) and Profile Card (Right)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        centerPanel.setOpaque(false);

        centerPanel.add(createDirectoryTablePanel());
        centerPanel.add(createProfileCardPanel());

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Status
        lblStatus = new JLabel("Showing 0 alumni records in directory.");
        lblStatus.setFont(UIStyle.FONT_SUBTITLE);
        lblStatus.setForeground(UIStyle.COLOR_TEXT_MUTED);
        lblStatus.setBorder(new EmptyBorder(4, 4, 2, 4));
        add(lblStatus, BorderLayout.SOUTH);
    }

    private JPanel createSearchPanel() {
        JPanel panel = UIStyle.createCardPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 4));

        panel.add(UIStyle.createLabel("Name:"));
        txtSearchName = new JTextField(8);
        UIStyle.styleTextField(txtSearchName);
        panel.add(txtSearchName);

        panel.add(UIStyle.createLabel("Dept:"));
        String[] depts = new String[]{"All", "Computer Science", "Information Tech", "AI & Data Science", "Electronics & Comm", "Mechanical Engg", "Civil Engg"};
        cmbDepartment = new JComboBox<>(depts);
        UIStyle.styleComboBox(cmbDepartment);
        panel.add(cmbDepartment);

        panel.add(UIStyle.createLabel("Org:"));
        txtSearchOrg = new JTextField(8);
        UIStyle.styleTextField(txtSearchOrg);
        panel.add(txtSearchOrg);

        panel.add(UIStyle.createLabel("City:"));
        txtSearchCity = new JTextField(7);
        UIStyle.styleTextField(txtSearchCity);
        panel.add(txtSearchCity);

        btnSearch = UIStyle.createPrimaryButton("Search");
        btnReset = UIStyle.createSecondaryButton("Reset / All");

        btnSearch.addActionListener(e -> handleSearch());
        btnReset.addActionListener(e -> handleReset());

        txtSearchName.addActionListener(e -> handleSearch());
        txtSearchOrg.addActionListener(e -> handleSearch());
        txtSearchCity.addActionListener(e -> handleSearch());

        panel.add(btnSearch);
        panel.add(btnReset);

        return panel;
    }

    private JPanel createDirectoryTablePanel() {
        JPanel card = UIStyle.createCardPanel();
        card.setLayout(new BorderLayout(0, 8));

        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                " Alumni Directory Listing ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                UIStyle.FONT_LABEL,
                UIStyle.COLOR_PRIMARY
        );
        card.setBorder(new CompoundBorder(border, new EmptyBorder(8, 8, 8, 8)));

        String[] cols = {"ID", "Name", "Batch", "Department", "Organization", "Job Role", "City"};
        directoryModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        directoryTable = new JTable(directoryModel);
        UIStyle.styleTable(directoryTable);
        directoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        directoryTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = directoryTable.getSelectedRow();
                if (row != -1) {
                    int id = (int) directoryModel.getValueAt(row, 0);
                    displayAlumniProfile(id);
                }
            }
        });

        JScrollPane sp = new JScrollPane(directoryTable);
        sp.setBorder(new LineBorder(UIStyle.COLOR_BORDER, 1));
        card.add(sp, BorderLayout.CENTER);

        return card;
    }

    private JPanel createProfileCardPanel() {
        JPanel card = UIStyle.createCardPanel();
        card.setLayout(new BorderLayout(0, 10));

        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                " Alumni Networking Profile ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                UIStyle.FONT_LABEL,
                UIStyle.COLOR_PRIMARY
        );
        card.setBorder(new CompoundBorder(border, new EmptyBorder(12, 14, 12, 14)));

        // Profile Header
        JPanel profileHeader = new JPanel(new GridLayout(3, 1, 0, 4));
        profileHeader.setBackground(new Color(241, 245, 249));
        profileHeader.setBorder(new CompoundBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));

        lblProfileName = new JLabel("Select an Alumni on the left", SwingConstants.LEFT);
        lblProfileName.setFont(UIStyle.FONT_SECTION_TITLE);
        lblProfileName.setForeground(UIStyle.COLOR_PRIMARY);

        lblProfileRole = new JLabel("Job Role & Company details will appear here");
        lblProfileRole.setFont(UIStyle.FONT_LABEL);
        lblProfileRole.setForeground(UIStyle.COLOR_ACCENT);

        lblProfileDeptYear = new JLabel("Department & Graduation Year");
        lblProfileDeptYear.setFont(UIStyle.FONT_SUBTITLE);
        lblProfileDeptYear.setForeground(UIStyle.COLOR_TEXT_MUTED);

        profileHeader.add(lblProfileName);
        profileHeader.add(lblProfileRole);
        profileHeader.add(lblProfileDeptYear);

        card.add(profileHeader, BorderLayout.NORTH);

        // Details Panel
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.35;
        detailsPanel.add(UIStyle.createLabel("City / Location:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        lblProfileCity = new JLabel("-");
        lblProfileCity.setFont(UIStyle.FONT_REGULAR);
        detailsPanel.add(lblProfileCity, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        detailsPanel.add(UIStyle.createLabel("Email Address:"), gbc);
        gbc.gridx = 1;
        lblProfileEmail = new JLabel("-");
        lblProfileEmail.setFont(UIStyle.FONT_REGULAR);
        detailsPanel.add(lblProfileEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        detailsPanel.add(UIStyle.createLabel("Contact Phone:"), gbc);
        gbc.gridx = 1;
        lblProfilePhone = new JLabel("-");
        lblProfilePhone.setFont(UIStyle.FONT_REGULAR);
        detailsPanel.add(lblProfilePhone, gbc);

        card.add(detailsPanel, BorderLayout.CENTER);

        // Networking Note Footer
        JPanel notePanel = new JPanel(new BorderLayout());
        notePanel.setBackground(new Color(236, 253, 245)); // Soft emerald
        notePanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(16, 185, 129), 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel lblNote = new JLabel("<html><b>Networking Guidance:</b> Connect with alumni for career advice, mentorship, and industry internship inquiries.</html>");
        lblNote.setFont(UIStyle.FONT_SUBTITLE);
        lblNote.setForeground(new Color(6, 95, 70));
        notePanel.add(lblNote, BorderLayout.CENTER);

        card.add(notePanel, BorderLayout.SOUTH);

        return card;
    }

    private void displayAlumniProfile(int alumniId) {
        Alumni a = alumniDAO.getAlumniById(alumniId);
        if (a != null) {
            lblProfileName.setText(a.getName());
            lblProfileRole.setText(a.getJobRole() + " at " + a.getOrganization());
            lblProfileDeptYear.setText(a.getDepartment() + " (Batch of " + a.getGraduationYear() + ")");
            lblProfileCity.setText(a.getCity());
            lblProfileEmail.setText(a.getEmail());
            lblProfilePhone.setText(a.getPhone());
        }
    }

    private void handleSearch() {
        String name = txtSearchName.getText().trim();
        String dept = (String) cmbDepartment.getSelectedItem();
        String org = txtSearchOrg.getText().trim();
        String city = txtSearchCity.getText().trim();

        List<Alumni> results = alumniDAO.searchByCriteria(name, dept, org, city);
        loadAlumni(results);
    }

    private void handleReset() {
        txtSearchName.setText("");
        cmbDepartment.setSelectedIndex(0);
        txtSearchOrg.setText("");
        txtSearchCity.setText("");
        loadAlumni(alumniDAO.getAllAlumni());
    }

    public void refreshData() {
        handleReset();
    }

    public void loadAlumni(List<Alumni> list) {
        directoryModel.setRowCount(0);
        for (Alumni a : list) {
            directoryModel.addRow(new Object[]{
                    a.getAlumniId(),
                    a.getName(),
                    a.getGraduationYear(),
                    a.getDepartment(),
                    a.getOrganization(),
                    a.getJobRole(),
                    a.getCity()
            });
        }
        lblStatus.setText("Showing " + list.size() + " alumni records in directory.");

        if (directoryTable.getRowCount() > 0) {
            directoryTable.setRowSelectionInterval(0, 0);
        } else {
            lblProfileName.setText("No matching alumni found");
            lblProfileRole.setText("-");
            lblProfileDeptYear.setText("-");
            lblProfileCity.setText("-");
            lblProfileEmail.setText("-");
            lblProfilePhone.setText("-");
        }
    }
}
