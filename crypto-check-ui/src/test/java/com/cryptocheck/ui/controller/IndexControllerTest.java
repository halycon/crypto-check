package com.cryptocheck.ui.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
//@WebFluxTest(IndexController.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private WebTestClient webTestClient;

//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }

    @Test
    public void test_getIndexPage_when_search_emptyURL() throws Exception {
        webTestClient
                // We then create a GET request to test an endpoint
                .get().uri("")
                .exchange()
                .expectStatus().isOk();
//        mockMvc.perform(get(""))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("test"))
//                .andExpect(view().name("index"));
    }

    @Test
    public void test_getIndexPage_when_searchWith_slash() throws Exception {
        webTestClient
                // We then create a GET request to test an endpoint
                .get().uri("/")
                .exchange()
                .expectStatus().isOk();
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("test"))
//                .andExpect(view().name("index"));
    }
}