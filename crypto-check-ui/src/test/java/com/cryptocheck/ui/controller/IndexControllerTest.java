package com.cryptocheck.ui.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

public class IndexControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    IndexController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test_getIndexPage_when_search_emptyURL() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("test"))
                .andExpect(view().name("index"));
    }

    @Test
    public void test_getIndexPage_when_searchWith_slash() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("test"))
                .andExpect(view().name("index"));
    }
}