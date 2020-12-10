package com.example.order.utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapUtil {
    @Test
    public void IterateHashMapExample1 () {
        // 1. 使用 Iterator 遍历 HashMap EntrySet
        Map < Integer, String > coursesMap = new HashMap < Integer, String > ();
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");
        for (Integer key : coursesMap.keySet()) {
            System.out.println("key= "+ key + " and value= " + coursesMap.get(key));
        }
//        Iterator < Entry < Integer, String >> iterator = coursesMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Entry < Integer, String > entry = iterator.next();
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
    }

    @Test
    public  void IterateHashMapExample2 () {
        //2. 使用 Iterator 遍历 HashMap KeySet
        Map < Integer, String > coursesMap = new HashMap < Integer, String > ();
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");

        // 2. 使用 Iterator 遍历 HashMap KeySet
        Iterator<Entry<Integer, String>> iterator = coursesMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry  = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public  void IterateHashMapExample3 () {
        //3. 使用 For-each 循环遍历 HashMap
        Map < Integer, String > coursesMap = new HashMap < Integer, String > ();
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");

        // 3. 使用 For-each 循环遍历 HashMap
        for (Map.Entry < Integer, String > entry: coursesMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public  void IterateHashMapExample4 () {
        //4. 使用 Lambda 表达式遍历 HashMap
        Map < Integer, String > coursesMap = new HashMap < Integer, String > ();
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");

        // 4. 使用 Lambda 表达式遍历 HashMap
        coursesMap.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

    @Test
    public  void IterateHashMapExample5 () {
        //5. 通过Map.values()遍历所有的value，但不能遍历key
        Map < Integer, String > coursesMap = new HashMap < Integer, String > ();
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");

        // 4. 使用 Lambda 表达式遍历 HashMap
        for (String a: coursesMap.values()
             ) {
            System.out.println("value:"+a);
        }
    }
}
