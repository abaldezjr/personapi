package one.digitalinovation.personapi.mapper;

import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    @Autowired
    private PhoneMapper phoneMapper;

    public Person toEntity(PersonDTO personDTO){
        return Person.builder()
                .id(personDTO.getId())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .cpf(personDTO.getCpf())
                .birthDate(personDTO.getBirthDate())
                .phones(phoneMapper.toEntity(personDTO.getPhones())).build();
    }

    public List<Person> toEntity(List<PersonDTO> personsDTO){
        return personsDTO.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public PersonDTO toDTO(Person person){
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(person.getBirthDate())
                .phones(phoneMapper.toDTO(person.getPhones()))
                .build();
    }

    public List<PersonDTO> toDTO(List<Person> persons){
        return persons.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}