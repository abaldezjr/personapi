package one.digitalinovation.personapi.controller;

import one.digitalinovation.personapi.dto.MessageResponseDTO;
import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.exception.PersonNotFoundException;
import one.digitalinovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return this.personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> findAll(){
        return this.personService.findAll();
    }

    @GetMapping("/{idPerson}")
    public PersonDTO findById(@PathVariable Long idPerson) throws PersonNotFoundException {
        return this.personService.findById(idPerson);
    }

    @DeleteMapping("/{idPerson}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long idPerson) throws PersonNotFoundException {
        this.personService.deletePerson(idPerson);
    }

    @PutMapping("/{idPerson}")
    public MessageResponseDTO updatePerson(@PathVariable Long idPerson,@RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return this.personService.updatePerson(idPerson, personDTO);
    }

}
