package ua.gov.prozorro.miniproject;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.gov.prozorro.miniproject.controllers.DocumentController;
import ua.gov.prozorro.miniproject.data.ContractRepository;
import ua.gov.prozorro.miniproject.dto.DocumentDTO;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DocumentControllerWithDocumentRepositoryTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ContractRepository contractRepo;

    @InjectMocks
    DocumentController documentController;

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    @Test
    public void testDocumentControllerStatusOk() throws Exception {
        Iterable<DocumentDTO> testDocuments = getTestDocuments();
        when(contractRepo.saveAll(testDocuments))
                .thenReturn(testDocuments);
        String json = objectMapper.writeValueAsString(testDocuments);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/documents")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    private Iterable<DocumentDTO> getTestDocuments() {
        return Stream.of(
                new DocumentDTO("1", "2", "3", "uk", "4", "5", "67", "6", "7", "8", "9", null),
                new DocumentDTO("11", "21", "31", "89", "56", "61", "35", "71", null, "91", "101", "46"),
                new DocumentDTO("13", "21", "31", null, "51", "60", "35", "71", "37", "91", "101", "23")
        )
                .peek(e -> e.setContractId("defaultContractId"))
                .collect(Collectors.toList());
    }
}
