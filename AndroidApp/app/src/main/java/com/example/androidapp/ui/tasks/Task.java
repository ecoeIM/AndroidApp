package com.example.androidapp.ui.tasks;

import java.sql.Time;
import java.time.DateTimeException;
import java.util.Date;

public class Task {
    String title;
    Date date;
    Time time;
    String light;
    String vent;

    public Date getDate() {
        return date;
    }

    public String getLight() {
        return light;
    }

    public String getTitle() {
        return title;
    }

    public String getVent() {
        return vent;
    }

    public Time getTime() {
        return time;
    }
}
