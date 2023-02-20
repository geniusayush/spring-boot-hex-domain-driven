package com.springdemo.application.service;

import com.springdemo.application.port.CircularRepository;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

public class CirculartixService {

    private final CircularRepository circularRepository;

    public CirculartixService(CircularRepository circularRepository) {

        this.circularRepository = circularRepository;
    }

    public Color calculate(List<String> materials) {
        List<Color> list = materials.stream().map(mat -> circularRepository.getItem(mat)
            .getDensityColor()).collect(Collectors.toList());
        final long l = list.stream().filter(c -> c == Color.GREEN).count() / list.size() * 100;
        return circularRepository.getSet()
            .stream()
            .filter(item -> item.getLowerPer() <= l && item.getUpperPer() >= l)
            .findFirst()
            .orElseThrow()
            .getColor();

    }
}



