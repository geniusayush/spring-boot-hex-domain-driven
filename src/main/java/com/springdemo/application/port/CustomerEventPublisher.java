package com.springdemo.application.port;

import com.springdemo.application.event.CustomerCreatedEvent;

public interface CustomerEventPublisher {

    void publishCustomerCreatedEvent(CustomerCreatedEvent event);

}