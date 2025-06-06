package com.academy.race.service;

import com.academy.race.model.RaceResult;
import com.academy.race.model.Runner;
import com.academy.race.RunnerIdGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//Сервис для имитации забега
@Service
public class RaceService {
    private final RunnerIdGenerator idGenerator;
    private final ResultService resultService;

    public RaceService(RunnerIdGenerator idGenerator, ResultService resultService) {
        this.idGenerator = idGenerator;
        this.resultService = resultService;
    }

    //Имитируем забег
    public void startRace(int runnersCount) {
        if (runnersCount <= 0) {
            throw new IllegalArgumentException("Number of runners must be positive");
        }

        RaceResult raceResult = new RaceResult();
        List<Runner> runners = createRunners(runnersCount, raceResult);
        ExecutorService executor = Executors.newFixedThreadPool(runnersCount);

        try {
            System.out.println("Забег начинается! Участников: " + runnersCount);
            runners.forEach(executor::submit);
            executor.shutdown();

            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                resultService.printResults(raceResult);
            } else {
                System.out.println("Забег не завершился в отведенное время");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
        }
    }

    private List<Runner> createRunners(int count, RaceResult raceResult) {
        List<Runner> runners = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int id = idGenerator.getNextId();
            double speed = 0.5 + ThreadLocalRandom.current().nextDouble(1.0);
            runners.add(new Runner(id, speed, raceResult));
        }
        return runners;
    }
}
