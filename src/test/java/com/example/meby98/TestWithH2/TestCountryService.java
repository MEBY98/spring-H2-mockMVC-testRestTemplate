package com.example.meby98.TestWithH2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TestCountryService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	CountryService countryService;

	@BeforeEach
	void setUpDB() {
		countryRepository.save(new CountryModel(Long.valueOf(1), "Mexico", 130497248));
		countryRepository.save(new CountryModel(Long.valueOf(2), "Spain", 49067981));
		countryRepository.save(new CountryModel(Long.valueOf(3), "Colombia", 46070146));
		countryRepository.save(new CountryModel(Long.valueOf(4), "Cuba", 46070146));
	}

	@Test
	void findAll() {
		assertEquals(4, countryService.findAll().size());
	}

	@Test
	void testFindById() {
		CountryModel testFindByIdRepository = countryRepository.findById(Long.valueOf(1)).get();
		CountryModel testFindByIdService = countryService.findById(Long.valueOf(1)).get();
		assertEquals(testFindByIdRepository.getName(), testFindByIdService.getName());
		assertEquals(testFindByIdRepository.getPopulation(), testFindByIdService.getPopulation());
	}

}
