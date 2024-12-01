package br.ueg.personalsystem.dto.request;

import br.ueg.genericarchitecture.annotation.MandatoryField;
import br.ueg.personalsystem.annotation.CEPValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    @MandatoryField(name = "cep")
    @CEPValidate(name = "cep")
    private String cep;

    @MandatoryField(name = "rua")
    private String street;

    private String number;

    private String complement;

    @MandatoryField(name = "bairro")
    private String neighborhood;

    @MandatoryField(name = "cidade")
    private String city;

    @MandatoryField(name = "estado")
    private String state;
}
