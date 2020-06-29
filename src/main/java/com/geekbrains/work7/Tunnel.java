package com.geekbrains.work7;

import java.util.concurrent.Semaphore;

import static com.geekbrains.work7.MainApp.CARS_COUNT;

public class Tunnel extends Stage {
    private Semaphore tunnelLimit = new Semaphore(CARS_COUNT / 2);
    public Tunnel(int length) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelLimit.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
