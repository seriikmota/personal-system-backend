package br.ueg.personalsystem.reflection;

import br.ueg.genericarchitecture.reflection.ApiReflectionUtils;
import br.ueg.personalsystem.annotation.EmailValidate;
import br.ueg.personalsystem.annotation.PasswordValidate;
import br.ueg.personalsystem.enums.ErrorEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ReflectionUtil {

    public static Map<String, List<ErrorEnum>> validateEmail(Object object) {
        Map<String, List<ErrorEnum>> mapErrors = new HashMap<>();
        List<Field> fieldsEmail = ApiReflectionUtils.getFieldsWithAnnotation(object, EmailValidate.class);

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        for (Field field : fieldsEmail) {
            Object valueField = ApiReflectionUtils.getFieldValue(object, field);

            if (valueField == null) {
                mapErrors.put(getNameEmailAnnotation(field), List.of(ErrorEnum.EMAIL_INVALID));
                continue;
            }

            if (valueField instanceof String valueString) {
                List<ErrorEnum> errors = new ArrayList<>();

                if (Pattern.matches(emailRegex, valueString)) {
                    errors.add(ErrorEnum.EMAIL_INVALID);
                }

                if (!errors.isEmpty()) {
                    mapErrors.put(getNamePasswordAnnotation(field), errors);
                }
            }
        }

        return mapErrors;
    }

    public static Map<String, List<ErrorEnum>> validatePassword(Object object) {
        Map<String, List<ErrorEnum>> mapErrors = new HashMap<>();
        List<Field> fieldsPassword = ApiReflectionUtils.getFieldsWithAnnotation(object, PasswordValidate.class);

        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).+$";
        for (Field field : fieldsPassword) {
            Object valueField = ApiReflectionUtils.getFieldValue(object, field);

            if (valueField == null) {
                mapErrors.put(getNamePasswordAnnotation(field), List.of(ErrorEnum.PASSWORD_INVALID));
                continue;
            }

            if (valueField instanceof String valueString) {
                List<ErrorEnum> errors = new ArrayList<>();

                if (valueString.trim().length() < 8) {
                    errors.add(ErrorEnum.PASSWORD_MIN_LENGTH);
                }
                if (Pattern.matches(regex, valueString)) {
                    errors.add(ErrorEnum.PASSWORD_NUM_LETTER);
                }

                if (!errors.isEmpty()) {
                    mapErrors.put(getNamePasswordAnnotation(field), errors);
                }
            }
        }

        return mapErrors;
    }

    public static String getNamePasswordAnnotation(Field field) {
        String nameOfAnnotation = field.getAnnotation(PasswordValidate.class).name();
        if (nameOfAnnotation != null && !nameOfAnnotation.isBlank()) {
            return field.getAnnotation(PasswordValidate.class).name();
        } else {
            return field.getName();
        }
    }

    public static String getNameEmailAnnotation(Field field) {
        String nameOfAnnotation = field.getAnnotation(EmailValidate.class).name();
        if (nameOfAnnotation != null && !nameOfAnnotation.isBlank()) {
            return field.getAnnotation(EmailValidate.class).name();
        } else {
            return field.getName();
        }
    }
}
