package com.mas.kinga;

import com.mas.kinga.models.Category;
import com.mas.kinga.models.GAME_TYPE;
import com.mas.kinga.models.Game;
import com.mas.kinga.repositories.CategoryRepository;
import com.mas.kinga.repositories.GameRepository;
import com.mas.kinga.services.GameService;
import com.mas.kinga.services.implementations.GameServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MasFinalProjectGameHavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasFinalProjectGameHavenApplication.class, args);
    }



}

