package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * UIStyle defines the unified color palette, fonts, and component styling
 * for the Alumni Directory & Networking Management System.
 *
 * Designed to guarantee 100% button text visibility and consistent,
 * polished presentation across all OS platforms and Look-and-Feels.
 */
public class UIStyle {

    // Primary Brand Colors (Academic Navy / Professional Canteen Palette)
    public static final Color COLOR_PRIMARY = new Color(26, 54, 93);       // Deep Navy
    public static final Color COLOR_PRIMARY_HOVER = new Color(43, 84, 143);
    public static final Color COLOR_ACCENT = new Color(37, 99, 235);        // Royal Blue
    public static final Color COLOR_SUCCESS = new Color(16, 149, 93);       // Emerald Green
    public static final Color COLOR_DANGER = new Color(220, 38, 38);        // Crimson Red
    public static final Color COLOR_WARNING = new Color(217, 119, 6);       // Amber
    public static final Color COLOR_SECONDARY = new Color(100, 116, 139);   // Slate Gray

    // Background & Surface Colors
    public static final Color COLOR_BG = new Color(241, 245, 249);          // Light Gray / Slate 100
    public static final Color COLOR_CARD_BG = Color.WHITE;                  // Card Background
    public static final Color COLOR_HEADER_BG = new Color(15, 23, 42);      // Slate 900
    public static final Color COLOR_BORDER = new Color(203, 213, 225);       // Slate 300
    public static final Color COLOR_ROW_ALT = new Color(248, 250, 252);     // Slate 50

    // Text Colors
    public static final Color COLOR_TEXT_PRIMARY = new Color(15, 23, 42);   // Dark Slate
    public static final Color COLOR_TEXT_MUTED = new Color(100, 116, 139);  // Muted Slate
    public static final Color COLOR_TEXT_WHITE = Color.WHITE;

    // Standard Typography
    public static final Font FONT_HEADER_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_SECTION_TITLE = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TABLE_CELL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BADGE = new Font("Segoe UI", Font.BOLD, 11);

    /**
     * Styles a button with explicit background, foreground, font, and borders
     * to guarantee text visibility on all systems.
     */
    public static JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BUTTON);
        btn.setBackground(bgColor);
        btn.setForeground(fgColor);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 16, 8, 16));
        return btn;
    }

    public static JButton createPrimaryButton(String text) {
        return createStyledButton(text, COLOR_PRIMARY, COLOR_TEXT_WHITE);
    }

    public static JButton createSuccessButton(String text) {
        return createStyledButton(text, COLOR_SUCCESS, COLOR_TEXT_WHITE);
    }

    public static JButton createDangerButton(String text) {
        return createStyledButton(text, COLOR_DANGER, COLOR_TEXT_WHITE);
    }

    public static JButton createSecondaryButton(String text) {
        return createStyledButton(text, COLOR_SECONDARY, COLOR_TEXT_WHITE);
    }

    public static JButton createAccentButton(String text) {
        return createStyledButton(text, COLOR_ACCENT, COLOR_TEXT_WHITE);
    }

    /**
     * Styles text input fields with clean borders and padding.
     */
    public static void styleTextField(JTextField textField) {
        textField.setFont(FONT_REGULAR);
        textField.setForeground(COLOR_TEXT_PRIMARY);
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(COLOR_TEXT_PRIMARY);
        Border line = new LineBorder(COLOR_BORDER, 1);
        Border pad = new EmptyBorder(6, 10, 6, 10);
        textField.setBorder(new CompoundBorder(line, pad));
    }

    /**
     * Styles combo boxes.
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(FONT_REGULAR);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(COLOR_TEXT_PRIMARY);
    }

    /**
     * Styles standard labels.
     */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FONT_LABEL);
        label.setForeground(COLOR_TEXT_PRIMARY);
        return label;
    }

    /**
     * Styles JTable headers and cell alignment with 100% visible headers across all OS platforms.
     */
    public static void styleTable(JTable table) {
        table.setFont(FONT_TABLE_CELL);
        table.setRowHeight(28);
        table.setGridColor(new Color(226, 232, 240));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(219, 234, 254)); // Light blue highlight
        table.setSelectionForeground(COLOR_TEXT_PRIMARY);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 34));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                label.setBackground(COLOR_PRIMARY);
                label.setForeground(COLOR_TEXT_WHITE);
                label.setFont(FONT_TABLE_HEADER);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(59, 130, 246)),
                        BorderFactory.createEmptyBorder(6, 6, 6, 6)
                ));
                return label;
            }
        };
        header.setDefaultRenderer(headerRenderer);

        // Center-align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Creates a card-style JPanel with a subtle border and margin.
     */
    public static JPanel createCardPanel() {
        JPanel card = new JPanel();
        card.setBackground(COLOR_CARD_BG);
        Border line = new LineBorder(COLOR_BORDER, 1, true);
        Border pad = new EmptyBorder(12, 12, 12, 12);
        card.setBorder(new CompoundBorder(line, pad));
        return card;
    }

    /**
     * Creates a header banner panel displaying PIET project and student details.
     */
    public static JPanel createBannerPanel(String title, String subtitle) {
        JPanel banner = new JPanel(new BorderLayout(15, 0));
        banner.setBackground(COLOR_HEADER_BG);
        banner.setBorder(new EmptyBorder(12, 20, 12, 20));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        textPanel.setOpaque(false);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(FONT_HEADER_TITLE);
        lblTitle.setForeground(COLOR_TEXT_WHITE);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(FONT_SUBTITLE);
        lblSubtitle.setForeground(new Color(148, 163, 184)); // Slate 400

        textPanel.add(lblTitle);
        textPanel.add(lblSubtitle);

        banner.add(textPanel, BorderLayout.CENTER);

        // Right badge for PIET Minor Project
        JPanel badgePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 6));
        badgePanel.setOpaque(false);
        JLabel badge = new JLabel("PIET Minor Project (NSP)");
        badge.setFont(FONT_BADGE);
        badge.setForeground(new Color(186, 230, 253));
        badge.setBorder(new CompoundBorder(
                new LineBorder(new Color(56, 189, 248), 1, true),
                new EmptyBorder(4, 10, 4, 10)
        ));
        badgePanel.add(badge);
        banner.add(badgePanel, BorderLayout.EAST);

        return banner;
    }
}
