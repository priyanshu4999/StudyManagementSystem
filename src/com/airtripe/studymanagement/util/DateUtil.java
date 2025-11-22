package com.airtripe.studymanagement.util;

import java.time.LocalDateTime;

public class DateUtil {

    private final LocalDateTime dateTime;
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private DateUtil(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public static class Builder{
        private int sec = 0;
        private int minute = 0;
        private int hour = 0;

        private int date = 1;
        private int month= 1;
        private int year = 2000;

        public Builder year(int year){
            this.year = year;
            return this;

        }
        public Builder month(int month){
            this.month = month;
            return this;
        }
        public Builder date(int date){
            this.date = date;
            return this;
        }

        public Builder sec(int sec){
            this.sec = sec;
            return this;
        }
        public Builder minute(int minute){
            this.minute = minute;
            return this;
        }
        public Builder hour(int hour){
            this.hour = hour;
            return this;
        }
        public DateUtil build(){
            LocalDateTime ldt = LocalDateTime.of(year,month,date,hour,minute,sec);
            return new DateUtil(ldt);
        }

    }

}
