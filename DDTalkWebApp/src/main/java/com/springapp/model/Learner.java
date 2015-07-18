package com.springapp.model;


import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="learners")
public class Learner {

    @Id
    @GeneratedValue
    @Column(name = "learner_id")
    Long learnerId;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    Date dob;
    String grade;
    String school;
    @Column(name = "student_id")
    String studentId;
    @Column(name = "date_initial_eval")
    Date dateIntialEval;
    String status;

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getDateIntialEval() {
        return dateIntialEval;
    }

    public void setDateIntialEval(Date dateIntialEval) {
        this.dateIntialEval = dateIntialEval;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
