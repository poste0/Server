package com.repositories;

import com.entities.users.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Repository
@Transactional
public interface AdminRepository extends CrudRepository<Admin , UUID>
{
}