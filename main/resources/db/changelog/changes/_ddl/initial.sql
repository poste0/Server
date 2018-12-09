CREATE SEQUENCE film_seq START 1;
CREATE SEQUENCE hall_seq START 1;
CREATE SEQUENCE visitor_seq START 1;
CREATE SEQUENCE session_seq START 1;
CREATE SEQUENCE order_seq START 1;
CREATE SEQUENCE event_seq START 1;

CREATE TABLE FILM (
  film_id     BIGINT  DEFAULT nextval('film_seq') NOT NULL,
  title       VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  genre       VARCHAR(10)  NOT NULL,
  end_date    TIMESTAMP    NOT NULL,
  rating      FLOAT,
  PRIMARY KEY (film_id)
);

CREATE TABLE HALL (
  hall_id  BIGINT DEFAULT nextval('hall_seq') NOT NULL,
  capacity NUMERIC NOT NULL,
  PRIMARY KEY (hall_id)
);

CREATE TABLE VISITOR (
  visitor_id    BIGINT DEFAULT nextval('visitor_seq') NOT NULL,
  name          VARCHAR(50),
  email         VARCHAR(50),
  age           NUMERIC,
  boughtTickets NUMERIC,
  PRIMARY KEY (visitor_id)
);

CREATE TABLE SESSION (
  session_id BIGINT DEFAULT nextval('session_seq') NOT NULL,
  film_id    BIGINT REFERENCES film (film_id),
  hall_id    BIGINT REFERENCES hall (hall_id),
  time       TIMESTAMP,
  cost       FLOAT,
  remaining_capacity NUMERIC NOT NULL,
  PRIMARY KEY (session_id)
);

CREATE TABLE booking (
  order_id       BIGINT DEFAULT nextval('order_seq') NOT NULL,
  visitor_id     BIGINT REFERENCES visitor (visitor_id),
  session_id     BIGINT REFERENCES session (session_id),
  tickets_amount NUMERIC NOT NULL,
  cost           FLOAT   NOT NULL,
  PRIMARY KEY (order_id)
);

CREATE TABLE event (
  id        BIGINT DEFAULT nextval('event_seq') NOT NULL,
  condition VARCHAR(255),
  end_date  TIMESTAMP,
  PRIMARY KEY (id)
)