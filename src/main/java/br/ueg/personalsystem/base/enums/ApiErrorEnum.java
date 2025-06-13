package br.ueg.personalsystem.base.enums;

import lombok.Getter;

@Getter
public enum ApiErrorEnum implements MessageCode {
    GENERAL("MESSAGE.GENERAL.M1", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    NOT_FOUND("MESSAGE.GENERAL.M2", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    PARAMETER_REQUIRED("MESSAGE.GENERAL.M3", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    LOGIN_INVALID("MESSAGE.LOGIN.M1", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    USER_PASSWORD_NOT_MATCH("MESSAGE.LOGIN.M2", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    INACTIVE_USER("MESSAGE.LOGIN.M3", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    INVALID_TOKEN("MESSAGE.LOGIN.M4", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    EXPIRED_TOKEN("MESSAGE.LOGIN.M5", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    MANDATORY_FIELD("MESSAGE.GENERAL.M4", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    ACCESS_DENIED("MESSAGE.LOGIN.M6", br.ueg.personalsystem.base.enums.MessageType.ERROR),
    INVALID_RESET_CODE("MESSAGE.LOGIN.M7", br.ueg.personalsystem.base.enums.MessageType.ERROR);

    private final String code;
    private final br.ueg.personalsystem.base.enums.MessageType type;

    ApiErrorEnum(String code, MessageType type){
        this.code = code;
        this.type = type;
    }
}
