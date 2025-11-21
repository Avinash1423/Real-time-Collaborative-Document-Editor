package com.docx.Back;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocxObjectRepository extends  JpaRepository<docxObject,String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO docx_object VALUES(:docxId, CAST(:delta AS  jsonb)) ON CONFLICT(docx_id) DO UPDATE SET delta= CAST(:delta AS jsonb)",nativeQuery = true )
    void UpdateDelta(@Param("delta")String delta,@Param("docxId")String docxId);


    @Query("SELECT d.delta FROM docxObject d WHERE d.docxId= :docxId")
    JsonNode findDeltaById(@Param("docxId") String docxId );

}


// use native query as JPQL cant cast to jsonb