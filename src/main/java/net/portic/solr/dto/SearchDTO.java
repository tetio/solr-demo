package net.portic.solr.dto;

import lombok.*;

import java.util.Date;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
public class SearchDTO {
    String ownerId;
    String searchTerm;
    Date fromDate;
    Date toDate;
}
