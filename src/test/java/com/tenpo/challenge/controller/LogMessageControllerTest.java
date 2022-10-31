package com.tenpo.challenge.controller;

import com.tenpo.challenge.repository.LogMessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LogMessageController.class)
@RunWith(SpringRunner.class)
public class LogMessageControllerTest extends AbstractTest {

    @MockBean
    private LogMessageRepository logMessageRepository;
    private final static String URI = "/api/log-message";

    @Test
    public void allAccessTest() throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get(URI.concat("/all"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
