package one.digitalinovation.personapi.service;

import one.digitalinovation.personapi.dto.MessageResponseDTO;
import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.entity.Person;
import one.digitalinovation.personapi.mapper.PersonMapper;
import one.digitalinovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        PersonMapper personMapper = new PersonMapper();
        Person savedPerson = this.personRepository.save(personMapper.toEntity(personDTO));
        return MessageResponseDTO.builder()
                .message("Person created with ID = "+ savedPerson.getId())
                .build();
    }

    public List<PersonDTO> findAll() {
        PersonMapper personMapper = new PersonMapper();
        return personMapper.toDTO(personRepository.findAll());
    }
}
