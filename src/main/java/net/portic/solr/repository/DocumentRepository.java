package net.portic.solr.repository;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import net.portic.solr.model.Document;

public interface DocumentRepository extends SolrCrudRepository<Document, Integer> {

    Iterable<Document> findAllByShipCall(String value);
    Iterable<Document> findAllByBooking(String value);

    @Query("booking:?0 OR shipCall:?0 OR equipmentNumbers:?0 OR equipmentRefs:?0")
    Iterable<Document> findAll(String value);

    @Query("ownerId:?1 AND (booking:?0 OR shipCall:?0 OR equipmentNumbers:?0 OR equipmentRefs:?0)")
    Iterable<Document> findAll(String value, String owner);}