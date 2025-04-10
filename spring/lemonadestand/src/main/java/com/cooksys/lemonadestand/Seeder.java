package com.cooksys.lemonadestand;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.repositories.LemonadeStandRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final LemonadeStandRepository lemonadeStandRepository;

    @Override
    public void run(String... args) throws Exception {

        LemonadeStand lemonadeStand1 = new LemonadeStand();
        LemonadeStand lemonadeStand2 = new LemonadeStand();
        LemonadeStand lemonadeStand3 = new LemonadeStand();
        LemonadeStand lemonadeStand4 = new LemonadeStand();
        LemonadeStand lemonadeStand5 = new LemonadeStand();

        lemonadeStand1.setName("Cooksys Lemonade Stand");
        lemonadeStand2.setName("Brad's Lemonade Stand");
        lemonadeStand3.setName("Frank's Lemonade Stand");
        lemonadeStand4.setName("Will's Lemonade Stand");
        lemonadeStand5.setName("Instructor's Lemonade Stand");

        lemonadeStandRepository.saveAllAndFlush(Arrays.asList(new LemonadeStand[]{lemonadeStand1, lemonadeStand2, lemonadeStand3, lemonadeStand4, lemonadeStand5}));

    }

}
