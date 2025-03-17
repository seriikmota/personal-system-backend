package br.ueg.personalsystem.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MandatoryField {
    String name();
    int length() default 255;
}
