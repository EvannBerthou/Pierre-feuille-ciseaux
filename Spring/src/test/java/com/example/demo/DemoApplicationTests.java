package com.example.demo;

import com.example.demo.Controller.GameController;
import com.example.demo.Controller.UserController;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired private GameController gameController;
    @Autowired private UserController userController;

	@Test
	void contextLoads() {
        assertThat(gameController).isNotNull();
        assertThat(userController).isNotNull();
    }
}
