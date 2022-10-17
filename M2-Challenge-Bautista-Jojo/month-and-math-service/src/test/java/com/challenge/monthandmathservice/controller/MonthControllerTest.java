package com.challenge.monthandmathservice.controller;

import com.challenge.monthandmathservice.model.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)
public class MonthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturn422StatusCodeWhenMonthNumberIsNotBetween1To12 () throws Exception{
        // Arrange and Act
        mockMvc.perform(
                get("/month/15")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void shouldReturnTheMonthInStringWhenMonthNumberIsBetween1To12 () throws Exception {
        // Arrange
        Month outputMonth = new Month();
        outputMonth.setNumber("3");
        outputMonth.setName("March");

        String outputJson = mapper.writeValueAsString(outputMonth);

        // Act
        mockMvc.perform(
                        get("/month/3"))
                .andDo(print())
                .andExpect(content().json(outputJson)) // Assert show expected month
                .andExpect(status().isOk()); // Assert that we get code 200 OK
    }

    @Test
    public void shouldReturnTheMonthInStringWhenRandomGeneratedANumberBetween1To12 () throws Exception{

        // Arrange and Act
        mockMvc.perform(
                get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk()); // Assert that we get code 200 OK
    }
}