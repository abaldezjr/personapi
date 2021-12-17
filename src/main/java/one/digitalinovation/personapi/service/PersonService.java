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
        Person personSaved = this.personRepository.save(this.personMapper.toEntity(personDTO));
        return createMessageResponse("Person created with ID = ", personSaved.getId());
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

    public MessageResponseDTO updatePerson(Long idPerson, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(idPerson);
        Person updatedPerson = this.personRepository.save(this.personMapper.toEntity(personDTO));
        return createMessageResponse("Person updated with ID = ", updatedPerson.getId());
    }

    private Person verifyIfExists(Long idPerson) throws PersonNotFoundException{
        return this.personRepository.findById(idPerson)
                .orElseThrow(() -> new PersonNotFoundException(idPerson));
    }

    private MessageResponseDTO createMessageResponse(String message, Long id){
        return MessageResponseDTO.builder().message(message + id).build();
    }
}
