package com.airtripe.studymanagement.entity;

import com.airtripe.studymanagement.util.DateUtil;

public abstract class Fields {
    String name;
    String description;
    DateUtil enrollmentdate;
    DateUtil exitdate;
    boolean active;
}
