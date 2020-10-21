package com.demo.securitylogin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;

@SpringBootTest
class SecurityloginApplicationTests {
    Integer iCount = 100;
    @Test
    void contextLoads() {
        Object obj = new Object();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (iCount > 0) {
                    System.out.println(iCount);
                    iCount--;
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (iCount > 0) {
                    System.out.println(iCount);
                    iCount--;
                }
            }
        }.start();
        System.out.println("over");
    }

}
