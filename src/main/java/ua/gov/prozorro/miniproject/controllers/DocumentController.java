package ua.gov.prozorro.miniproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.gov.prozorro.miniproject.data.ContractRepository;
import ua.gov.prozorro.miniproject.dto.DocumentDTO;

@RestController
@RequestMapping(path="/documents", produces="application/json")
@CrossOrigin(origins="*")
public class DocumentController {

    @Autowired
    ContractRepository contractRepo;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<DocumentDTO> postDocuments(@RequestBody Iterable<DocumentDTO> documents) {
        return contractRepo.saveAll(documents);
    }
}
