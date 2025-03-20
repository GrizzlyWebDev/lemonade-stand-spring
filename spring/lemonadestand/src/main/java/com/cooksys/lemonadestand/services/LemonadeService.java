package com.cooksys.lemonadestand.services;

import java.util.List;

import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;

public interface LemonadeService {

    List<LemonadeResponseDto> getAllLemonades();

    LemonadeResponseDto getLemonadeById(Long id);

    LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto);

    LemonadeResponseDto updateLemonadeById(Long id, LemonadeRequestDto lemonadeRequestDto);

    LemonadeResponseDto deleteLemonadeById(Long id);

}
