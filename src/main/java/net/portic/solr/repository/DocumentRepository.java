package net.portic.solr.repository;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import net.portic.solr.model.Document;

import java.util.Date;

public interface DocumentRepository extends SolrCrudRepository<Document, Integer> {

    Iterable<Document> findAllByShipCall(String value);
    Iterable<Document> findAllByBooking(String value);

//    @Query("booking:?0 OR shipCall:?0 OR equipmentNumbers:?0 OR equipmentRefs:?0")
//    Iterable<Document> findAll(String value);
    @Query("booking:?0 OR shipCall:?0 OR equipmentNumbers:?0 OR equipmentRefs:?0 OR username:?0")
    Iterable<Document> findAll(String value);

    @Query("ownerId:?1 AND (booking:?0 OR shipCall:?0 OR equipmentNumbers:?0 OR equipmentRefs:?0 OR username:?0)")
    Iterable<Document> findAll(String value, String owner);

    @Query("updated:[?0 TO ?1]")
    Iterable<Document> findAllDate(Date fromDate, Date toDate);

    @Query("ownerId:?0 AND updated:[?1 TO ?2] ")
    Iterable<Document> findAllDate(String ownerId, Date fromDate, Date toDate);
}