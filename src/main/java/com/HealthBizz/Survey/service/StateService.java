package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.StateDto;
import com.HealthBizz.Survey.entity.State;
import com.HealthBizz.Survey.reporsitory.StateRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private ObjectMapper objectMapper;

    public List<StateDto> viewAllStates() {
        List<State> allStates = stateRepo.findAll();
        List<StateDto> stateList = allStates.stream()
                .map(state -> {
                    StateDto theState = objectMapper.convertValue(state,StateDto.class);
                    return theState;
                })
                .collect(Collectors.toList());

        return stateList;
    }
}
