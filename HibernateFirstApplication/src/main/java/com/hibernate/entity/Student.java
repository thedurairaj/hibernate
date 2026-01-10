package com.hibernate.entity;

public class Student {

    private Integer id;
    private String sName;
    private String gender;
    private Integer mark;

    public Student(Integer id, String sName, String gender, Integer mark) {
        this.id = id;
        this.sName = sName;
        this.gender = gender;
        this.mark = mark;
    }

    public Student() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
