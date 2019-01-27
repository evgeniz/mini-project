package ua.gov.prozorro.miniproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContractDTO {

    @JsonProperty("data")
    private List<DocumentDTO> documents;
}
