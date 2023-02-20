package com.springdemo.application.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreatedEvent {

    private Long id;

    private LocalDateTime date;

    public CustomerCreatedEvent(Long id) {
        this.id = id;
        this.date = LocalDateTime.now();
    }

}