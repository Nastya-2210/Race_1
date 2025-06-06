package com.academy.race.service;

import com.academy.race.model.RaceResult;
import com.academy.race.model.Runner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


//Сервис для подсчета и вывода результатов забега
@Service
public class ResultService {
    public void printResults(RaceResult raceResult) {
        if (raceResult == null) {
            throw new IllegalArgumentException("RaceResult cannot be null");
        }

        List<Runner> runners = new ArrayList<>(raceResult.getFinishedRunners());
        runners.sort(Comparator.comparingLong(Runner::getFinishTime));

        if (runners.isEmpty()) {
            System.out.println("Никто не финишировал!");
            return;
        }

        Runner winner = runners.getFirst();
        Runner loser = runners.getLast();

        System.out.println("\nРезультаты забега:");
        System.out.println("Победитель: Спортсмен № " + winner.getId() +
                " (скорость: " + String.format("%.2f", winner.getSpeed()) + ")");
        System.out.println("Аутсайдер: Спортсмен № " + loser.getId() +
                " (скорость: " + String.format("%.2f", loser.getSpeed()) + ")");

        System.out.println("\nОбщая таблица результатов:");
        long startTime = runners.getFirst().getFinishTime();
        for (int i = 0; i < runners.size(); i++) {
            Runner runner = runners.get(i);
            System.out.println((i + 1) + " место: Спортсмен № " + runner.getId() +
                    ", скорость: " + String.format("%.2f", runner.getSpeed()) +
                    ", время: " + (runner.getFinishTime() - startTime) + "ms");
        }
    }
}