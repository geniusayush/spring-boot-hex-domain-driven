package com.springdemo.application.port;



import com.springdemo.domain.Item;
import com.springdemo.domain.Itemresult;

import java.util.HashSet;

public interface CircularRepository {
    HashSet<Itemresult> getSet();

    Item getItem(String material);
}
