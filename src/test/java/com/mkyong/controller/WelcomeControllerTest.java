package com.mkyong.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePageShowsInitialTasks() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attribute("tasks", hasItem("Buy groceries")));
    }

    @Test
    public void addTaskRedirectsAndPersists() throws Exception {
        mockMvc.perform(post("/").param("task", "New test task"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/"))
                .andExpect(model().attribute("tasks", hasItem("New test task")));
    }

    @Test
    public void filterReturnsOnlyMatchingTasks() throws Exception {
        mockMvc.perform(get("/").param("filter", "groceries"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("Buy groceries")))
                .andExpect(model().attribute("tasks", not(hasItem("Read a book"))));
    }

    @Test
    public void emptyTaskIsNotAdded() throws Exception {
        mockMvc.perform(post("/").param("task", "   "))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/"))
                .andExpect(model().attribute("tasks", not(hasItem("   "))));
    }

    @Test
    public void helloEndpointShowsName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "World"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("message", "World"));
    }
}
