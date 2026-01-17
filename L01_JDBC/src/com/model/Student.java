package com.model;

public class Student {

    private int studentId;
    private String studentName;
    private String standard;
    private String section;
    private Address studentAddress;

    public Student(int studentId, String studentName, String standard, String section, Address studentAddress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.standard = standard;
        this.section = section;
        this.studentAddress = studentAddress;
    }

    public Student() {

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", standard='" + standard + '\'' +
                ", section='" + section + '\'' +
                ", studentAddress=" + studentAddress +
                '}';
    }
}
