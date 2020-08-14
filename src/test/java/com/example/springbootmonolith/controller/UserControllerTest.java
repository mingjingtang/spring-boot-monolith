package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.config.JwtUtil;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @InjectMocks
    private User user;

    @InjectMocks
    private UserRole userRole;

    @Spy
    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp(){
        initializeDummyUser();
        getUsers();
    }

    public void initializeDummyUser(){
        user.setUsername("superman");
        user.setPassword("super");

        userRole.setName("ROLE_DBA");
        user.setUserRole(userRole);
    }

    public List<User> getUsers(){
        userList.add(user);
        return userList;
    }



    @Test
    public void helloWorld_ReturnsString_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!!"));
    }

    @Test
    public void login_Returns200_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("superman","super"));

        when(userService.login(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();

        System.out.println("print:" + result.getResponse().getContentAsString());
    }

    @Test
    public void signup_Returns200_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("superman","super"));

        when(userService.signUp(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();

        System.out.println("print:" + result.getResponse().getContentAsString());
    }

    @Test
    public void listUsers_ReturnUser_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/list");

        when(userService.listUser()).thenReturn(userList);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is(401))
//                .andExpect(content().json("[{\"id\":1,\"username\":\"superman\",\"password\":\"$2a$10$6Ipv79jqR2.C0taEQc055ujm4hKl.TugIg7uelocCu7O6nwIZKJEm\",\"userProfile\":{\"id\":1,\"email\":\"superman@gmail.com\",\"mobile\":\"1234567\",\"address\":\"super st\"},\"userRole\":{\"id\":1,\"name\":\"ROLE_DBA\",\"users\":[1]},\"courses\":[{\"id\":1,\"code\":\"SHI\",\"name\":\"Super Hero Immersive\",\"users\":[1]}]}]"))
                .andReturn();

        System.out.println("print: " + result.getResponse().getContentAsString());
    }



    private static String createUserInJson (String name, String password) {
        return "{ \"username\": \"" + name + "\", " +
                "\"password\":\"" + password + "\"}";
    }
}
