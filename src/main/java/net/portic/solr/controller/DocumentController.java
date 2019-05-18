package net.portic.solr.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.github.javafaker.Faker;
import net.portic.solr.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.convert.DateTimeConverters;
import org.springframework.web.bind.annotation.*;

import net.portic.solr.model.Document;
import net.portic.solr.repository.DocumentRepository;


@RestController
public class DocumentController {
    @Autowired
    private DocumentRepository repository;

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
            return repository.findAllDate(searchDTO.getFromDate(), searchDTO.getToDate());
        }
        return repository.findAllDate(searchDTO.getOwnerId(), searchDTO.getFromDate(), searchDTO.getToDate());
    }

    @PostMapping("/document/findcriteria")
    public Iterable<Document> findDocumentCriteria(@RequestBody SearchDTO searchDTO) {
        if (isSuperUser(searchDTO.getOwnerId())) {
            return repository.findAll(searchDTO.getSearchTerm());
        }
        return repository.findAll(searchDTO.getSearchTerm(), searchDTO.getOwnerId());
    }

    private boolean isSuperUser(String ownerId) {
        return ("A100000001".equalsIgnoreCase(ownerId));
    }

    @PutMapping("/document")
    public Document updateDocument(@RequestBody Document document) {
        return repository.save(document);
    }


    @PostConstruct
    public void addDocuments() {
        List<Document> documents = new ArrayList<>();
        LocalDateTime ldtnow = LocalDateTime.now();
        Date now = Date.from(ldtnow.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime ldtaMonthAgo = ldtnow.minusMonths(1);
        Date aMonthAgo = Date.from(ldtaMonthAgo.atZone(ZoneId.systemDefault()).toInstant());
        documents.add(Document.builder()
                .id("123")
                .booking("BK-0001-8981771")
                .equipmentRefs(new String[]{"REF1"})
                .equipmentNumbers(new String[]{"MACU0099881"})
                .ownerId("QA331122111")
                .username("jsmith")
                .created(aMonthAgo)
                .updated(aMonthAgo)
                .build());

        documents.add(Document.builder()
                .id("223")
                .booking("BK-0001-89817233")
                .equipmentRefs(new String[]{"REF1"})
                .ownerId("QA331122111")
                .username("jsmith")
                .created(aMonthAgo)
                .updated(aMonthAgo)
                .build());
        documents.add(Document.builder()
                .id("332")
                .booking("BK-0001-89817222")
                .equipmentRefs(new String[]{"REF1", "REF3"})
                .ownerId("QA331122111")
                .username("jdow")
                .created(aMonthAgo)
                .updated(aMonthAgo)
                .build());
        documents.add(Document.builder()
                .id("545")
                .shipCall("55555")
                .booking("BK-0001-89817322")
                .equipmentRefs(new String[]{"REF1", "REF12"})
                .ownerId("QZ7778888")
                .username("hpotter")
                .created(aMonthAgo)
                .updated(aMonthAgo)
                .build());
        documents.add(Document.builder()
                .id("346")
                .shipCall("33445")
                .booking("55555")
                .equipmentRefs(new String[]{"REF1", "REF12"})
                .ownerId("QZ7778888")
                .username("hpotter")
                .created(aMonthAgo)
                .updated(aMonthAgo)
                .build());
        documents.add(Document.builder()
                .id("223")
                .shipCall("99999")
                .booking("55555")
                .equipmentRefs(new String[]{"REF1", "REF12"})
                .ownerId("QZ7778888")
                .username("hpotter")
                .created(now)
                .updated(now)
                .build());
        repository.saveAll(documents);

        Faker faker = new Faker();
        Document document;
        String ownerId = "QZ7778888";
        Date when;
        for (int i = 0; i < 10000; i++) {
            if (i % 5 == 0) {
                ownerId = "QA331122111";
            } else {
                ownerId = "QZ7778888";
            }
            when = faker.date().past(faker.number().numberBetween(1, 1000), TimeUnit.DAYS);
            document = Document.builder()
                    .id(faker.idNumber().valid())
                    .shipCall("" + faker.number().numberBetween(10000, 100000))
                    .booking(faker.idNumber().valid())
                    .equipmentNumbers(new String[]{faker.random().hex(3) + "U" + faker.number().digits((7)), faker.random().hex(3) + "U" + faker.number().digits((7))})
                    .equipmentRefs(new String[]{"REF" + faker.number().digits(4), "REF" + faker.number().digits(4)})
                    .ownerId(ownerId)
                    .username(faker.pokemon().name())
                    .created(when)
                    .updated(when)
                    .build();
            repository.save(document);
        }
    }
}