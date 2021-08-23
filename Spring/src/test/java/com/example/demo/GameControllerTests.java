package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.Controller.GameController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest()
class GameControllerTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private GameController gameController;

    @Test
    void newGame() throws Exception {
        mockMvc.perform(get("/game")).andExpect(status().isOk());
        assertThat(gameController).isNotNull();
    }
}
