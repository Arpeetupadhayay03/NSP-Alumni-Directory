import db.DBConnection;
import ui.LoginFrame;

import javax.swing.*;

/**
 * Main application entry point for the Alumni Directory & Networking Management System.
 *
 * PIET Minor Project (NSP) - B.Tech II Year (III Semester)
 * Student Name: ARPEET UPADHAYAY (PIET25CR007)
 * College: Poornima Institute of Engineering & Technology
 */
public class Main {

    public static void main(String[] args) {
        // Set Look and Feel to System Look and Feel for native, crisp rendering
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("[Main] Could not set system look and feel: " + e.getMessage());
        }

        // Test database connectivity
        System.out.println("==========================================================");
        System.out.println("  Alumni Directory & Networking System - PIET (NSP)       ");
        System.out.println("  Student: ARPEET UPADHAYAY (PIET25CR007)                ");
        System.out.println("  Section: 3CSD | Session: 2026-27                       ");
        System.out.println("==========================================================");

        boolean dbConnected = DBConnection.testConnection();
        if (dbConnected) {
            System.out.println("[DBConnection] Connection to alumni_db successful!");
        } else {
            System.err.println("[DBConnection] Warning: Could not connect to MySQL. Ensure service is active.");
        }

        // Launch Login UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
