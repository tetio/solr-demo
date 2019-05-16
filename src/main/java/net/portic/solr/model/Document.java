package net.portic.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "e_documents")
public class Document {

	@Id
	@Field
	private String id;
	@Field
	private String expedientId;
	@Field
	private String expedientVersion;
	@Field
//	@Indexed(name = "booking", type = "string")
	private String booking;
	@Field
	private String bl;
	@Field
//	@Indexed(name = "shipCall", type = "string")
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
	@Field
	private String ownerId;
	@Field
	private String username;
	@Field
	private String docNumber;
	@Field
	private String docVersion;
	@Field
	private Date created;
	@Field
	private Date updated;

}
