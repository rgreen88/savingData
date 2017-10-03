package com.example.rynel.savingdata;

/**
 * Created by rynel on 10/2/2017.
 */

public class Person {

    private String name, gender, age;

    public Person(String name, String age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
