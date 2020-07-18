package com.javatechie.spring.mokito.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.spring.mokito.api.model.Employee;
import com.javatechie.spring.mokito.api.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMokitoApplicationTests
{
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addEmpTest() throws Exception {
        Employee emp = new Employee();
        emp.setDept("IT");
        emp.setName("Aman");

        String jsonRequest = objectMapper.writeValueAsString(emp);

        MvcResult result = mockMvc.perform(post("/EmployeeService/addEmployee").content(jsonRequest)
                        .content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContect = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContect, Response.class);

        Assert.assertTrue(response.isStatus() == Boolean.TRUE);
    }

    @Test
    public void getEmpTest() throws Exception
    {
        MvcResult result = mockMvc.perform
                (get("/EmployeeService/getEmployee").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContect = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContect, Response.class);

        Assert.assertTrue(response.isStatus() == Boolean.TRUE);
    }


}
