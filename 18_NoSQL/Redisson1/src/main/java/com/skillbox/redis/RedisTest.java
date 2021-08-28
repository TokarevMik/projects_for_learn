package com.skillbox.redis;

import java.util.LinkedList;

public class RedisTest {
    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        for (int i = 1; i < 21; i++) {
            redis.addToStorage(("User " + i), i); //add user to redis
        }
        while (true) {
            for (int i = 0; i < 20; i++) {
                int a = (int) (Math.random() * 50);//определение вставки
                if (a % 38 == 0) {
                    int numPayUser = 1 + (int) (Math.random() * 20); //число для вставки
                    String payingUser = "User " + numPayUser;
                    System.out.println(payingUser + " оплатил платную услугу");
                    double incr = redis.getKeyOfUser(payingUser) - redis.getKeyOfUser(redis.getUser(i));
                    if (incr != 0) {
                        System.out.println("На главной странице показываем " + payingUser);
                    }
                    redis.userIncrement(incr, payingUser);
                }
                System.out.println("На главной странице показываем " + redis.getUser(i)); //print name of user
                Thread.sleep(100);

            }
            Thread.sleep(1000);
            System.out.println();
        }

    }
}
