package com.academy.race;

import org.springframework.stereotype.Component;

@Component
public class RunnerIdGenerator {
    private int nextId = 1;

    public synchronized int getNextId() {
        return nextId++;
    }
}
