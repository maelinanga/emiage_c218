package com.websevice.gepers.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.websevice.gepers.modele.RequestsLeaves;
import com.websevice.gepers.service.RequestsLeavesService;
import com.websevice.gepers.controller.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestsLeavesService requestService;

    @Test
    public void testSaveRequest() throws Exception {
        // Prepare test data
        RequestsLeaves request = new RequestsLeaves(); // Set necessary properties f

        // Mock the behavior of requestService
        doNothing().when(requestService).saveRequest(request);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/saveRequest")
                .flashAttr("request", request))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/monprofil"));

        // Verify the behavior
        verify(requestService).saveRequest(request);
    }
}