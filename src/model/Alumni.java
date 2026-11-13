package model;

import java.sql.Timestamp;

/**
 * Model class representing an Alumni profile record in the directory.
 */
public class Alumni {
    private int alumniId;
    private String name;
    private int graduationYear;
    private String department;
    private String organization;
    private String jobRole;
    private String city;
    private String email;
    private String phone;
    private Timestamp createdAt;

    public Alumni() {
    }

    public Alumni(int alumniId, String name, int graduationYear, String department, 
                  String organization, String jobRole, String city, String email, String phone, Timestamp createdAt) {
        this.alumniId = alumniId;
        this.name = name;
        this.graduationYear = graduationYear;
        this.department = department;
        this.organization = organization;
        this.jobRole = jobRole;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Alumni(String name, int graduationYear, String department, 
                  String organization, String jobRole, String city, String email, String phone) {
        this.name = name;
        this.graduationYear = graduationYear;
        this.department = department;
        this.organization = organization;
        this.jobRole = jobRole;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public int getAlumniId() {
        return alumniId;
    }

    public void setAlumniId(int alumniId) {
        this.alumniId = alumniId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return name + " (" + department + " - " + graduationYear + ") - " + organization;
    }
}
