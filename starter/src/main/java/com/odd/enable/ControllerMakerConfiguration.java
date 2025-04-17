package com.odd.enable;

import com.odd.model.ControllerMarker;
import org.springframework.context.annotation.Bean;

public class ControllerMakerConfiguration {


    @Bean
    ControllerMarker controllerMarker(){
        return new ControllerMarker();
    }

}
