--SELECT cron.schedule('0 0 * * * ', $$delete from session; select session_generate()$$)
--
insert into hall VALUES(nextval('hall_seq'), 100);
insert into hall VALUES(nextval('hall_seq'), 100);
insert into hall VALUES(nextval('hall_seq'), 200);
insert into hall VALUES(nextval('hall_seq'), 200);
insert into hall VALUES(nextval('hall_seq'), 300);
insert into hall VALUES(nextval('hall_seq'), 300);

insert into film VALUES(nextval('film_seq'), 'title', 'desc', 'horror', current_date + INTERVAL '2 week',null);

--select hall.hall_id, film.film_id from hall, film limit 12;

select session_generate();
select * from databasechangelog;
update databasechangelog set md5sum='' where filename='db/changelog/changes/jobs/session_job_generate.sql'