package com.example.order.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonImplProxy {
    Person person = new PersonImpl();
    public Person createProxy() {
        return (Person) Proxy.newProxyInstance(PersonImplProxy.class.getClassLoader(),person.getClass().getInterfaces(),
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 编写代码
                        String methodName = method.getName();
                        if ("sing".equals(methodName)) {
                            System.out.println("啦啦啦啦啦啦");
                            return method.invoke(person, args);
                        } else if ("dance".equals(methodName)) {
                            System.out.println("哈哈哈哈哈哈2");
                            return method.invoke(person, args);
                        } else {
                            System.out.println("哈哈哈哈哈哈1");
                        }
                        return null;
                    }
                });
    }

}
