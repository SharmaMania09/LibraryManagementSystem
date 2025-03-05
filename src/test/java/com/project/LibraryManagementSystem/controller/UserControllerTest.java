package com.project.LibraryManagementSystem.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.LibraryManagementSystem.model.User;
import com.project.LibraryManagementSystem.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("MEMBER");
    }

    /* 
    @Test
    void testShowRegisterForm() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    */

    @Test
    void testRegisterUser() throws Exception {
        doNothing().when(userService).saveUser(any(User.class));

        mockMvc.perform(post("/users/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection());

        verify(userService, times(1)).saveUser(any(User.class));
    }

    

    @Test
    void testLogin_Success() throws Exception {
        when(userService.verifyUser(any(User.class))).thenReturn("mocked-jwt-token");

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("mocked-jwt-token"));

        verify(userService, times(1)).verifyUser(any(User.class));
    }

    @Test
    void testLogin_Failed() throws Exception {
        when(userService.verifyUser(any(User.class))).thenReturn("Failed");

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    void testGetDashboardView() throws Exception {
        mockMvc.perform(get("/users/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-dashboard"));
    }
}
