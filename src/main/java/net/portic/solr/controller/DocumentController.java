package net.portic.solr.controller;

import java.util.ArrayList;
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
        documents.add(new Document()
                .builder()
                .id(123L)
                .booking("BK-0001-8981771")
                .equipmentRefs(new String[]{"REF1"})
                .build());

        documents.add(new Document()
                .builder()
                .id(223L)
                .booking("BK-0001-89817233")
                .equipmentRefs(new String[]{"REF1"})
                .build());
        documents.add(new Document()
                .builder()
                .id(332L)
                .booking("BK-0001-89817222")
                .equipmentRefs(new String[]{"REF1", "REF3"})
                .build());
        documents.add(new Document()
                .builder()
                .id(345L)
                .booking("BK-0001-89817322")
                .equipmentRefs(new String[]{"REF1", "REF12"})
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
        return repository.findAll(searchDTO.getSearchTerm());
    }

    @PutMapping("/document")
    public Document updateDocument(@RequestBody Document document) {
        return repository.save(document);
    }
}