package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.TalukaDto;
import com.HealthBizz.Survey.entity.Taluka;
import com.HealthBizz.Survey.reporsitory.TalukaRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TalukaService {

    @Autowired
    private TalukaRepo talukaRepo;

    @Autowired
    private ObjectMapper objectMapper;

    public List<TalukaDto> viewAllTalukas() {
        List<Taluka> allTaluka = talukaRepo.findAll();
        List<TalukaDto> talukaList = allTaluka.stream()
                .map(taluka -> {
                    TalukaDto theTaluka = objectMapper.convertValue(taluka,TalukaDto.class);
                    theTaluka.setDistrictName(taluka.getDistrict().getName());
                    return theTaluka;
                })
                .collect(Collectors.toList());

        return talukaList;
    }
}
