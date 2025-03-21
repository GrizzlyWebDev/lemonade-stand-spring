package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.LemonadeStandStandMapper;
import com.cooksys.lemonadestand.model.LemonadeStandRequestDto;
import com.cooksys.lemonadestand.model.LemonadeStandResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeStandRepository;
import com.cooksys.lemonadestand.services.LemonadeStandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LemonadeStandServiceImpl implements LemonadeStandService {

    private final LemonadeStandRepository lemonadeStandRepository;
    private final LemonadeStandStandMapper lemonadeStandMapper;

    private void validateLemonadeStandRequest(LemonadeStandRequestDto lemonadeStandRequestDto) {
        if (lemonadeStandRequestDto.getName() == null) {
            throw new BadRequestException("All fields are required for creating a lemonade stand.");
        }
    }

    @Override
    public LemonadeStand getLemonadeStandFromRequest(Long id) {
        Optional<LemonadeStand> optionalLemonadeStand = lemonadeStandRepository.findByIdAndDeletedFalse(id);
        if (optionalLemonadeStand.isEmpty()) {
            throw new NotFoundException("No lemonade stand found with id: " + id);
        }
        return optionalLemonadeStand.get();
    }

    @Override
    public LemonadeStandResponseDto createLemonadeStand(LemonadeStandRequestDto lemonadeStandRequestDto) {
        validateLemonadeStandRequest(lemonadeStandRequestDto);
        return lemonadeStandMapper.entityToResponseDto(lemonadeStandRepository.saveAndFlush(lemonadeStandMapper.requestDtoToEntity(lemonadeStandRequestDto)));
    }

    @Override
    public List<LemonadeStandResponseDto> getAllLemonadeStands() {
        return lemonadeStandMapper.entitiesToResponseDtos(lemonadeStandRepository.findAllByDeletedFalse());
    }

    @Override
    public LemonadeStandResponseDto getLemonadeStandById(Long id) {
        return lemonadeStandMapper.entityToResponseDto(getLemonadeStandFromRequest(id));
    }

    @Override
    public LemonadeStandResponseDto updateLemonadeStand(Long id, LemonadeStandRequestDto lemonadeStandRequestDto) {
        if (lemonadeStandRequestDto.getName() == null) {
            throw new BadRequestException("At least one field is required for updating a lemonadeStand.");
        }
        LemonadeStand lemonadeStand = getLemonadeStandFromRequest(id);

        if (lemonadeStand.getName() != null) {
            lemonadeStand.setName(lemonadeStandRequestDto.getName());
        }

        return lemonadeStandMapper.entityToResponseDto(lemonadeStandRepository.saveAndFlush(lemonadeStand));
    }

    @Override
    public LemonadeStandResponseDto deleteLemonadeStand(Long id) {
        LemonadeStand lemonadeStand = getLemonadeStandFromRequest(id);
        lemonadeStand.setDeleted(true);
        return lemonadeStandMapper.entityToResponseDto(lemonadeStandRepository.saveAndFlush(lemonadeStand));
    }

}
