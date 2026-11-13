-- ==========================================================
-- PIET Minor Project (NSP) - 2026-27
-- Project Title: Alumni Directory & Networking Management System
-- Student Name: ARPEET UPADHAYAY (PIET25CR007)
-- Section: 3CSD | College: Poornima Institute of Engineering & Technology
-- Database Script: schema.sql
-- Compatible with: MySQL Server 8.0+ / 8.4+ & MySQL Workbench
-- ==========================================================

DROP DATABASE IF EXISTS alumni_db;
CREATE DATABASE alumni_db;
USE alumni_db;

-- ----------------------------------------------------------
-- 1. Table: users
-- Stores authentication credentials and role permissions
-- ----------------------------------------------------------
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('Admin', 'User') NOT NULL DEFAULT 'User',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------------------------------------
-- 2. Table: alumni
-- Stores structured alumni profile records for networking
-- ----------------------------------------------------------
CREATE TABLE alumni (
    alumni_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    graduation_year INT NOT NULL,
    department VARCHAR(50) NOT NULL,
    organization VARCHAR(100) NOT NULL,
    job_role VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================================
-- Sample Seed Data
-- ==========================================================

-- Insert System Users (1 Admin, 3 Customers/Students)
INSERT INTO users (username, password, full_name, role) VALUES
('admin', 'admin123', 'Alumni Coordinator (Admin)', 'Admin'),
('user1', 'user123', 'Arpeet Upadhayay', 'User'),
('user2', 'user123', 'Saurabh Sharma', 'User'),
('user3', 'user123', 'Neha Gupta', 'User');

-- Insert Realistic Alumni Directory Records
INSERT INTO alumni (name, graduation_year, department, organization, job_role, city, email, phone) VALUES
('Rohit Verma', 2019, 'Computer Science', 'Google', 'Senior Software Engineer', 'Bengaluru', 'rohit.verma@gmail.com', '9829012345'),
('Pooja Choudhary', 2020, 'Information Tech', 'Microsoft', 'Cloud Solutions Architect', 'Hyderabad', 'pooja.choudhary@outlook.com', '9829023456'),
('Aman Mathur', 2018, 'Computer Science', 'Amazon', 'Software Development Engineer II', 'Bengaluru', 'aman.mathur@amazon.com', '9829034567'),
('Divya Sharma', 2021, 'AI & Data Science', 'Deloitte', 'Data Scientist & Consultant', 'Gurugram', 'divya.sharma@deloitte.com', '9829045678'),
('Karan Singh', 2019, 'Electronics & Comm', 'TCS', 'Systems Engineer', 'Pune', 'karan.singh@tcs.com', '9829056789'),
('Sneha Agarwal', 2022, 'Computer Science', 'Infosys', 'Associate Consultant', 'Jaipur', 'sneha.agarwal@infosys.com', '9829067890'),
('Vikas Meena', 2020, 'Mechanical Engg', 'L&T', 'Project Design Engineer', 'Mumbai', 'vikas.meena@larsentoubro.com', '9829078901'),
('Ananya Jain', 2023, 'AI & Data Science', 'Capgemini', 'Data Analyst', 'Noida', 'ananya.jain@capgemini.com', '9829089012'),
('Harshvardhan Rathore', 2021, 'Civil Engg', 'Tata Projects', 'Site Operations Manager', 'Jaipur', 'harsh.rathore@tataprojects.com', '9829090123'),
('Ritu Shekhawat', 2022, 'Information Tech', 'Wipro', 'DevOps Specialist', 'Pune', 'ritu.shekhawat@wipro.com', '9829101234');
