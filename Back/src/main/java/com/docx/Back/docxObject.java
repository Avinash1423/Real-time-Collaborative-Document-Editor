package com.docx.Back;
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

    @Column(name="delta",columnDefinition="jsonb")
    String delta;


}
