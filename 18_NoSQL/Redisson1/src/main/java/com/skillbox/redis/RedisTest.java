package com.skillbox.redis;
import java.util.Random;

public class RedisTest {
    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        for (int i = 1; i < 21; i++) {
            redis.addToStorage(("User " + i), i); //add user to redis
        }
        int counterI = 1;
        while (true) {

            if (new Random().nextInt(10) == 0) {
                int numPayUser = 1 + (int) (Math.random() * 20); //число для вставки
                String payingUser = "User " + numPayUser;
                System.out.println(payingUser + " оплатил платную услугу");
                int key = redis.getKeyOfUser(redis.getUser(0));
                redis.addToStorage(payingUser, key);
            }
            System.out.println("На главной странице показываем " + redis.getUser(0)); //print name of user
            redis.userIncrement(20, redis.getUser(0));

            Thread.sleep(100);

            if (counterI % 20 == 0) {
                Thread.sleep(1000);
                System.out.println();
            } //визуальная отсечка списка
            counterI++;
        }

    }
}
