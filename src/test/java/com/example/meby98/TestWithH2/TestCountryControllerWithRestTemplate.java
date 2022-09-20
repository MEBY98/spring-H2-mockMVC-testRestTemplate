package com.example.meby98.TestWithH2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestCountryControllerWithRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUpDB() {
        countryRepository.save(new CountryModel(Long.valueOf(1), "Mexico", 130497248));
        countryRepository.save(new CountryModel(Long.valueOf(2), "Spain", 49067981));
        countryRepository.save(new CountryModel(Long.valueOf(3), "Colombia", 46070146));
        countryRepository.save(new CountryModel(Long.valueOf(4), "Cuba", 46070146));
    }

    @Test
    public void testGetAllCountries() {
        CountryModel[] allCountries = restTemplate
                .getForEntity("http://localhost:" + port + "/api/country", CountryModel[].class).getBody();
        assertEquals(4, allCountries.length);
    }

    @Test
    void testPostCountry() {
        Long createdCountryId = restTemplate
                .postForEntity("http://localhost:" + port + "/api/country", new CountryRequest("Name", 1000),
                        Long.class)
                .getBody();
        List<CountryModel> countriesOnDB = this.countryRepository.findAll();
        assertEquals(countriesOnDB.get(countriesOnDB.size() - 1).getId(), createdCountryId);
    }
}
