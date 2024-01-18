package com.brude.springboot.jpa.springbootjpa.dtos;

public class PersonDto {

    private String firstName;
    private String lastName;


    public PersonDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "PersonDto [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    
    
}