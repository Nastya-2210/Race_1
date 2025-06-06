package com.academy.race.model;

import java.util.ArrayList;
import java.util.List;

//Класс для агрегирования результатов забега
public class RaceResult {
    private final List<Runner> finishedRunners = new ArrayList<>();

    public synchronized void addResult(Runner runner) {
        finishedRunners.add(runner);
    }

    public List<Runner> getFinishedRunners() {
        return new ArrayList<>(finishedRunners);
    }
}
