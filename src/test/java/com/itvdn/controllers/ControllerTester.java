package com.itvdn.controllers;

import com.itvdn.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/app-simple-ctx.xml"})
@WebAppConfiguration
@FixMethodOrder
public class ControllerTester {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testController() throws Exception {
        mockMvc.perform(get("/bye2")).andDo(print()).andExpect(view().name("bye"));
    }

    @Test
    public void testController2() throws Exception {
        mockMvc.perform(get("/bye2")).andExpect(status().isOk()).andExpect(view().name("bye"));
    }

    @Test
    public void testController3() throws Exception {
        mockMvc.perform(get("/path/{studentName}", "Grisha"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testController4() throws Exception {
        mockMvc.perform(get("/rest/{studentName}", "Oleeg"))
                .andExpect(status().is2xxSuccessful());
        Assert.assertTrue(mockMvc.perform(get("/rest/{studentName}", "Oleeg"))
                .andReturn().getResponse().getContentAsString().contains("Olee"));
    }

    @Test
    public void testController5() throws Exception {
        mockMvc.perform(post("/addStudent").flashAttr("student", new Student(35, "Vasily", 1)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("result"))
                .andDo(print());
    }

    @Test
    public void testController6() throws Exception {
        mockMvc.perform(post("/addStudent")
                        .param("age", String.valueOf(35))
                        .param("name", "Vasily")
                        .param("id", String.valueOf(1)).flashAttr("student", new Student()))
                        .andExpect(status().is2xxSuccessful()).andExpect(forwardedUrl("result"))
                        .andExpect(view().name("result"))
                        .andDo(print());
    }

    @Test
    public void testController7() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testController8() throws Exception {
        mockMvc.perform(get("/all")).andExpect(status().is2xxSuccessful());
    }

    /*Integration*/
    @Test
    public void testController9() throws Exception {
        mockMvc.perform(post("/employee/add").param("name", "Oleg")
        .param("position", "CEO").param("phone", "042")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testController10() throws Exception {
        System.out.println(mockMvc.perform(post("/employee/findByNameAndPosition").param("name", "Oleg")
                .param("position", "CEO")).andExpect(status().is2xxSuccessful()).andReturn().getModelAndView().getModel().get("employees"));
    }




    @Test
    public void testController11() throws Exception {
        System.out.println(Objects.requireNonNull(mockMvc.perform(post("/employee/findByName").param("name", "Oleg"))
                .andExpect(status().is2xxSuccessful()).andReturn().getModelAndView()).getModel().get("employees"));
    }
}
