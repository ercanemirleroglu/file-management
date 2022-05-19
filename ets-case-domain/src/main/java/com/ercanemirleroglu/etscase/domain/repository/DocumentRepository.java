package com.ercanemirleroglu.etscase.domain.repository;

import com.ercanemirleroglu.etscase.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
