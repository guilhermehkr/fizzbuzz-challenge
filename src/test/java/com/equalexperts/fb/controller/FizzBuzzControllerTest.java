package com.equalexperts.fb.controller;

import com.equalexperts.fb.exception.handler.CustomExceptionHandler;
import com.equalexperts.fb.service.FizzBuzz;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.equalexperts.fb.controller.FizzBuzzController.ROOT_PATH;
import static com.equalexperts.fb.controller.FizzBuzzController.SEQUENCE_END_PARAMETER_NAME;
import static com.equalexperts.fb.controller.FizzBuzzController.SEQUENCE_START_PARAMETER_NAME;
import static com.equalexperts.fb.exception.handler.CustomExceptionHandler.METHOD_PARAMETER_TYPE_MISMATCH;
import static com.equalexperts.fb.exception.handler.CustomExceptionHandler.MISSING_REQUEST_PARAMETER_MESSAGE;
import static java.lang.String.format;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        FizzBuzzController.class,
        CustomExceptionHandler.class
})
public class FizzBuzzControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private FizzBuzzController fizzBuzzController;

    @MockBean
    private FizzBuzz fizzBuzz;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    private final static String FULL_REQUEST = "%s?sequenceStart=%s&sequenceEnd=%s";
    private final static String PARTIAL_REQUEST = "%s?sequenceStart=%d";
    private final static String PLAY_RESULT = "1 2 fizz 4";

    @Before
    public void setup() {
        mockMvc =
                standaloneSetup(fizzBuzzController)
                        .setControllerAdvice(customExceptionHandler)
                        .build();
    }

    @Test
    public void shouldRequestDemoAndReturnDefaultMessage() throws Exception {

        Integer sequenceStart = 1;
        Integer sequenceEnd = 4;

        MockHttpServletRequestBuilder getRequest =
                get(format(FULL_REQUEST, ROOT_PATH, sequenceStart, sequenceEnd));

        given(fizzBuzz.play(sequenceStart, sequenceEnd)).willReturn(PLAY_RESULT);

        mockMvc.perform(getRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(PLAY_RESULT));

        verify(fizzBuzz).play(sequenceStart, sequenceEnd);
    }

    @Test
    public void shouldReturnMissingParameterErrorMessage() throws Exception  {

        mockMvc.perform(get(ROOT_PATH))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(format(MISSING_REQUEST_PARAMETER_MESSAGE, SEQUENCE_START_PARAMETER_NAME)));

        mockMvc.perform(get(format(PARTIAL_REQUEST, ROOT_PATH, 10)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(format(MISSING_REQUEST_PARAMETER_MESSAGE, SEQUENCE_END_PARAMETER_NAME)));

        String wrongParameter = "$$$";
        mockMvc.perform(get(format(FULL_REQUEST, ROOT_PATH, wrongParameter, 10)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(format(METHOD_PARAMETER_TYPE_MISMATCH, wrongParameter, SEQUENCE_START_PARAMETER_NAME)));
    }
}