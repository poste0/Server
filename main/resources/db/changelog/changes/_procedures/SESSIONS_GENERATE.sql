create or replace function session_generate()
returns integer
as $$
DECLARE
  time_rec RECORD;
  film_id_rec RECORD;
  hall_id_rec RECORD;
  session_cost FLOAT := 100;
  scale FLOAT := 1;
BEGIN
  FOR time_rec IN select time from generate_series(current_date, current_date + INTERVAL '22 hours', '2 hours') time
  LOOP
    scale := scale + 0.2;
    FOR film_id_rec IN select film.film_id from film
    LOOP
        FOR hall_id_rec IN select hall.hall_id, hall.capacity from hall
        LOOP
        INSERT INTO
          SESSION VALUES(
            nextval('session_seq'),
            film_id_rec.film_id,
            hall_id_rec.hall_id,
            time_rec.time,
            session_cost * scale,
            hall_id_rec.capacity
        );
        END LOOP;
    END LOOP;
  END LOOP;
  RETURN 1;
END;
$$ LANGUAGE plpgsql;