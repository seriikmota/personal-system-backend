package br.ueg.personalsystem.base.enums;

import lombok.Getter;

@Getter
public enum ApiErrorEnum implements MessageCode {
    GENERAL("MEA001", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    NOT_FOUND("MEA002", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    PARAMETER_REQUIRED("MEA003", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    LOGIN_INVALID("MEA004", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    USER_PASSWORD_NOT_MATCH("MEA005", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    INACTIVE_USER("MEA006", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    INVALID_TOKEN("MEA007", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    EXPIRED_TOKEN("MEA008", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    MANDATORY_FIELD("MEA009", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    ACCESS_DENIED("MEA010", br.ueg.personalsystem.base.enums.MessageType.ERROR);

    private final String code;
    private final br.ueg.personalsystem.base.enums.MessageType type;

    ApiErrorEnum(String code, MessageType type){
        this.code = code;
        this.type = type;
    }
}
