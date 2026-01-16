package com.monitoredrx.patient.controller;

import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.service.PatientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PatientController.class)
@DisplayName("PatientControllerTest")
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("getPatientsTest")
    public void getPatientsTest() throws Exception {
        PatientResponse response = new PatientResponse();
        response.setId(UUID.randomUUID().toString());

        when(patientService.getAllPatients())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(patientService).getAllPatients();
    }

    @Test
    @DisplayName("getPatientsByPageTest")
    public void getPatientsByPageTest() throws Exception {
        PatientResponse response = new PatientResponse();
        response.setId(UUID.randomUUID().toString());

        Page<PatientResponse> page =
                new PageImpl<>(List.of(response), PageRequest.of(0, 16), 1);

        when(patientService.getPatientsByPage(any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/api/v1/patients/pages")
                        .param("page", "0")
                        .param("size", "16"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    @DisplayName("getPatientTest")
    public void getPatientTest() throws Exception {
        UUID id = UUID.randomUUID();

        PatientResponse response = new PatientResponse();
        response.setId(id.toString());

        when(patientService.getPatientById(id.toString()))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/patients/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

}
