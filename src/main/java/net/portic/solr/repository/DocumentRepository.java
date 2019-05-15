package net.portic.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;
import net.portic.solr.model.Document;

public interface DocumentRepository extends SolrCrudRepository<Document, Integer> {
    
}