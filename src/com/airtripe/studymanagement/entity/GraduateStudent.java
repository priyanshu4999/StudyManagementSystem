package com.airtripe.studymanagement.entity;

import java.time.LocalDate;

public class GraduateStudent extends Student{

    public LocalDate graduationDate;
    public GraduateStudent(String name , String id , LocalDate graduationDate){
        super(name , id);
        this.graduationDate = graduationDate;
    }
    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }
    @Override
    public String getRole(){
        return "GraduateStudent";
    }

}
