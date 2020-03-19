package by.sivko.resume.convertors;

import by.sivko.resume.entities.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getName();
    }

    @Override
    public Gender convertToEntityAttribute(String name) {
        return Gender.fromName(name);
    }
}
