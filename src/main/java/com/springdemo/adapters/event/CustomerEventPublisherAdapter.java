package com.springdemo.adapters.event;


import com.springdemo.application.event.CustomerCreatedEvent;
import com.springdemo.application.port.CustomerEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class CustomerEventPublisherAdapter implements CustomerEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishCustomerCreatedEvent(CustomerCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}