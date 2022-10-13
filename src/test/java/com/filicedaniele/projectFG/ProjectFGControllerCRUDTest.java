package com.filicedaniele.projectFG;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.filicedaniele.projectFG.Data.UserDto;
import com.filicedaniele.projectFG.Data.UserRequest;
import com.filicedaniele.projectFG.controller.UserController;
import com.filicedaniele.projectFG.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class ProjectFGControllerCRUDTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testget() throws Exception {
        UserDto user = new UserDto("test", "test", "test@gmail.com");
        List<UserDto> users = Arrays.asList(user);

        Mockito.when(userService.get(null, null)).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.users[0].nome", Matchers.is("test")));
    }

    @Test
    public void testpost() throws Exception {

        UserDto user = new UserDto("test", "test", "test@gmail.com");
        String json = "{\n" +
                "    \"nome\": \"test\",\n" +
                "        \"cognome\": \"test\",\n" +
                "        \"email\": \"test@gmail.com\"\n" +
                "}";

        Mockito.when(userService.create(Mockito.any())).thenReturn(user);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_created.nome", Matchers.is("test")))
                .andReturn();
    }

    @Test
    public void testdelete() throws Exception {
        UserDto user = new UserDto("test", "test", "test@gmail.com");

        userService.create(new UserRequest("test", "test", "test@gmail.com"));

        Mockito.when(userService.delete("test@gmail.com")).thenReturn(user);

        mockMvc.perform(delete("/user")
                .param("email", "test@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_deleted.nome", Matchers.is("test")))
                .andReturn();


    }

    @Test
    public void testupdate() throws Exception {

        UserDto user = new UserDto("test", "test", "test@gmail.com");
        String json = "{\n" +
                "    \"nome\": \"test\",\n" +
                "        \"cognome\": \"test\",\n" +
                "        \"email\": \"test@gmail.com\"\n" +
                "}";

        Mockito.when(userService.update(Mockito.any())).thenReturn(user);

        mockMvc.perform(patch("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_updated.email", Matchers.is("test@gmail.com")))
                .andReturn();
    }


}

