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



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "documents")
public class Document {

	@Id
	@Field
	@Indexed(name = "docid", type = "long")
	private Long id;
	@Field
	private Long expedientId;
	@Field
	private Integer expedientVersion;
	@Field
	private String booking;
	@Field
	private String BL;
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
