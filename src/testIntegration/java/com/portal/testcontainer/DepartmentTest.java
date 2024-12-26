package com.portal.testcontainer;

import com.portal.testcontainer.model.Department;
import com.portal.testcontainer.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestContainerConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        departmentRepository.deleteAll();

        List<Department> departments = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            departments.add(Department.builder()
                    .name("Department " + i)
                    .head("Head " + i)
                    .build());
        }
        departmentRepository.saveAll(departments);

    }

    @Test
    void testGetDepartments() throws Exception {
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Department 1"))
                .andExpect(jsonPath("$[0].head").value("Head 1"));
    }
}
