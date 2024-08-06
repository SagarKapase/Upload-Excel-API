package com.exceldata.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private Integer stuId;
    private String firstName;
    private String lastName;
    private String college;
    private String placementStatus;
    private String companyName;

    public Student(Integer stuId, String firstName, String lastName, String college, String placementStatus, String companyName) {
        this.stuId = stuId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.college = college;
        this.placementStatus = placementStatus;
        this.companyName = companyName;
    }

    public Student() {
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(String placementStatus) {
        this.placementStatus = placementStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
