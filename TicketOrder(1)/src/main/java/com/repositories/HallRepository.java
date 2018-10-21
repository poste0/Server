package com.repositories;

import com.entities.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface HallRepository extends CrudRepository<Hall, UUID>
{
}