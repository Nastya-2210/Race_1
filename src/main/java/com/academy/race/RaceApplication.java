package com.academy.race;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.academy.race.service.RaceService;

@SpringBootApplication
public class RaceApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RaceApplication.class, args);
		RaceService raceService = context.getBean(RaceService.class);

		int runnersCount = 5; // Количество спортсменов
		raceService.startRace(runnersCount);//Запуск забега

		context.close();
	}
}
