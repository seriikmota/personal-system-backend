package br.ueg.personalsystem.reflection;

import br.ueg.personalsystem.base.reflection.ApiReflectionUtils;
import br.ueg.personalsystem.annotation.*;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.util.Util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtil extends ApiReflectionUtils {

    public static Map<String, List<ErrorEnum>> validateAnnotations(Object object) {
        List<Field> fields = getFields(object);

        Map<String, List<ErrorEnum>> mapFieldErrors = new HashMap<>();
        for (Field field : fields) {
            if (!field.getType().isPrimitive() && !field.getType().getName().startsWith("java.lang") && !field.getType().getName().startsWith("java.time") && !field.getType().isEnum()) {
                Object objectValue = getFieldValue(object, field);
                if (objectValue != null) {
                    mapFieldErrors.putAll(validateAnnotations(getFieldValue(object, field)));
                }
            }

            if (field.isAnnotationPresent(EmailValidate.class)) {
                List<ErrorEnum> errors = Util.validateEmail((String) getFieldValue(object, field));
                if (!errors.isEmpty()) {
                    if (mapFieldErrors.containsKey(field.getName())) {
                        mapFieldErrors.get(getNameEmailAnnotation(field)).addAll(errors);
                    } else {
                        mapFieldErrors.put(getNameEmailAnnotation(field), errors);
                    }
                }
            }
            if (field.isAnnotationPresent(NumberPhoneValidate.class)) {
                List<ErrorEnum> errors = Util.validateNumberPhone((String) getFieldValue(object, field));
                if (!errors.isEmpty()) {
                    if (mapFieldErrors.containsKey(field.getName())) {
                        mapFieldErrors.get(getNameNumberPhoneAnnotation(field)).addAll(errors);
                    } else {
                        mapFieldErrors.put(getNameNumberPhoneAnnotation(field), errors);
                    }
                }
            }
            if (field.isAnnotationPresent(PasswordValidate.class)) {
                List<ErrorEnum> errors = Util.validatePassword((String) getFieldValue(object, field));
                if (!errors.isEmpty()) {
                    if (mapFieldErrors.containsKey(field.getName())) {
                        mapFieldErrors.get(getNamePasswordAnnotation(field)).addAll(errors);
                    } else {
                        mapFieldErrors.put(getNamePasswordAnnotation(field), errors);
                    }
                }
            }
            if (field.isAnnotationPresent(CPFValidate.class)) {
                List<ErrorEnum> errors = Util.validateCpf((String) getFieldValue(object, field));
                if (!errors.isEmpty()) {
                    if (mapFieldErrors.containsKey(field.getName())) {
                        mapFieldErrors.get(getNameCpfValidateAnnotation(field)).addAll(errors);
                    } else {
                        mapFieldErrors.put(getNameCpfValidateAnnotation(field), errors);
                    }
                }
            }
            if (field.isAnnotationPresent(CEPValidate.class)) {
                List<ErrorEnum> errors = Util.validateCep((String) getFieldValue(object, field));
                if (!errors.isEmpty()) {
                    if (mapFieldErrors.containsKey(field.getName())) {
                        mapFieldErrors.get(getNameCepValidateAnnotation(field)).addAll(errors);
                    } else {
                        mapFieldErrors.put(getNameCepValidateAnnotation(field), errors);
                    }
                }
            }
        }
        return mapFieldErrors;
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

    public static String getNameCpfValidateAnnotation(Field field) {
        String nameOfAnnotation = field.getAnnotation(CPFValidate.class).name();
        if (nameOfAnnotation != null && !nameOfAnnotation.isBlank()) {
            return field.getAnnotation(CPFValidate.class).name();
        } else {
            return field.getName();
        }
    }

    public static String getNameNumberPhoneAnnotation(Field field) {
        String nameOfAnnotation = field.getAnnotation(NumberPhoneValidate.class).name();
        if (nameOfAnnotation != null && !nameOfAnnotation.isBlank()) {
            return field.getAnnotation(NumberPhoneValidate.class).name();
        } else {
            return field.getName();
        }
    }

    public static String getNameCepValidateAnnotation(Field field) {
        String nameOfAnnotation = field.getAnnotation(CEPValidate.class).name();
        if (nameOfAnnotation != null && !nameOfAnnotation.isBlank()) {
            return field.getAnnotation(CEPValidate.class).name();
        } else {
            return field.getName();
        }
    }
}
