package one.digitalinovation.personapi.service;

import one.digitalinovation.personapi.dto.MessageResponseDTO;
import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.entity.Person;
import one.digitalinovation.personapi.exception.PersonNotFoundException;
import one.digitalinovation.personapi.mapper.PersonMapper;
import one.digitalinovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person savedPerson = this.personRepository.save(this.personMapper.toEntity(personDTO));
        return MessageResponseDTO.builder()
                .message("Person created with ID = "+ savedPerson.getId())
                .build();
    }

    public List<PersonDTO> findAll() {
        return this.personMapper.toDTO(this.personRepository.findAll());
    }

    public PersonDTO findById(Long idPerson) throws PersonNotFoundException {
        return this.personMapper.toDTO(verifyIfExists(idPerson));
    }

    public void deletePerson(Long idPerson) throws PersonNotFoundException {
        this.personRepository.delete(verifyIfExists(idPerson));
    }

    public Person verifyIfExists(Long idPerson) throws PersonNotFoundException{
        return this.personRepository.findById(idPerson)
                .orElseThrow(() -> new PersonNotFoundException(idPerson));
    }

}
