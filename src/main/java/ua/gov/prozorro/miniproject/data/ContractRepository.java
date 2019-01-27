package ua.gov.prozorro.miniproject.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.gov.prozorro.miniproject.dto.DocumentDTO;

@CrossOrigin(origins="*")
public interface ContractRepository extends CrudRepository<DocumentDTO, String> {
}
