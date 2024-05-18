package ch.zhaw.iwi.devops.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LogbookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        // Clear logbook before each test
        mockMvc.perform(delete("/logbook")).andExpect(status().isOk());
    }

    @Test
    public void testAddEntry() throws Exception {
        // Add a new logbook entry
        mockMvc.perform(post("/logbook")
                .content("Test entry")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify the entry is added
        mockMvc.perform(get("/logbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].message").value("Test entry"));
    }

    @Test
    public void testGetAllEntries() throws Exception {
        // Add two new logbook entries
        mockMvc.perform(post("/logbook")
                .content("Entry 1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        
        mockMvc.perform(post("/logbook")
                .content("Entry 2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify both entries are returned
        mockMvc.perform(get("/logbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].message").value("Entry 1"))
                .andExpect(jsonPath("$[1].message").value("Entry 2"));
    }

    @Test
    public void testDeleteMostRecentEntry() throws Exception {
        // Add two new logbook entries
        mockMvc.perform(post("/logbook")
                .content("Entry 1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        
        mockMvc.perform(post("/logbook")
                .content("Entry 2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Delete the most recent entry
        mockMvc.perform(delete("/logbook"))
                .andExpect(status().isOk());

        // Verify only one entry remains
        mockMvc.perform(get("/logbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].message").value("Entry 1"));
    }
}