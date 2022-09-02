package com.odd.annotation;


import com.odd.enable.ControllerMakerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(ControllerMakerConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableResponseResult {


}
