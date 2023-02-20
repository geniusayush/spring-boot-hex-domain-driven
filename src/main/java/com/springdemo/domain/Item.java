package com.springdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    int density;
    Color densityColor;
}

