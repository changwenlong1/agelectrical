package com.example.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.order.entity.GSysOrder;
import com.example.order.entity.GSysOrderProgress;
import com.example.order.entity.GSysUserMenu;
import com.example.order.test.MyRunnable;
import com.example.order.test.Person;
import com.example.order.test.PersonImplProxy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    /**
     * 拼接list
     * @param <T>
     */
    @org.junit.Test
    public <T>void test01() {
        List<T> list = new ArrayList<>();
        GSysUserMenu gSysUserMenu = new GSysUserMenu((long)111111,(long)111111);
        list.add((T) gSysUserMenu);
        list.add((T) "1");
        list.add((T) "2");
        System.out.println("拼接的list:"+list);
    }

    /**
     *  StringUtils.split()和string.split()的区别
     *  StringUtils.split()是把分隔符拆成一个个单独的字符，再用这些字符去把字符串进行分割的。只要匹配到了分隔符中的任意一个字符，就会进行分割。
     * string.split()是把分隔符作为一个整体来对字符串进行分割。
     */
    @org.junit.Test
    public void test02() {
        String str = "a!b#c!d";
        String s = "#!";
        //Apache工具类的split方法
        final String[] result1 = StringUtils.split(str, s);
        for (final String string : result1) {
            System.out.println(string);
        }
        //jdk的split方法
        final String[] result2 = str.split(s);
        for (final String string : result2) {
            System.out.println(string);
        }
        System.out.println("############################");
        String str2 =",1, ,2,3,,";
        String[] split1 = StringUtils.split(str2, ",");//split1 长度 4
        //Apache工具类的split方法
        for (final String string : split1) {
            System.out.println(string);
        }
        //jdk的split方法
        String[] split2 = str2.split(",");//split2 长度 5
        for (final String string : split2) {
            System.out.println(string);
        }
    }

    /**
     * fastjon的常用方法
     *       JSON.toJSONString():对象转为json格式字符串；
     *
     *       JSON.parseObject():json格式转字符串对象；
     *
     *       JSON.parseArray():转为列表对象；
     *
     *       JSON.toJSONStringWithDateFormat():返回指定日期格式的json字符串
     * @param <T>
     */
    @org.junit.Test
    public <T>void test03() {
        // java对象转为json格式的字符串
        GSysUserMenu gSysUserMenu = new GSysUserMenu((long)111111,(long)111111);
        String s = JSON.toJSONString(gSysUserMenu);
        System.out.println("java对象转为json格式的字符串:"+s);

        // json格式的字符串转为java对象
        String userStr = "{\"userId\":222222,\"menuId\":222222}";
        GSysUserMenu gSysUserMenu1 = JSON.parseObject(userStr, GSysUserMenu.class);
        System.out.println("json格式的字符串转为java对象:"+gSysUserMenu1);
        //System.out.println(gSysUserMenu1.getUserId());

        //列表对象转json字符串
        List<T> list = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            GSysUserMenu u = new GSysUserMenu( (long)i, (long)i);
            list.add((T) u);
        }
        list.add((T)"嘻嘻");
        list.add((T)"哈哈");
        System.out.println("列表对象转json字符串:"+JSON.toJSONString(list));

        // json格式字符串转为list对象
        String listStr = "[{\"userId\":12,\"menuId\":111111},{\"userId\":13,\"menuId\":222222},{\"userId\":14,\"menuId\":333333},{\"userId\":15,\"menuId\":444444}, {\"userId\":16,\"menuId\":555555},{\"userId\":17,\"menuId\":666666}]";
        List<GSysUserMenu> gSysUserMenuList = JSON.parseArray(listStr, GSysUserMenu.class);
        System.out.println("json字符串转列表:"+gSysUserMenuList);
        System.out.println("json字符串转列表元素:"+gSysUserMenuList.get(0).getUserId());


        //返回执行日期格式的json字符串
        GSysOrderProgress gSysOrderProgress = new GSysOrderProgress((long)111111,(long)111111, new Date(),"1111");
        // 针对日期对象，默认转为时间戳
        System.out.println("针对日期对象，默认转为时间戳:"+JSON.toJSONString(gSysOrderProgress));
        // 默认将日期对象转为 yyyy-MM-dd HH:mm:ss格式的字符串
        System.out.println("默认将日期对象转为 yyyy-MM-dd HH:mm:ss格式的字符串:"+JSON.toJSONString(gSysOrderProgress, SerializerFeature.WriteDateUseDateFormat));
        // 日期对象转换为指定格式
        System.out.println("日期对象转换为指定格式:"+JSON.toJSONStringWithDateFormat(gSysOrderProgress, "yyyy-MM-dd",SerializerFeature.WriteDateUseDateFormat));
    }

    /**
     * jsckson的常用方法
     * mapper.writeValueAsString() 对象转为json格式字符串
     * mapper.readValue() json格式字符串转为对象
     */
    @org.junit.Test
    public void test3(){
        // 使用jackson时核心对象
        ObjectMapper mapper = new ObjectMapper();

        // java对象转为json格式字符串
        GSysUserMenu gSysUserMenu = new GSysUserMenu((long)111111,(long)111111);
        try {
            String s = mapper.writeValueAsString(gSysUserMenu);
            System.out.println("java对象转为json格式字符串:"+s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //list集合转json格式字符串
        List<GSysUserMenu> list = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            GSysUserMenu u = new GSysUserMenu((long)222222,(long)222222);
            list.add(u);
        }
        try {
            String s = mapper.writeValueAsString(list);
            System.out.println("list集合转json格式字符串:"+s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // json格式字符串转为对象
        String jsonStr = "{\"userId\":222222,\"menuId\":222222}";
        try {
            GSysUserMenu user1 = mapper.readValue(jsonStr, GSysUserMenu.class);
            System.out.println("json格式字符串转为对象:"+user1);
            System.out.println(user1.getUserId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // json格式字符串转为list对象
        String jsonStr2 =  "[{\"userId\":12,\"menuId\":111111},{\"userId\":13,\"menuId\":222222},{\"userId\":14,\"menuId\":333333},{\"userId\":15,\"menuId\":444444}, {\"userId\":16,\"menuId\":555555},{\"userId\":17,\"menuId\":666666}]";
        try {
            // 转对象
            List<GSysUserMenu> list1 = mapper.readValue(jsonStr2, new TypeReference<List<GSysUserMenu>>() {});
            System.out.println("json格式字符串转为list对象:"+list1);
            System.out.println(list1.get(0).getUserId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置日期的转换格式
        mapper.setDateFormat(new SimpleDateFormat("yyy-MM-dd"));
        GSysOrderProgress gSysOrderProgress = new GSysOrderProgress((long)111111,(long)111111, new Date(),"1111");
        try {
            // 默认日期转为时间戳
            String s = mapper.writeValueAsString(gSysOrderProgress);
            System.out.println("设置日期的转换格式:"+s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 冒泡排序
     */
    @org.junit.Test
    public void test04(){
        /*int [] a = {2,3,44,1,23,22,333};
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length-1-i; j++) {
                if(a[j]>a[j+1]){
                    int temp = a[i];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        System.out.println("从小到大排序后的结果是:");
        for (int t = 0; t < a.length; t++) {
            System.out.print(a[t]+"-----");
        }*/
       
            //冒泡排序算法
            int[] numbers=new int[]{1,65,8,2,3,9,4};
            //需进行length-1次冒泡
            for(int i=0;i<numbers.length-1;i++)
            {
                for(int j=0;j<numbers.length-1-i;j++)
                {
                    if(numbers[j]>numbers[j+1])
                    {
                        int temp=numbers[j];
                        numbers[j]=numbers[j+1];
                        numbers[j+1]=temp;
                    }
                }
            }
            System.out.println("从小到大排序后的结果是:");
        int i;
        for(i=0; i<numbers.length; i++)
                System.out.print(numbers[i]+" ");
        
    }

    /**
     * 插入排序
     */
    @org.junit.Test
    public void test05(){
        int s;
        int temp;
        //插入排序
        int[] numbers=new int[]{1,65,8,2,3,9,4};
        for (int i = 1; i < numbers.length; i++) {
            temp = numbers[i];
            s = i;
            while (s > 0 && temp < numbers[s - 1]) {
                numbers[s] = numbers[s - 1];
                s = s - 1;
            }
            numbers[s] = temp;
        }
        System.out.println("从小到大排序后的结果是:");
        int i;
        for(i=0; i<numbers.length; i++)
            System.out.print(numbers[i]+" ");

    }

    /**
     *动态代理
     */
    @org.junit.Test
    public void test06(){
        PersonImplProxy proxy = new PersonImplProxy();
        Person person = proxy.createProxy();
        System.out.println(person.sing("说散就散"));
        System.out.println(person.dance("体面"));

    }

    /**
     *猜数字
     */
    @org.junit.Test
    public void test07(){
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int num = random.nextInt(100);
        System.out.println(num);
        while (true){
            System.out.println("请输入猜测数字");
            int scnum = sc.nextInt();
            if(num>scnum){
                System.out.println("猜小了");
            }else if(num<scnum){
                System.out.println("猜大了");
            }else{
                System.out.println("恭喜你猜对了");
                break;
            }
        }
    }

    /**
     * newSingleThreadExecutor：单线程池。
     */
    @org.junit.Test
    public void test08(){
        ExecutorService pool = Executors.newSingleThreadExecutor();
       /* pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());*/
        for (int i = 0; i <=5 ; i++) {
            pool.submit(new MyRunnable());
        }
        pool.shutdown();
    }

    /**
     * newFixedThreadPool：固定线程数量的线程池。
     */
    @org.junit.Test
    public void test09(){
        ExecutorService pool = Executors.newFixedThreadPool(2);
       /* pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());*/
        for (int i = 0; i <=5 ; i++) {
            pool.submit(new MyRunnable());
        }
        pool.shutdown();
    }

    /**
     * newCachedThreadPool：缓冲功能的线程。（不用固定线程池长度）
     */
    @org.junit.Test
    public void test10(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.submit(new MyRunnable());
        }
        pool.shutdown();
    }

}
