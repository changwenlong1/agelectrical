
package com.example.order.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 用于获取两个对象之间不同的值对应的列
 **/
public class CompareObjectUtils {

    protected static final Logger logger = LoggerFactory.getLogger(CompareObjectUtils.class);

    public static LinkedList<Map<String, Object>> compareAndGet(Class clazz, Object o1, Object o2) throws IllegalAccessException, InvocationTargetException {
        Class<?> o1Class = o1.getClass();
        if (!o1Class.isInstance(o2)) {
            throw new RuntimeException("请传入一致的对象类型");
        }
        LinkedList<Map<String, Object>> nameList = new LinkedList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                String name = method.getName();
                name = name.replace("get", "");
                name = name.replaceFirst(String.valueOf(name.charAt(0)), String.valueOf(name.charAt(0)).toLowerCase());
                Object invoke1 = method.invoke(o1, null);
                Object invoke2 = method.invoke(o2, null);
                if (null != invoke1 && null != invoke2) {
                    boolean equals = false;
                    if (invoke1 instanceof BigDecimal) {
                        equals = (((BigDecimal) invoke1).doubleValue()) != (((BigDecimal) invoke2).doubleValue());
                    } else {
                        equals = !invoke1.equals(invoke2);
                    }
                    if (equals) {
                        Map<String, Object> map = new LinkedHashMap<>();
                        map.put("fieldName", name);
                        map.put("o1Value", invoke1);
                        map.put("o2Value", invoke2);
                        nameList.add(map);
                    }
                }
            }
        }
        return nameList;
    }

    public static LinkedList<Map<String, Object>> compareFiled(Class clazz, Object o1) throws IllegalAccessException, InvocationTargetException {
        Class<?> o1Class = o1.getClass();
        LinkedList<Map<String, Object>> nameList = new LinkedList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                String name = method.getName();
                name = name.replace("get", "");
                name = name.replaceFirst(String.valueOf(name.charAt(0)), String.valueOf(name.charAt(0)).toLowerCase());
                Object invoke1 = method.invoke(o1, null);
                if (null != invoke1) {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("fieldName", name);
                    map.put("o1Value", invoke1);
                    nameList.add(map);
                }
            }
        }
        return nameList;
    }
}
