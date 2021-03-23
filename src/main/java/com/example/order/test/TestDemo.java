package com.example.order.test;

import java.util.Random;
import java.util.Scanner;

public class TestDemo {
    /**
     * 猜数字
     * @param args
     *//*
    public static void main(String[] args) {
        Random random = new Random(); // 默认随机种子是系统时间
        Scanner sc = new Scanner(System.in);
        int toGuess = random.nextInt(100);
        // System.out.println("toGuess: " + toGuess);
        while (true) {
            System.out.println("请输入要输入的数字: (1-100)");
            int num = sc.nextInt();
            if (num < toGuess) {
                System.out.println("小了");
            } else if (num > toGuess) {
                System.out.println("大了");
            } else {
                System.out.println("恭喜回答正确");
                break;
            }
        }
        sc.close();
    }*/
    /**
     * 菱形打印
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入打印行数:");
        int scnum = sc.nextInt();
        for (int i = 1; i <= scnum; i++) {
            for (int j = 1; j <= scnum; j++) {
                if(j==i || j+i==scnum+1){
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
       /* Scanner scanner = new Scanner(System.in);
        System.out.println("请输入打印行数：");
        int num= scanner.nextInt();
        //第一层循环控制行数
        for(int i = 1;i <= num;i++){
            //第二层循环控制行
            for(int j = 1;j <= num;j++){
                //当 i == j 时会打印 'x' ;当i + j == num + 1 时会打印'x'
                if(j == i || j + i == num + 1) {
                    System.out.print("x");
                }
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }*/
    }
}
