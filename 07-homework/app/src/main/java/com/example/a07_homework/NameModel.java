package com.example.a07_homework;

public class NameModel {
    private String name;
    private String nameDays;
    private String description;

    public NameModel(String name, String nameDays, String description) {
        this.name = name;
        this.nameDays = nameDays;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getNameDays() {
        return nameDays;
    }

    public String getDescription() {
        return description;
    }
}

