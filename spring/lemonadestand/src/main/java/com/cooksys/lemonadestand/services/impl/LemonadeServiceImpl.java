package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.LemonadeMapper;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

    private final LemonadeRepository lemonadeRepository;
    private final LemonadeMapper lemonadeMapper;

    private void setLemonadePrice(Lemonade lemonade) {
        lemonade.setPrice(lemonade.getLemonJuice() * .20 + lemonade.getWater() * .01 + lemonade.getSugar() * .15 + lemonade.getIceCubes() * .05 + .50);
    }

    private void validateLemonadeRequest(LemonadeRequestDto lemonadeRequestDto) {
        if (lemonadeRequestDto.getLemonJuice() == null || lemonadeRequestDto.getWater() == null || lemonadeRequestDto.getSugar() == null || lemonadeRequestDto.getIceCubes() == null) {
            throw new BadRequestException("All fields are required for creating a lemonade.");
        }
    }

    @Override
    public Lemonade getLemonadeFromRequest(Long id) {
        Optional<Lemonade> optionalLemonade = lemonadeRepository.findByIdAndDeletedFalse(id);
        if (optionalLemonade.isEmpty()) {
            throw new NotFoundException("No lemonade found with id: " + id);
        }
        return optionalLemonade.get();
    }

    @Override
    public List<LemonadeResponseDto> getAllLemonades() {
        return lemonadeMapper.entitiesToResponseDtos(lemonadeRepository.findAllByDeletedFalse());
    }

    @Override
    public LemonadeResponseDto getLemonadeById(Long id) {
        return lemonadeMapper.entityToResponseDto(getLemonadeFromRequest(id));
    }

    @Override
    public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {

        validateLemonadeRequest(lemonadeRequestDto);

        Lemonade lemonade = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
        setLemonadePrice(lemonade);
        lemonade.setDeleted(false);

        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonade));
    }

    @Override
    public LemonadeResponseDto updateLemonadeById(Long id, LemonadeRequestDto lemonadeRequestDto) {
        validateLemonadeRequest(lemonadeRequestDto);
        Lemonade lemonade = getLemonadeFromRequest(id);
        lemonade.setLemonJuice(lemonadeRequestDto.getLemonJuice());
        lemonade.setWater(lemonadeRequestDto.getWater());
        lemonade.setSugar(lemonadeRequestDto.getSugar());
        lemonade.setIceCubes(lemonadeRequestDto.getIceCubes());
        setLemonadePrice(lemonade);
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonade));
    }

    @Override
    public LemonadeResponseDto deleteLemonadeById(Long id) {
        Lemonade lemonade = getLemonadeFromRequest(id);
        lemonade.setDeleted(true);
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonade));
    }
}
