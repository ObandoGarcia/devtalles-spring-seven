package com.obando.porfolio_backend.repository;

import com.obando.porfolio_backend.model.PersonalInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = (resultSet, rowNumber) -> {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(resultSet.getLong("id"));
        personalInfo.setFirstName(resultSet.getString("first_name"));
        personalInfo.setLastName(resultSet.getString("last_name"));
        personalInfo.setEmail(resultSet.getString("email"));
        personalInfo.setPhoneNumber(resultSet.getString("phone"));
        personalInfo.setTitle(resultSet.getString("title"));
        personalInfo.setProfileDescription(resultSet.getString("profile_description"));
        personalInfo.setProfileImageUrl(resultSet.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(resultSet.getInt("years_of_experience"));
        personalInfo.setLinkedinUrl(resultSet.getString("linkedin_url"));
        personalInfo.setGithubUrl(resultSet.getString("github_url"));

        return personalInfo;
    };

    public PersonalInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if (personalInfo.getId() == null) {
            String sql = "insert into personal_info (first_name, last_name, title, profile_description, " +
                "profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) " +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, personalInfo.getFirstName());
                preparedStatement.setString(2, personalInfo.getLastName());
                preparedStatement.setString(3, personalInfo.getTitle());
                preparedStatement.setString(4, personalInfo.getProfileDescription());
                preparedStatement.setString(5, personalInfo.getProfileImageUrl());
                if (personalInfo.getYearsOfExperience() != null) {
                    preparedStatement.setInt(6, personalInfo.getYearsOfExperience());
                } else {
                    preparedStatement.setNull(6, java.sql.Types.INTEGER);
                }
                preparedStatement.setString(7, personalInfo.getEmail());
                preparedStatement.setString(8, personalInfo.getPhoneNumber());
                preparedStatement.setString(9, personalInfo.getLinkedinUrl());
                preparedStatement.setString(10, personalInfo.getGithubUrl());
                return preparedStatement;
            }, keyHolder);

            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {
            String sql = "update personal_info set first_name = ?, last_name = ?, title = ?, " +
                    "profile_description = ?, profile_image_url = ?, " +
                    "years_of_experience = ?, email = ?, " +
                    "phone = ?, linkedin_url = ?, github_url = " +
                    "where id = ?";
            jdbcTemplate.update(sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhoneNumber(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId());
        }

        return personalInfo;
    }

    @Override
    public Optional<PersonalInfo> findByiD(Long id) {
        String sql = "select * from personal_info where id = ?";

        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "select * from personal_info";
        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from personal_info where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
