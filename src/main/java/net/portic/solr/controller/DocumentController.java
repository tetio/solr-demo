package net.portic.solr.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import net.portic.solr.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.portic.solr.model.Document;
import net.portic.solr.repository.DocumentRepository;


@RestController
public class DocumentController {
    @Autowired
    private DocumentRepository repository;

    @PostConstruct
    public void addDocuments() {
        List<Document> documents = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.add(Calendar.MONTH, -1);
        Date aMonthAgo = cal.getTime();
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

    }

    @GetMapping("/document")
    public Iterable<Document> getAll() {
        return repository.findAll();
    }

    @GetMapping("/document/{id}")
    public Document getDocument(@PathVariable Integer id) {
        Document doc = repository.findById(id).orElse(null);
        return doc;
    }

    @PostMapping("/document")
    public Document createDocument(@RequestBody Document document) {
        return repository.save(document);
    }

    @PostMapping("/document/find")
    public Iterable<Document> findDocument(@RequestBody SearchDTO searchDTO) {
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
}