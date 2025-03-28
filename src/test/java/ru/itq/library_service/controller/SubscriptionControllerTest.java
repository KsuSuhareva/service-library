package ru.itq.library_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itq.library_service.service.SubscriptionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubscriptionControllerTest extends IntegrationTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @Test
    public void testGetByUserFullName() throws Exception {
        String userFullName = "Suhareva Ksu";

        mockMvc.perform(get("/subscriptions/by-user-full-name")
                        .param("userFullName", userFullName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userFullName").value(userFullName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value("k.sukhareva@itq-group.com"));
    }

    @Test
    public void testGetByUserFullName_Not_Found() throws Exception {
        String userFullName = "Not Found";

        mockMvc.perform(get("/subscriptions/by-user-full-name")
                        .param("userFullName", userFullName))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Абонемент не найден по ФИО: Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("NOT_FOUND"));
    }

}
