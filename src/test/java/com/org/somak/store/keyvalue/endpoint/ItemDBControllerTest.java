package com.org.somak.store.keyvalue.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.somak.store.keyvalue.dto.Item;
import com.org.somak.store.keyvalue.dto.Product;
import com.org.somak.store.keyvalue.exception.CustomEventException;
import com.org.somak.store.keyvalue.service.ItemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = { "jasypt.encryptor.password=annotation" })
@ActiveProfiles("test")
public class ItemDBControllerTest {

    static {
        System.setProperty("jasypt.encryptor.password", "password");
        System.setProperty("user.name", "password");
    }
    @BeforeAll
    public static void before() {
        System.setProperty("jasypt.encryptor.password", "password");
    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService service;
    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testGetListByKind() throws Exception {

        //given
        String id = "someId";
        Product p = new Product();
        p.setName("name");
        p.setID("someId");
        //when
        doReturn(Arrays.asList(p)).when(service).listObjects(any());
        //then
        mvc
                .perform(
                        get("/rest/store/findAll?kind=Product")
                )
                .andExpect(status().isOk());


        //when
        doThrow(CustomEventException.class).when(service).listObjects(any());
        //then
        mvc
                .perform(
                        get("/rest/store/findAll?kind=Product")
                )
                .andExpect(status().is4xxClientError());

    }


    @Test
    public void testGetObjectByName() throws Exception {

        //given
        String id = "someId";
        Product p = new Product();
        p.setName("name");
        p.setID("someId");
        //when
        doReturn(p).when(service).getObjectByName(any());
        //then
        mvc
                .perform(
                        get("/rest/store/name?name=someId")
                )
                .andExpect(status().isOk());


        //when
        doThrow(NoSuchElementException.class).when(service).getObjectByName(any());
        //then
        mvc
                .perform(
                        get("/rest/store/name?name=someId")
                )
                .andExpect(status().is4xxClientError());

        //when
        doThrow(CustomEventException.class).when(service).getObjectByName(any());
        //then
        mvc
                .perform(
                        get("/rest/store/name?name=someId")
                )
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void testGetObjectByID() throws Exception {

        //given
        String id = "someId";
        Product p = new Product();
        p.setName("name");
        p.setID("someId");
        //when
        doReturn(p).when(service).getById(any());
        //then
        mvc
                .perform(
                        get("/rest/store/?id=someId")
                )
                .andExpect(status().isOk());


        //when
        doThrow(NoSuchElementException.class).when(service).getById(any());
        //then
        mvc
                .perform(
                        get("/rest/store/?id=someId")
                )
                .andExpect(status().is4xxClientError());

        //when
        doThrow(CustomEventException.class).when(service).getById(any());
        //then
        mvc
                .perform(
                        get("/rest/store/?id=someId")
                )
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void testDelete() throws Exception {

        //given
        Product p = new Product();
        p.setName("name");

        //when & then

        doReturn(true).when(service).deleteObject(any(String.class));

        mvc
                .perform(
                        delete("/rest/store/?id=344535")
                )
                .andExpect(status().isOk());
    }


    @Test
    public void testSave() throws Exception {

        //given
        Product p = new Product();
        p.setName("name");

        //when & then

        doReturn("someId").when(service).saveItem(any(Item.class));
        mvc
            .perform(
                    post("/rest/store/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapToJson(p))
            )
            .andExpect(status().isCreated());
    }
}
