package ua.gov.prozorro.miniproject.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "documents")
public class DocumentDTO {

    @Id
    @NotNull
    private final String id;

    @NotNull
    private final String hash;

    private final String description;

    private final String language;

    @NotNull
    private final String format;

    @NotNull
    private final String url;

    @NotNull
    private final String title;

    @Column(name = "document_of")
    @NotNull
    private final String documentOf;

    @Column(name = "date_published")
    @NotNull
    private final String datePublished;

    @Column(name = "type")
    private final String documentType;

    @Column(name = "date_modified")
    @NotNull
    private final String dateModified;

    @Column(name = "related_item")
    private final String relatedItem;

    @JsonDeserialize
    @Column(name = "contract_id")
    private String contractId;

}
