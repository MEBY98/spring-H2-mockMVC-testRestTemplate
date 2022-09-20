package com.example.meby98.TestWithH2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<CountryModel> findAll() {
        return this.countryRepository.findAll();
    }

    public Optional<CountryModel> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    public Long create(CountryRequest newCountry) {
        return this.countryRepository.save(new CountryModel(newCountry.getName(), newCountry.getPopulation())).getId();
    }

    public boolean update(Long id, CountryRequest updatedCountry) {
        Optional<CountryModel> country = this.countryRepository.findById(id);
        if (country.isPresent()) {
            country.get().setName(updatedCountry.getName());
            country.get().setPopulation(updatedCountry.getPopulation());
            return true;
        } else {
            return false;
        }
    }

    public void delete(Long id) {
        this.countryRepository.deleteById(id);
    }
}
