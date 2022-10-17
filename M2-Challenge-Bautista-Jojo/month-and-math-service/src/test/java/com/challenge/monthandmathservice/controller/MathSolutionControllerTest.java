package com.challenge.monthandmathservice.controller;

import com.challenge.monthandmathservice.model.MathSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private MathSolution calcNumbers;

    @Before
    public void setUp() {
        calcNumbers = new MathSolution();
    }

    @Test
    public void shouldReturn422StatusCodeWhenDividingANumberByZero () throws Exception {
        // Arrange
        calcNumbers.setOperand1("7");
        calcNumbers.setOperand2("0");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenBothOperand1AndOperand2AreNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenBothOperand1AndOperand2AreNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenBothOperand1AndOperand2AreNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenBothOperand1AndOperand2AreNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand1IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand1IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand1IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand1IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand2IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand2IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand2IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand2IsNull () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenBothOperand1AndOperand2AreMissing () throws Exception {
        // Arrange
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenBothOperand1AndOperand2AreMissing () throws Exception {
        // Arrange
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenBothOperand1AndOperand2AreMissing () throws Exception {
        // Arrange
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenBothOperand1AndOperand2AreMissing () throws Exception {
        // Arrange
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand1IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand1IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand1IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand1IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand2IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand2IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand2IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand2IsMissing () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand1IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("Any text");
        calcNumbers.setOperand2("3");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand1IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("Any text");
        calcNumbers.setOperand2("3");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand1IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("Any text");
        calcNumbers.setOperand2("3");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand1IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("Any text");
        calcNumbers.setOperand2("3");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand2IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("7");
        calcNumbers.setOperand2("Any text");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand2IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("7");
        calcNumbers.setOperand2("Any text");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand2IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("7");
        calcNumbers.setOperand2("Any text");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand2IsNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("7");
        calcNumbers.setOperand2("Any text");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand1AndOperand2AreNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("I am a text");
        calcNumbers.setOperand2("I too");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand1AndOperand2AreNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("I am a text");
        calcNumbers.setOperand2("I too");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand1AndOperand2AreNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("I am a text");
        calcNumbers.setOperand2("I too");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand1AndOperand2AreNonNumericString () throws Exception {
        // Arrange
        calcNumbers.setOperand1("I am a text");
        calcNumbers.setOperand2("I too");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenBothOperand1AndOperand2AreBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2(Boolean.toString(false));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenBothOperand1AndOperand2AreBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2(Boolean.toString(false));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenBothOperand1AndOperand2AreBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2(Boolean.toString(false));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenBothOperand1AndOperand2AreBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(null);
        calcNumbers.setOperand2(null);
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand1IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand1IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand1IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand1IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1(Boolean.toString(true));
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturn422StatusCodeWhenOperand2IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(Boolean.toString(true));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void subtractReturn422StatusCodeWhenOperand2IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(Boolean.toString(true));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void multiplyReturn422StatusCodeWhenOperand2IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(Boolean.toString(true));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void divideReturn422StatusCodeWhenOperand2IsBoolean () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2(Boolean.toString(true));
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity()); // Assert that we get back Exception code 422
    }

    @Test
    public void addReturnTheSumOfTwoNumbers () throws Exception {
        // Arrange
        calcNumbers.setOperand1("8");
        calcNumbers.setOperand2("7");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.answer").value(15)) // Assert that we get back the sum of the given numbers
                .andExpect(status().isCreated());
    }

    @Test
    public void subtractReturnTheDifferenceOfTwoNumbers () throws Exception {
        // Arrange
        calcNumbers.setOperand1("12");
        calcNumbers.setOperand2("7");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.answer").value(5)) // Assert that we get back the difference of the given numbers
                .andExpect(status().isCreated());
     }

    @Test
    public void multiplyReturnTheProductOfTwoNumbers () throws Exception {
        // Arrange
        calcNumbers.setOperand1("-3");
        calcNumbers.setOperand2("7");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.answer").value(-21)) // Assert that we get back the product of the given numbers
                .andExpect(status().isCreated());
    }

    @Test
    public void divideReturnTheQuotientOfTwoNumbers () throws Exception {
        // Arrange
        calcNumbers.setOperand1("18");
        calcNumbers.setOperand2("5");
        String inputJson = mapper.writeValueAsString(calcNumbers);

        // Act
        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.answer").value(3)) // Assert that we get back the product of the given numbers
                .andExpect(status().isCreated());
    }
}