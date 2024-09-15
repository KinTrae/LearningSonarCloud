package com.mas.kinga;

import com.mas.kinga.models.*;
import com.mas.kinga.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final GameElementRepository gameElementRepository;
    private final StationaryEmployeeRepository stationaryEmployeeRepository;
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }


    private void initData() {

        try {

            Category category = Category.builder().name("Action").description("Action Games").build();
            categoryRepository.save(category);

            //CATEGORIES
            Category c1 = Category.builder()
                    .name("Fantasy")
                    .build();

            Category c2 = Category.builder()
                    .name("Horror")
                    .build();

            Category c3 = Category.builder()
                    .name("Single Player")
                    .build();

            categoryRepository.save(c1);
            categoryRepository.save(c2);
            categoryRepository.save(c3);

            List<Category> categories = categoryRepository.findAll();

            ///TEST GRY - można przetestować releaseDate<=present, UNIQUE(name), GAME_TYPE(BOARD_GAME={game_elements[0..*]}, VIDEO={licenseKeys[1..*], platforms[1..*]})
            Game g1 = Game.builder()
                    .gameType(Collections.singleton(GAME_TYPE.BOARD_GAME))
                    .price(1)
                    .categories(new ArrayList<>(Arrays.asList(c1, c2)))
                    .name("Diuna")
                    .image("https://xxboxnews.blob.core.windows.net/prod/sites/2/2024/03/DUNE_IMPERIUM_HERO-ff78510157fd28db7476.jpg")
                    .releaseDate(LocalDate.now())
                    .quantity(100)
                    .categories(categories)
                    .build();
            gameRepository.save(g1);
            System.out.println("Zapisano g1");
            gameRepository.findAll().forEach(e -> e.getName());
            System.out.println("----------- \n ----------");

            Game g2 = Game.builder()
                    .gameType(Collections.singleton(GAME_TYPE.BOARD_GAME))
                    .price(1)
                    .categories(new ArrayList<>(Arrays.asList(c1)))
                    .name("Scythe")
                    .image("https://image.ceneostatic.pl/data/products/48837856/6014d65f-6c5d-47a4-969f-7383784b5e0e_i-phalanx-games-scythe.jpg")
                    .releaseDate(LocalDate.now())
                    .quantity(100)
                    .categories(categories.subList(0,1))
                    .build();
            gameRepository.save(g2);
            System.out.println("Zapisano g2");

            Game video1 = Game.builder()
                    .gameType(Collections.singleton(GAME_TYPE.VIDEO))
                    .licenseKeys(Map.of("2", 0L))
                    .platforms(Set.of(PLATFORM.PC))
                    .price(1)
                    .categories(new ArrayList<>(Arrays.asList(c1, c3)))
                    .name("Hercules - XP")
                    .image("https://archive.org/download/herculesag/disney%20hercules%20pc.jpg")
                    .releaseDate(LocalDate.now())
                    .quantity(100)
                    .categories(categories.subList(0,1))
                    .build();

            gameRepository.save(video1);
            System.out.println("Zapisano v1");

            //TEST GAME_ELEMENTS - można przetestować próbę przypisania elementu do złego typu gry - działa tylko dla BOARD_GAME,

            GameElement ge1 = GameElement.builder()
                    .description("opis")
                    .elementName("Kostka")
                    .quantity(1)
                    .game(g1)
                    .build();

            GameElement ge2 = GameElement.builder()
                    .description("opis")
                    .elementName("Karty")
                    .quantity(100)
                    .game(g1)
                    .build();

            {
                // Save GameElements ge1 and ge2
                gameElementRepository.save(ge1);
                gameElementRepository.save(ge2);

                // Save updated g1
                g1.setGameElements(new HashSet<>(Set.copyOf(Arrays.asList(ge1, ge2))));
                gameRepository.save(g1);
//                System.out.println("g1: " + gameElementRepository.findAllByGame(g1).toString());


//                gameRepository.delete(g1);
//                System.out.println("All elements " + gameElementRepository.findAllByGame(g1).toString());
            }

            //PERSON - age validator
            StationaryEmployee p1 = StationaryEmployee.builder()
                    .name("Ala")
                    .surname("Ma kota")
                    .salary(100)
                    .birthdate(LocalDate.of(2002, 8, 31))
                    .build();

            User user1 = User.builder()
                    .name("Kinga")
                    .surname("Traczyk")
                    .login("KinTrae")
                    .email("s24839@pjwst.edu.pl")
                    .birthdate(LocalDate.of(2002, 8, 31))
                    .role(ROLE.CUSTOMER)
                    .password("admin").build();

            stationaryEmployeeRepository.save(p1);
            userRepository.save(user1);
            System.out.println(stationaryEmployeeRepository.findAll());
            System.out.println(userRepository.findAll().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
