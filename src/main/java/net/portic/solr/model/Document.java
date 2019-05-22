package net.portic.solr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SolrDocument(collection = "e_documents")
public class Document {

    @Id
    @Generated
    @Field
    private String id;
    @Field
    private String draftId;
    // file is "expedient" in catalan
    @Field
    private String fileId;
    @Field
    private String fileVersion;
    @Field
    private String documentId;
    @Field
    private String documentVersion;
    @Field
    private String ownerId;
    @Field
    private String username;
    @Field
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date created;
    @Field
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updated;

    // Functional fields
    @Field
    private String booking;
    @Field
    private String bl;
    @Field
    private String shipCall;
    @Field
    private String[] equipmentNumbers;
    @Field
    private String[] equipmentRefs;
    @Field
    private String[] goodCodes;
    @Field
    private String terminalName;
    @Field
    private String terminalId;
    @Field
    private String depotName;
    @Field
    private String depotId;
    @Field
    private String shippingAgentName;
    @Field
    private String shippingAgentId;
    @Field
    private String forwarderName;
    @Field
    private String forwarderId;
    @Field
    private String haulierName;
    @Field
    private String haulierId;

}
