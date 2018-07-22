package com.cryptocheck.microservices.eth.controller;

import com.cryptocheck.microservices.eth.domain.repository.Price;
import com.cryptocheck.microservices.eth.domain.service.PriceRequest;
import com.cryptocheck.microservices.eth.domain.service.PriceResponse;
import com.cryptocheck.microservices.eth.domain.service.SMACriteria;
import com.cryptocheck.microservices.eth.repository.IEthH2Repository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWebMvc
public class EthServiceControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    public IEthH2Repository h2Repository;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(EthServiceController.class).build();
        Price priceRandom1 = new Price();
        priceRandom1.setPriceBTC(BigDecimal.valueOf(10000));
        priceRandom1.setPriceUSD(BigDecimal.valueOf(20000));
        h2Repository.save(priceRandom1);
    }

    @Test
    public void getEthPrice_NoParam_returnsPriceResponse() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/getEthPrice")
                        .contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        if (result.getRequest().isAsyncStarted()){
            mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(result))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is(true)));
        }

    }

    @Test
    public void getEthPrice_WithSmaCriterias_returnsPriceResponse() {

        PriceRequest request = new PriceRequest();
        request.setSmaCriteria(new SMACriteria(1,1));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ResponseEntity<PriceResponse> response = testRestTemplate.postForEntity("/getEthPrice",new HttpEntity<>(request, headers),
                PriceResponse.class);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assert.assertNotNull("responseBody is not null", response.getBody());
        Assert.assertTrue("sma price is greater than 0", !response.getBody().getSmaPrice().equals(BigDecimal.ZERO));

    }
}
