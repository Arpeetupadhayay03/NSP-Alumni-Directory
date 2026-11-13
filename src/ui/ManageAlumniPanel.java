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
 * ManageAlumniPanel provides the Administrator with full CRUD
 * (Create, Read, Update, Delete) and multi-field search operations
 * on college alumni directory records.
 */
public class ManageAlumniPanel extends JPanel {

    private final AlumniDAO alumniDAO;

    // Form Components
    private JTextField txtAlumniId;
    private JTextField txtName;
    private JComboBox<Integer> cmbGradYear;
    private JComboBox<String> cmbDepartment;
    private JTextField txtOrganization;
    private JTextField txtJobRole;
    private JTextField txtCity;
    private JTextField txtEmail;
    private JTextField txtPhone;

    // Action Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Search Controls
    private JTextField txtSearch;
    private JComboBox<String> cmbSearchDept;
    private JButton btnSearch;
    private JButton btnReset;

    // Table
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblStatus;

    public static final String[] DEPARTMENTS = {
            "Computer Science", "Information Tech", "AI & Data Science",
            "Electronics & Comm", "Mechanical Engg", "Civil Engg"
    };

    public ManageAlumniPanel() {
        this.alumniDAO = new AlumniDAO();
        initComponents();
        loadTableData(alumniDAO.getAllAlumni());
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyle.COLOR_BG);
        setBorder(new EmptyBorder(12, 12, 12, 12));

        // Top Search Bar
        add(createSearchPanel(), BorderLayout.NORTH);

        // Center Split: Left Form vs Right Table
        JPanel contentPanel = new JPanel(new BorderLayout(12, 0));
        contentPanel.setOpaque(false);

        contentPanel.add(createFormPanel(), BorderLayout.WEST);
        contentPanel.add(createTablePanel(), BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        // Bottom Status Bar
        lblStatus = new JLabel("Total Alumni Records: 0");
        lblStatus.setFont(UIStyle.FONT_SUBTITLE);
        lblStatus.setForeground(UIStyle.COLOR_TEXT_MUTED);
        lblStatus.setBorder(new EmptyBorder(6, 4, 2, 4));
        add(lblStatus, BorderLayout.SOUTH);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panel.setBackground(UIStyle.COLOR_CARD_BG);
        panel.setBorder(new CompoundBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                new EmptyBorder(4, 10, 4, 10)
        ));

        panel.add(UIStyle.createLabel("Search (Name/Org/City/ID):"));
        txtSearch = new JTextField(15);
        UIStyle.styleTextField(txtSearch);
        panel.add(txtSearch);

        panel.add(UIStyle.createLabel("Department:"));
        String[] filterDepts = new String[DEPARTMENTS.length + 1];
        filterDepts[0] = "All";
        System.arraycopy(DEPARTMENTS, 0, filterDepts, 1, DEPARTMENTS.length);
        cmbSearchDept = new JComboBox<>(filterDepts);
        UIStyle.styleComboBox(cmbSearchDept);
        panel.add(cmbSearchDept);

        btnSearch = UIStyle.createPrimaryButton("Search");
        btnReset = UIStyle.createSecondaryButton("Show All / Reset");

        btnSearch.addActionListener(e -> handleSearch());
        btnReset.addActionListener(e -> handleReset());
        txtSearch.addActionListener(e -> handleSearch());

