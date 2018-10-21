package com.notification;

import com.entities.Film;
import com.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

public class PremiersNotificationByEmail extends AbstractNotificationByEmail
{
	public PremiersNotificationByEmail()
	{
	}

	private Date rangeDate;

	private FilmRepository filmRepository;

	@Autowired
	public void setFilmRepository(FilmRepository filmRepository){this.filmRepository = filmRepository;}

	public PremiersNotificationByEmail(String email , Date date){
		this.email = email;
		this.rangeDate = date;
	}

	@Override
	public void notifyUser() {
		Set<Film> films = findPremiers();
		StringBuilder builder = new StringBuilder();
		message = "Hello. These are new films you can watch.";
		subject = "Premiers";
		builder.append(message + '\n');
		for(Film film : films){
			builder.append(film.getTitle()).append('\n').append(film.getDescription()).append('\n');
		}
		emailService.sendSimpleMessage(email , subject , message);
	}

	private Set<Film> findPremiers(){
		Set<Film> films = filmRepository.findPremiers();
		return films;
	}
}