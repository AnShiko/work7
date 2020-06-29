package com.geekbrains.work7;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.geekbrains.work7.MainApp.countDownLatchFinish;
import static com.geekbrains.work7.MainApp.countDownLatchReady;
import static com.geekbrains.work7.MainApp.startBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static final AtomicBoolean isWon = new AtomicBoolean(false);


    static {
        CARS_COUNT = 0;

    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
            try {
                System.out.println(this.name + " готовится");
                Thread.sleep(500 + (int) (Math.random() * 800));
                countDownLatchReady.countDown();
                System.out.println(this.name + " готов");
                startBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            final ArrayList <Stage> stages = race.getStages();
            for (Stage stage : stages) {
                stage.go(this);
            }
            countDownLatchFinish.countDown();
        if (!isWon.getAndSet(true))
            System.out.println(this.name + " - WIN");
        }
    }


