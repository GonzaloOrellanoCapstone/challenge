package com.tenpo.challenge.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.tenpo.challenge.payload.request.LoginRequest;
import com.tenpo.challenge.payload.request.SignupRequest;
import com.tenpo.challenge.payload.request.TokenRefreshRequest;
import com.tenpo.challenge.service.AuthService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AuthController.class)
@RunWith(SpringRunner.class)
public class AuthControllerTest extends AbstractTest {

    @MockBean
    private AuthService authService;
    private final static String URI = "/api/auth";

    @Test
    public void authentucateUserTest() throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post(URI.concat("/signin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aJsonLoginRequest(createLoginRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUserTest() throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post(URI.concat("/signup"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aJsonRegisterRequest(createSignupRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void refreshTokenTest() throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post(URI.concat("/refreshtoken"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aJsonTokenRefreshRequest(createRefreshTokenRequest())))
                .andExpect(status().isOk());
    }

    /*@Test
    public void signoutTest() throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post(URI.concat("/signout"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

    private LoginRequest createLoginRequest() {
        return new LoginRequest("username", "password");
    }

    private SignupRequest createSignupRequest() {
        return new SignupRequest("username", "email", null, "password");
    }

    private TokenRefreshRequest createRefreshTokenRequest() {
        return new TokenRefreshRequest("token");
    }

    private String aJsonLoginRequest(LoginRequest request){
        final Gson prettyGson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        JsonElement element = prettyGson.toJsonTree(request);
        element.getAsJsonObject().addProperty("@type", "user");

        return prettyGson.toJson(element);
    }

    private String aJsonRegisterRequest(SignupRequest request){
        final Gson prettyGson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        JsonElement element = prettyGson.toJsonTree(request);
        element.getAsJsonObject().addProperty("@type", "user");

        return prettyGson.toJson(element);
    }

    private String aJsonTokenRefreshRequest(TokenRefreshRequest request){
        final Gson prettyGson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        JsonElement element = prettyGson.toJsonTree(request);
        element.getAsJsonObject().addProperty("@type", "user");

        return prettyGson.toJson(element);
    }
}
