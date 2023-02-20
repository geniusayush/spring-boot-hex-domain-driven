package com.springdemo.adapters.event;

import com.springdemo.application.event.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerEventListenerAdapter {

    @EventListener
    public void handle(CustomerCreatedEvent event){
        log.info("Customer created with id " + event.getId() + " at " + event.getDate());
    }

}