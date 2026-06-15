package com.obando.porfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    private Long id;
    private String name;
    private Integer levelPercentage;
    private String iconClass;
    private Long personalInfoId;
}
