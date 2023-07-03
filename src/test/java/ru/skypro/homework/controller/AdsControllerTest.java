package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.service.AdService;

@WebMvcTest(AdsController.class)
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AdsController controller;

    @SpyBean
    private AdService service;

    /*@Test
    public void shouldGetAllAds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/ads")
                .content()
                .contentType(MediaType.APPLICATION_JSON))
    }*/
}
