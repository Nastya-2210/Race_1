package com.academy.race.model;

public class Runner implements Runnable {
    private final int id;
    private final double speed;
    private final RaceResult raceResult;
    private volatile long finishTime;

    public Runner(int id, double speed, RaceResult raceResult) {
        if (raceResult == null) {
            throw new IllegalArgumentException("RaceResult cannot be null");
        }
        this.id = id;
        this.speed = speed;
        this.raceResult = raceResult;
    }

    @Override
    public void run() {
        try {
            for (int barrier = 1; barrier <= 10; barrier++) {
                int delay = (int) (1000 / speed);
                Thread.sleep(delay);

                System.out.println("Спортсмен № " + id + " пробежал барьер № " + barrier);
            }

            finishTime = System.currentTimeMillis();
            raceResult.addResult(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    public long getFinishTime() {
        return finishTime;
    }
}