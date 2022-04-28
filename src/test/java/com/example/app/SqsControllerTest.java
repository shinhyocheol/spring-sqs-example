package com.example.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SqsController.class)
public class SqsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 메세지를_큐에_등록() throws Exception {

        mockMvc.perform(get("/api/v1/send"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}