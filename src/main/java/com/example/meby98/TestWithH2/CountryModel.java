package com.example.meby98.TestWithH2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "countries")
@NoArgsConstructor
public class CountryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer population;

    public CountryModel(Long id, String name, Integer population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public CountryModel(String name, Integer population) {
        this.name = name;
        this.population = population;
    }
}
