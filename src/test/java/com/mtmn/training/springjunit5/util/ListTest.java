package com.mtmn.training.springjunit5.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ListTest {

    private List<String> list = new ArrayList<>();

    @Test
    void testOne(){
        Assertions.assertTrue(list.isEmpty(), "List should be empty");
        list.add("one");
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void testTwo(){
        Assertions.assertTrue(list.isEmpty(), "List should be empty");
        list.add("two");
        Assertions.assertEquals(1, list.size());
    }

}


