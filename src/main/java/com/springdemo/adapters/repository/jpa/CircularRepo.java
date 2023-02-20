package com.springdemo.adapters.repository.jpa;

import com.springdemo.application.port.CircularRepository;
import com.springdemo.domain.Item;
import com.springdemo.domain.Itemresult;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class CircularRepo implements CircularRepository {

    private final HashMap<String, Item> map = new HashMap<>();
    private final HashSet<Itemresult> set = new HashSet<>();

    public CircularRepo() {
        map.put("Lisca966", new Item(100, Color.GREEN));
        map.put("TK- ZLB-M", new Item(20, Color.GREEN));
        set.add(new Itemresult(60, 100, Color.GREEN));
        set.add(new Itemresult(0, 59, Color.GREEN));
    }

    @Override
    public HashSet<Itemresult> getSet() {
        return set;
    }

    @Override
    public Item getItem(String material) {
        return map.get(material);
    }
}
