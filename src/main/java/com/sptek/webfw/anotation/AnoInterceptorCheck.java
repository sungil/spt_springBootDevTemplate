package com.sptek.webfw.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //메소드에 적용하기 위해
@Retention(RetentionPolicy.RUNTIME)
public @interface AnoInterceptorCheck {

}
