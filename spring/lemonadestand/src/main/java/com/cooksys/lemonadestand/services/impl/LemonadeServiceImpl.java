package com.cooksys.lemonadestand.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.mappers.LemonadeMapper;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

    private LemonadeRepository lemonadeRepository;
    private LemonadeMapper lemonadeMapper;

    @Override
    public List<LemonadeResponseDto> getAllLemonades() {
        return lemonadeMapper.entitiesToResponseDtos(lemonadeRepository.findAll());
    }

    @Override
    public LemonadeResponseDto getLemonadeById(Long id) {
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.getReferenceById(id));
    }

    @Override
    public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {

        Lemonade lemonade = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
        lemonade.setPrice(lemonade.getLemonJuice() * .20 + lemonade.getWater() * .01 + lemonade.getSugar() * .15 + lemonade.getIceCubes() * .05 + .50);

        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonade));
    }

}
