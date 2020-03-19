package by.sivko.resume.entities;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("мужчина"), FEMALE("женщина");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender fromName(String name) {
        switch (name) {
            case "мужчина":
                return Gender.MALE;
            case "женщина":
                return Gender.FEMALE;
            default:
                throw new IllegalArgumentException(String.format("name [%s] not supported.", name));
        }
    }
}
