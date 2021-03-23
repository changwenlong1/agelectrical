package com.example.order.test;

public class PersonImpl implements Person{
    @Override
    public String sing(String name) {
        System.out.println("唱" + name);
        return "唱完了";
    }

    @Override
    public String dance(String name) {
        System.out.println("跳" + name);
        return "不好玩";
    }
}
