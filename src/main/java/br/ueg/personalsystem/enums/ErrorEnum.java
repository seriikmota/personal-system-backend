package br.ueg.personalsystem.enums;

import br.ueg.genericarchitecture.enums.MessageCode;
import br.ueg.genericarchitecture.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum implements MessageCode {
    MANDATORY_FIELD("M1", MessageType.ERROR),
    PASSWORDS_DIFFERENT("M2", MessageType.ERROR),
    PASSWORD_INVALID("M3", MessageType.ERROR),
    EMAIL_INVALID("M4", MessageType.ERROR),
    EMAIL_EXISTS("M5", MessageType.ERROR),
    LOGIN_EXISTS("M6", MessageType.ERROR),
    PASSWORD_MIN_LENGTH("M7", MessageType.ERROR),
    PASSWORD_NUM_LETTER("M8", MessageType.ERROR),
    NUMBER_PHONE_INVALID("M9", MessageType.ERROR),
    BIRTH_DAY_IS_AFTER("M10", MessageType.ERROR),
    CPF_EXIST("M11", MessageType.ERROR),
    CPF_INVALID("M12", MessageType.ERROR),
    PATIENT_VALUE_HOUR_INVALID("M13", MessageType.ERROR),
    CEP_INVALID("M14", MessageType.ERROR),;

    private final String code;
    private final MessageType type;
}
