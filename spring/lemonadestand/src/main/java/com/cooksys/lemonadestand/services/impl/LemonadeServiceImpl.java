package com.cooksys.lemonadestand.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

    private LemonadeRepository lemonadeRepository;

    @Override
    public List<LemonadeResponseDto> getAllLemonades() {
        List<LemonadeResponseDto> result = new ArrayList<>();
        for (Lemonade lemonade: lemonadeRepository.findAll()) {
            result.add(new LemonadeResponseDto(lemonade.getId(), lemonade.getPrice()));
        }
        return result;
    }

    @Override
    public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {
        Lemonade lemonadeToSave = new Lemonade();
        lemonadeToSave.setLemonJuice(lemonadeRequestDto.getLemonJuice());
        lemonadeToSave.setWater(lemonadeRequestDto.getWater());
        lemonadeToSave.setSugar(lemonadeRequestDto.getSugar());
        lemonadeToSave.setIceCubes(lemonadeRequestDto.getIceCubes());

        lemonadeToSave.setPrice(lemonadeToSave.getLemonJuice() * .20 + lemonadeToSave.getWater() * .01 + lemonadeToSave.getSugar() * .15 + lemonadeToSave.getIceCubes() * .05 + .50);
        Lemonade newlyCreatedLemonade = lemonadeRepository.saveAndFlush(lemonadeToSave);
        return new LemonadeResponseDto(newlyCreatedLemonade.getId(), newlyCreatedLemonade.getPrice());
    }

}
