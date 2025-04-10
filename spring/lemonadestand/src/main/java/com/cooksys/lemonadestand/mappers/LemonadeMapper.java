package com.cooksys.lemonadestand.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.model.LemonadeDto;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;

@Mapper(componentModel = "spring")
public interface LemonadeMapper {

    Lemonade requestDtoToEntity(LemonadeRequestDto lemonadeRequestDto);

    Lemonade lemonadeDtoToEntity(LemonadeDto lemonadeDto);

    LemonadeResponseDto entityToResponseDto(Lemonade lemonade);

    Lemonade lemonadeResponseDtoToEntity(LemonadeResponseDto lemonadeResponseDto);

    List<LemonadeResponseDto> entitiesToResponseDtos(List<Lemonade> lemonades);

}
