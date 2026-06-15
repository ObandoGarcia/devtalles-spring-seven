package com.obando.porfolio_backend.repository;

import com.obando.porfolio_backend.model.PersonalInfo;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoRepository {

    PersonalInfo save(PersonalInfo personalInfo);

    Optional<PersonalInfo> findByiD(Long id);

    List<PersonalInfo> findAll();

    void deleteById(Long id);
}
