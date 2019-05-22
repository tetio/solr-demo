package net.portic.solr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import net.portic.solr.model.Document;

import java.util.Date;

public interface DocumentRepository extends SolrCrudRepository<Document, Integer> {
    static final String COMMON_QUERY = "booking:*?0* OR shipCall:*?0* OR equipmentNumbers:*?0* OR equipmentRefs:*?0* OR username:?0 " +
            "OR terminalName:*?0* OR terminalId:*?0* " +
            "OR depotName:*?0* OR depotId:*?0* " +
            "OR shippingAgentName:*?0* OR shippingAgentId:*?0* " +
            "OR forwarderName:*?0* OR forwarderId:*?0* " +
            "OR haulierName:*?0* OR haulierId:*?0* ";
    @Query(COMMON_QUERY)
    Page<Document> findAll(String value, Pageable page);

    @Query("ownerId:?1 AND ("+COMMON_QUERY+")")
    Page<Document> findAll(String value, String owner, Pageable page);

    @Query("updated:[?0 TO ?1]")
    Page<Document> findAllDate(Date fromDate, Date toDate, Pageable page);

    @Query("ownerId:?0 AND updated:[?1 TO ?2] ")
    Page<Document> findAllDate(String ownerId, Date fromDate, Date toDate, Pageable page);
}