package com.repositories;

import com.entities.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Repository
@Transactional
public interface FilmRepository extends CrudRepository<Film, UUID> {
	@Query("SELECT film FROM FILM film WHERE SYSDATE() < film.startDate")
	Set<Film> findUntilDate();
}
