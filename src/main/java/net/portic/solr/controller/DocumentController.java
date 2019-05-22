package net.portic.solr.controller;

import com.github.javafaker.Faker;
import net.portic.solr.dto.SearchDTO;
import net.portic.solr.model.Document;
import net.portic.solr.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
public class DocumentController {
    @Autowired
    private DocumentRepository repository;

    private Pageable pageRequest = PageRequest.of(0,20);

    @GetMapping("/document")
    public Iterable<Document> getAll() {
        return repository.findAll();
    }

    @GetMapping("/document/{id}")
    public Document getDocument(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/document")
    public Document createDocument(@RequestBody Document document) {
        return repository.save(document);
    }

    @PostMapping("/document/finddate")
    public Iterable<Document> findDocumenDatet(@RequestBody SearchDTO searchDTO) {
        if (isSuperUser(searchDTO.getOwnerId())) {
            return repository.findAllDate(searchDTO.getFromDate(), searchDTO.getToDate(), pageRequest).getContent();
        }
        return repository.findAllDate(searchDTO.getOwnerId(), searchDTO.getFromDate(), searchDTO.getToDate(), pageRequest).getContent();
    }

    @PostMapping("/document/findcriteria")
    public Iterable<Document> findDocumentCriteria(@RequestBody SearchDTO searchDTO) {
        if (isSuperUser(searchDTO.getOwnerId())) {
            return repository.findAll(searchDTO.getSearchTerm(), pageRequest).getContent();
        }
        return repository.findAll(searchDTO.getSearchTerm(), searchDTO.getOwnerId(), pageRequest).getContent();
    }

    private boolean isSuperUser(String ownerId) {
        return ("A100000001".equalsIgnoreCase(ownerId));
    }

    @PutMapping("/document")
    public Document updateDocument(@RequestBody Document document) {
        return repository.save(document);
    }


    //@PostConstruct
    public void addDocuments() {
        Faker faker = new Faker();
        Document document;
        String ownerId;
        Date when;
        Date today;
        for (int i = 0; i < 10000; i++) {
            if (i % 5 == 0) {
                ownerId = "QA331122111";
            } else {
                ownerId = "QZ7778888";
            }
            when = faker.date().past(faker.number().numberBetween(1, 1000), TimeUnit.DAYS);
            today = faker.date().past(faker.number().numberBetween(1, 120), TimeUnit.MINUTES);
            document = Document.builder()
                    .shipCall("" + faker.number().numberBetween(10000, 100000))
                    .booking(faker.idNumber().valid())
                    .equipmentNumbers(new String[]{faker.random().hex(3) + "U" + faker.number().digits((7)), faker.random().hex(3) + "U" + faker.number().digits((7))})
                    .equipmentRefs(new String[]{"REF" + faker.number().digits(4), "REF" + faker.number().digits(4)})
                    .terminalId("CAT"+faker.number().numberBetween(10000000, 99999999))
                    .terminalName(faker.company().name())
                    .depotId("CAD"+faker.number().numberBetween(10000000, 99999999))
                    .depotName(faker.company().name())
                    .shippingAgentId("CAS"+faker.number().numberBetween(10000000, 99999999))
                    .shippingAgentName(faker.company().name())
                    .forwarderId("CAF"+faker.number().numberBetween(10000000, 99999999))
                    .forwarderName(faker.company().name())
                    .haulierId("CAH"+faker.number().numberBetween(10000000, 99999999))
                    .haulierName(faker.company().name())
                    .ownerId(ownerId)
                    .username(faker.pokemon().name())
                    .created(when)
                    .updated(today)
                    .build();
            repository.save(document);
        }
    }
}