package com.airtripe.studymanagement.entity;

import com.airtripe.studymanagement.util.DateUtil;

public abstract class Person {
    private String name;
    private String id;
    private String email;
    private Role role;
    private String contactNumber;
    private boolean active;
    private float yoe;
    private DateUtil enrollmentdate;
    private DateUtil exitdate;
    private Fields[] courses;


}

enum Role {
    MENTOR,
    MENTEE,
    ADMIN,
    SUPPORT
}
