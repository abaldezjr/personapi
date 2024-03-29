package one.digitalinovation.personapi.mapper;

import one.digitalinovation.personapi.dto.request.PhoneDTO;
import one.digitalinovation.personapi.entity.Phone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneMapper {

    public Phone toEntity(PhoneDTO phoneDTO){
        return Phone.builder()
                .id(phoneDTO.getId())
                .type(phoneDTO.getType())
                .number(phoneDTO.getNumber())
                .build();
    }

    public List<Phone> toEntity(List<PhoneDTO> phonesDTO){
        return phonesDTO.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

    }

    public PhoneDTO toDTO(Phone phone){
        return PhoneDTO.builder()
                .id(phone.getId())
                .type(phone.getType())
                .number(phone.getNumber())
                .build();
    }

    public List<PhoneDTO> toDTO(List<Phone> phones){
        return phones.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
