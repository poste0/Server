package com.repositories;

import com.entities.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VisitorsRepository extends CrudRepository<Visitor, Long> {
}