        panel.add(btnSearch);
        panel.add(btnReset);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel formCard = UIStyle.createCardPanel();
        formCard.setLayout(new BorderLayout(0, 6));
        formCard.setPreferredSize(new Dimension(380, 520));

        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                " Alumni Information Details ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                UIStyle.FONT_LABEL,
                UIStyle.COLOR_PRIMARY
        );
        formCard.setBorder(new CompoundBorder(border, new EmptyBorder(4, 8, 6, 8)));

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 4, 3, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Alumni ID (Read-only)
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.35;
        fieldsPanel.add(UIStyle.createLabel("Alumni ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtAlumniId = new JTextField();
        txtAlumniId.setEditable(false);
        txtAlumniId.setBackground(new Color(241, 245, 249));
        UIStyle.styleTextField(txtAlumniId);
        fieldsPanel.add(txtAlumniId, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        fieldsPanel.add(UIStyle.createLabel("Full Name: *"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField();
        UIStyle.styleTextField(txtName);
        fieldsPanel.add(txtName, gbc);

        // Graduation Year
        gbc.gridx = 0; gbc.gridy = 2;
        fieldsPanel.add(UIStyle.createLabel("Grad Year: *"), gbc);
        gbc.gridx = 1;
        Integer[] years = new Integer[30];
        int startYear = 2026;
        for (int i = 0; i < 30; i++) {
            years[i] = startYear - i;
        }
        cmbGradYear = new JComboBox<>(years);
        UIStyle.styleComboBox(cmbGradYear);
        fieldsPanel.add(cmbGradYear, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 3;
        fieldsPanel.add(UIStyle.createLabel("Department: *"), gbc);
        gbc.gridx = 1;
        cmbDepartment = new JComboBox<>(DEPARTMENTS);
        UIStyle.styleComboBox(cmbDepartment);
        fieldsPanel.add(cmbDepartment, gbc);

        // Organization
        gbc.gridx = 0; gbc.gridy = 4;
        fieldsPanel.add(UIStyle.createLabel("Organization: *"), gbc);
        gbc.gridx = 1;
        txtOrganization = new JTextField();
        UIStyle.styleTextField(txtOrganization);
        fieldsPanel.add(txtOrganization, gbc);

        // Job Role
        gbc.gridx = 0; gbc.gridy = 5;
        fieldsPanel.add(UIStyle.createLabel("Job Role: *"), gbc);
        gbc.gridx = 1;
        txtJobRole = new JTextField();
        UIStyle.styleTextField(txtJobRole);
        fieldsPanel.add(txtJobRole, gbc);

        // City
        gbc.gridx = 0; gbc.gridy = 6;
        fieldsPanel.add(UIStyle.createLabel("Current City: *"), gbc);
        gbc.gridx = 1;
        txtCity = new JTextField();
        UIStyle.styleTextField(txtCity);
        fieldsPanel.add(txtCity, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 7;
        fieldsPanel.add(UIStyle.createLabel("Email: *"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField();
        UIStyle.styleTextField(txtEmail);
        fieldsPanel.add(txtEmail, gbc);

        // Phone
        gbc.gridx = 0; gbc.gridy = 8;
        fieldsPanel.add(UIStyle.createLabel("Phone: *"), gbc);
        gbc.gridx = 1;
        txtPhone = new JTextField();
        UIStyle.styleTextField(txtPhone);
        fieldsPanel.add(txtPhone, gbc);

        JScrollPane spFields = new JScrollPane(fieldsPanel);
        spFields.setBorder(null);
        spFields.setOpaque(false);
        spFields.getViewport().setOpaque(false);
        formCard.add(spFields, BorderLayout.CENTER);

        // Action Buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

        btnAdd = UIStyle.createSuccessButton("Add Alumni");
        btnUpdate = UIStyle.createPrimaryButton("Update Alumni");
        btnDelete = UIStyle.createDangerButton("Delete Alumni");
        btnClear = UIStyle.createSecondaryButton("Clear Form");

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        btnAdd.addActionListener(e -> handleAdd());
        btnUpdate.addActionListener(e -> handleUpdate());
        btnDelete.addActionListener(e -> handleDelete());
        btnClear.addActionListener(e -> clearForm());

        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnUpdate);
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(btnClear);

        formCard.add(buttonsPanel, BorderLayout.SOUTH);

        return formCard;
    }

    private JPanel createTablePanel() {
        JPanel tableCard = UIStyle.createCardPanel();
        tableCard.setLayout(new BorderLayout(0, 8));

        String[] cols = {"ID", "Name", "Year", "Department", "Organization", "Job Role", "City", "Email", "Phone"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        table = new JTable(tableModel);
        UIStyle.styleTable(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    populateFormFromRow(selectedRow);
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new LineBorder(UIStyle.COLOR_BORDER, 1));
        tableCard.add(sp, BorderLayout.CENTER);

        return tableCard;
    }

    private void populateFormFromRow(int row) {
        try {
            txtAlumniId.setText(tableModel.getValueAt(row, 0).toString());
            txtName.setText(tableModel.getValueAt(row, 1).toString());
            cmbGradYear.setSelectedItem(Integer.parseInt(tableModel.getValueAt(row, 2).toString()));
            cmbDepartment.setSelectedItem(tableModel.getValueAt(row, 3).toString());
            txtOrganization.setText(tableModel.getValueAt(row, 4).toString());
            txtJobRole.setText(tableModel.getValueAt(row, 5).toString());
            txtCity.setText(tableModel.getValueAt(row, 6).toString());
            txtEmail.setText(tableModel.getValueAt(row, 7).toString());
            txtPhone.setText(tableModel.getValueAt(row, 8).toString());

            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
            btnAdd.setEnabled(false);
        } catch (Exception ignored) {}
    }

    private void clearForm() {
        txtAlumniId.setText("");
        txtName.setText("");
        if (cmbGradYear.getItemCount() > 0) cmbGradYear.setSelectedIndex(0);
        if (cmbDepartment.getItemCount() > 0) cmbDepartment.setSelectedIndex(0);
        txtOrganization.setText("");
        txtJobRole.setText("");
        txtCity.setText("");
        txtEmail.setText("");
        txtPhone.setText("");

        table.clearSelection();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
        txtName.requestFocus();
    }

    private void handleAdd() {
        if (!validateInput()) return;

        Alumni a = extractAlumniFromForm();
        boolean success = alumniDAO.addAlumni(a);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Alumni record added successfully!\nGenerated ID: " + a.getAlumniId(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadTableData(alumniDAO.getAllAlumni());
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to add alumni record. Please check database connectivity.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String idStr = txtAlumniId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an alumni record from the table to update.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validateInput()) return;

        Alumni a = extractAlumniFromForm();
        a.setAlumniId(Integer.parseInt(idStr));

        boolean success = alumniDAO.updateAlumni(a);
        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Alumni record updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadTableData(alumniDAO.getAllAlumni());
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update alumni record.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDelete() {
        String idStr = txtAlumniId.getText().trim();
        String name = txtName.getText().trim();
        String org = txtOrganization.getText().trim();

        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an alumni record from the table to delete.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int alumniId = Integer.parseInt(idStr);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this alumni record?\n\nID: " + alumniId + "\nName: " + name + "\nOrganization: " + org,
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = alumniDAO.deleteAlumni(alumniId);
            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Alumni record deleted successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadTableData(alumniDAO.getAllAlumni());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to delete alumni record.",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateInput() {
        String name = txtName.getText().trim();
        String org = txtOrganization.getText().trim();
        String role = txtJobRole.getText().trim();
        String city = txtCity.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Alumni Name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            txtName.requestFocus();
            return false;
        }

        if (org.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Organization cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            txtOrganization.requestFocus();
            return false;
        }

        if (role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Job Role cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            txtJobRole.requestFocus();
            return false;
        }

        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            txtCity.requestFocus();
            return false;
        }

        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address (e.g. user@domain.com).", "Validation Error", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (phone.isEmpty() || !phone.matches("\\d{10,12}")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number (10-12 numeric digits).", "Validation Error", JOptionPane.WARNING_MESSAGE);
            txtPhone.requestFocus();
            return false;
        }

        return true;
    }

    private Alumni extractAlumniFromForm() {
        String name = txtName.getText().trim();
        int year = (Integer) cmbGradYear.getSelectedItem();
        String dept = (String) cmbDepartment.getSelectedItem();
        String org = txtOrganization.getText().trim();
        String role = txtJobRole.getText().trim();
        String city = txtCity.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        return new Alumni(name, year, dept, org, role, city, email, phone);
    }

    private void handleSearch() {
        String keyword = txtSearch.getText().trim();
        String dept = (String) cmbSearchDept.getSelectedItem();

        List<Alumni> results = alumniDAO.searchAlumni(keyword, dept);
        loadTableData(results);
    }

    private void handleReset() {
        txtSearch.setText("");
        cmbSearchDept.setSelectedIndex(0);
        clearForm();
        loadTableData(alumniDAO.getAllAlumni());
    }

    public void refreshData() {
        handleReset();
    }

    private void loadTableData(List<Alumni> list) {
        tableModel.setRowCount(0);
        for (Alumni a : list) {
            tableModel.addRow(new Object[]{
                    a.getAlumniId(),
                    a.getName(),
                    a.getGraduationYear(),
                    a.getDepartment(),
                    a.getOrganization(),
                    a.getJobRole(),
                    a.getCity(),
                    a.getEmail(),
                    a.getPhone()
            });
        }
        lblStatus.setText("Total Alumni Records: " + list.size() + " | Click any row to edit or delete.");
    }
}
