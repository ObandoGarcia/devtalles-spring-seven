package com.obando.porfolio_backend.service;

import com.obando.porfolio_backend.model.PersonalInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoServiceImpl implements IPersonalInfoService{

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return null;
    }

    @Override
    public Optional<PersonalInfo> findByiD(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonalInfo> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
