package com.example.a05_homework;

public class UserModel {
    private final int avatarId;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String country;
    private final String city;

    public UserModel(int avatarId, String firstName, String lastName, int age, String country, String city) {
        this.avatarId = avatarId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}

