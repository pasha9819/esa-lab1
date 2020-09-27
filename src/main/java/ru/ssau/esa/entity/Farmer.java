package ru.ssau.esa.entity;


import java.util.List;

public class Farmer extends NamedLongIdEntity{

    private String surname;
    private List<Animal> animalList;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

}
