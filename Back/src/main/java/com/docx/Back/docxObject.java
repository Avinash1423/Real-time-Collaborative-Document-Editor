package com.docx.Back;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="docx_object")
public class docxObject {

    @Id
    @Column(name="docx_id")
    String docxId;

    @Type(JsonType.class)
    @Column(name="delta",columnDefinition="jsonb")
    JsonNode delta;


}
