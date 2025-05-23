package br.ueg.personalsystem.dto.request;

import br.ueg.personalsystem.base.annotation.MandatoryField;
import br.ueg.personalsystem.annotation.EmailValidate;
import br.ueg.personalsystem.annotation.PasswordValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @MandatoryField(name = "Nome")
    private String name;

    @MandatoryField(name = "Login")
    private String login;

    @MandatoryField(name = "Senha")
    @PasswordValidate(name = "Senha")
    private String password;

    @MandatoryField(name = "Confirmação de Senha")
    private String confirmPassword;

    @MandatoryField(name = "Email")
    @EmailValidate(name = "Email")
    private String email;

    @MandatoryField(name = "Nome")
    private Boolean enabled;
}
