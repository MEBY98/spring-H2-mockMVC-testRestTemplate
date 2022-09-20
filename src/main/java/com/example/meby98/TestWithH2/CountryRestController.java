package com.example.meby98.TestWithH2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryRestController {
    @Autowired
    CountryService countryService;

    @GetMapping
    public List<CountryModel> getAll() {
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CountryModel> getById(@PathVariable(name = "id") Long id) {
        return this.countryService.findById(id);
    }

    @PostMapping
    public Long add(@RequestBody CountryRequest newCountry) {
        return this.countryService.create(newCountry);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable(name = "id") Long id, @RequestBody CountryRequest updatedCountry) {
        return this.countryService.update(id, updatedCountry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.countryService.delete(id);
    }
}
