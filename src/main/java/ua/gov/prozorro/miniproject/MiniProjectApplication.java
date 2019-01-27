package ua.gov.prozorro.miniproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.gov.prozorro.miniproject.dto.ContractDTO;
import ua.gov.prozorro.miniproject.dto.DocumentDTO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class MiniProjectApplication {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static OkHttpClient client = new OkHttpClient();


    public static void main(String[] args) {
        SpringApplication.run(MiniProjectApplication.class, args);

        Iterable<DocumentDTO> documents = Arrays.stream(args)
                .map(Optional::of)
                .map(MiniProjectApplication::getContractFromAPI)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        saveContractDocumentsInDB(documents);
    }

    private static List<DocumentDTO> getContractFromAPI(Optional<String> contractURL) {
        String url = contractURL
                .orElseThrow(IllegalArgumentException::new);
        Request request = new Request.Builder()
                .url(url)
                .build();
        String contactId = url.substring(url.indexOf("contracts/") + 10, url.indexOf("/documents"));

        try {
            String responseBody = client
                    .newCall(request)
                    .execute()
                    .body()
                    .string();

            ContractDTO contract = new ObjectMapper()
                    .readValue(responseBody, ContractDTO.class);
            return contract.getDocuments()
                    .stream()
                    .peek(e -> e.setContractId(contactId))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveContractDocumentsInDB(Iterable<DocumentDTO> documents) {
        try {
            String json = objectMapper.writeValueAsString(documents);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            Request request = new Request.Builder()
                    .url("http://localhost:8080/documents")
                    .post(body)
                    .build();
            client
                    .newCall(request)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
