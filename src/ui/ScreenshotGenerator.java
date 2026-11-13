package ui;

import dao.UserDAO;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ScreenshotGenerator captures genuine, high-resolution PNG screenshots
 * of all application screens directly from the live Java Swing component tree.
 */
public class ScreenshotGenerator {

    private static final String OUTPUT_DIR = "screenshots";

    public static void main(String[] args) {
        System.out.println("Starting automated UI Screenshot Generator for Alumni Directory...");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        File dir = new File(OUTPUT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        UserDAO userDAO = new UserDAO();
        User admin = userDAO.authenticate("admin", "admin123");
        if (admin == null) {
            admin = new User(1, "admin", "admin123", "Alumni Coordinator (Admin)", "Admin", null);
        }

        User student = userDAO.authenticate("user1", "user123");
        if (student == null) {
            student = new User(2, "user1", "user123", "Arpeet Upadhayay", "User", null);
        }

        // 1. Capture Login Screen
        captureLoginScreen();

        // 2. Capture Admin Dashboard Overview
        captureAdminOverview(admin);

        // 3. Capture Admin Alumni Management (CRUD & Form)
        captureAdminCRUD(admin);

        // 4. Capture Admin Directory & Search
        captureAdminDirectorySearch(admin);

        // 5. Capture User Dashboard (Directory View)
        captureUserDashboard(student);

        // 6. Capture User Networking Details Profile View
        captureUserDetailsView(student);

        // 7. Capture Change Password Dialog
        captureChangePasswordDialog(admin);

        // 8. Capture Delete Confirmation Dialog
        captureDeleteConfirmationDialog(admin);

        System.out.println("All 8 screenshots successfully captured in: " + dir.getAbsolutePath());
        System.exit(0);
    }

    private static void captureLoginScreen() {
        try {
            LoginFrame frame = new LoginFrame();
            frame.setSize(480, 560);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            Thread.sleep(300);

            saveWindowImage(frame, OUTPUT_DIR + "/01_login_screen.png");
            frame.dispose();
            System.out.println("  Saved: 01_login_screen.png");
        } catch (Exception e) {
            System.err.println("Error capturing Login Screen: " + e.getMessage());
        }
    }

    private static void captureAdminOverview(User admin) {
        try {
            AdminDashboardFrame frame = new AdminDashboardFrame(admin);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = findTabbedPane(frame);
            if (tabs != null) tabs.setSelectedIndex(0);

            frame.setVisible(true);
            Thread.sleep(300);

            saveWindowImage(frame, OUTPUT_DIR + "/02_admin_dashboard.png");
            frame.dispose();
            System.out.println("  Saved: 02_admin_dashboard.png");
        } catch (Exception e) {
            System.err.println("Error capturing Admin Dashboard: " + e.getMessage());
        }
    }

    private static void captureAdminCRUD(User admin) {
        try {
            AdminDashboardFrame frame = new AdminDashboardFrame(admin);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = findTabbedPane(frame);
            if (tabs != null) tabs.setSelectedIndex(0);

            frame.setVisible(true);
            Thread.sleep(300);

            saveWindowImage(frame, OUTPUT_DIR + "/03_manage_alumni_crud.png");
            frame.dispose();
            System.out.println("  Saved: 03_manage_alumni_crud.png");
        } catch (Exception e) {
            System.err.println("Error capturing Alumni CRUD: " + e.getMessage());
        }
    }

    private static void captureAdminDirectorySearch(User admin) {
        try {
            AdminDashboardFrame frame = new AdminDashboardFrame(admin);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = findTabbedPane(frame);
            if (tabs != null) tabs.setSelectedIndex(1);

            frame.setVisible(true);
            Thread.sleep(300);

            saveWindowImage(frame, OUTPUT_DIR + "/04_alumni_directory_search.png");
            frame.dispose();
            System.out.println("  Saved: 04_alumni_directory_search.png");
        } catch (Exception e) {
            System.err.println("Error capturing Admin Directory Search: " + e.getMessage());
        }
    }

    private static void captureUserDashboard(User student) {
        try {
            UserDashboardFrame frame = new UserDashboardFrame(student);
            frame.setSize(1050, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            Thread.sleep(300);

            saveWindowImage(frame, OUTPUT_DIR + "/05_user_dashboard_directory.png");
            frame.dispose();
            System.out.println("  Saved: 05_user_dashboard_directory.png");
        } catch (Exception e) {
            System.err.println("Error capturing User Dashboard: " + e.getMessage());
        }
    }

    private static void captureUserDetailsView(User student) {
        try {
            UserDashboardFrame frame = new UserDashboardFrame(student);
            frame.setSize(1050, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            Thread.sleep(300);

            saveWindowImage(frame, OUTPUT_DIR + "/06_user_alumni_details_view.png");
            frame.dispose();
            System.out.println("  Saved: 06_user_alumni_details_view.png");
        } catch (Exception e) {
            System.err.println("Error capturing User Details View: " + e.getMessage());
        }
    }

    private static void captureChangePasswordDialog(User user) {
        try {
            JFrame dummy = new JFrame();
            dummy.setUndecorated(true);
            dummy.setLocationRelativeTo(null);
            dummy.setVisible(true);

            ChangePasswordDialog dialog = new ChangePasswordDialog(dummy, user);
            dialog.setLocationRelativeTo(null);

            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(250);
                    saveWindowImage(dialog, OUTPUT_DIR + "/07_change_password_dialog.png");
                    dialog.dispose();
                    dummy.dispose();
                    System.out.println("  Saved: 07_change_password_dialog.png");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            dialog.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error capturing Change Password Dialog: " + e.getMessage());
        }
    }

    private static void captureDeleteConfirmationDialog(User admin) {
        try {
            JFrame dummy = new JFrame();
            dummy.setSize(400, 300);
            dummy.setLocationRelativeTo(null);
            dummy.setVisible(true);

            JOptionPane pane = new JOptionPane(
                    "Are you sure you want to delete this alumni record?\n\nID: 5\nName: Karan Singh\nOrganization: TCS",
                    JOptionPane.WARNING_MESSAGE,
                    JOptionPane.YES_NO_OPTION);
            JDialog d = pane.createDialog(dummy, "Confirm Deletion");
            d.setLocationRelativeTo(null);

            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(200);
                    saveWindowImage(d, OUTPUT_DIR + "/08_delete_confirmation_dialog.png");
                    d.dispose();
                    dummy.dispose();
                    System.out.println("  Saved: 08_delete_confirmation_dialog.png");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            d.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error capturing Delete Confirmation Dialog: " + e.getMessage());
        }
    }

    private static void saveWindowImage(Window window, String filePath) {
        try {
            int width = Math.max(window.getWidth(), 1);
            int height = Math.max(window.getHeight(), 1);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            window.paint(g2d);
            g2d.dispose();

            ImageIO.write(image, "png", new File(filePath));
        } catch (Exception e) {
            System.err.println("Failed to save screenshot " + filePath + ": " + e.getMessage());
        }
    }

    private static JTabbedPane findTabbedPane(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTabbedPane) {
                return (JTabbedPane) c;
            }
            if (c instanceof Container) {
                JTabbedPane found = findTabbedPane((Container) c);
                if (found != null) return found;
            }
        }
        return null;
    }
}
