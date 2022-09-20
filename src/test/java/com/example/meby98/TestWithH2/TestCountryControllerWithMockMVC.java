package com.example.meby98.TestWithH2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class TestCountryControllerWithMockMVC {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUpDB() {
        countryRepository.save(new CountryModel(Long.valueOf(1), "Mexico", 130497248));
        countryRepository.save(new CountryModel(Long.valueOf(2), "Spain", 49067981));
        countryRepository.save(new CountryModel(Long.valueOf(3), "Colombia", 46070146));
        countryRepository.save(new CountryModel(Long.valueOf(4), "Cuba", 46070146));
    }

    @Test
    public void testGetAllCountries() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/country")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                objectMapper.writeValueAsString(this.countryRepository.findAll()));
    }

    @Test
    void testPostCountry() throws JsonProcessingException, Exception {
        MvcResult result = this.mockMvc.perform(post("/api/country").contentType("application/json")
                .content(objectMapper.writeValueAsString(new CountryRequest("Name", 1000)))).andReturn();
        List<CountryModel> countriesOnDB = this.countryRepository.findAll();
        assertEquals(countriesOnDB.get(countriesOnDB.size() - 1).getId().toString(),
                result.getResponse().getContentAsString());
    }
}
