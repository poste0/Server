CREATE TABLE FILM (
  film_id     uuid NOT NULL,
  title       VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  rating      FLOAT,
  PRIMARY KEY (film_id)
);

CREATE TABLE HALL (
  hall_id  uuid NOT NULL,
  capacity NUMERIC      NOT NULL,
  PRIMARY KEY (hall_id)
);

CREATE TABLE VISITOR (
  visitor_id    uuid NOT NULL,
  name          VARCHAR(50),
  email         VARCHAR(50),
  age           NUMERIC,
  boughtTickets NUMERIC,
  PRIMARY KEY (visitor_id)
);

CREATE TABLE SESSION (
  session_id uuid NOT NULL,
  film_id    uuid REFERENCES film (film_id),
  hall_id    uuid REFERENCES hall (hall_id),
  time       TIMESTAMP,
  PRIMARY KEY (session_id)
);

CREATE TABLE BOOKING (
  order_id       uuid NOT NULL,
  visitor_id     uuid REFERENCES visitor (visitor_id),
  session_id     uuid REFERENCES session (session_id),
  tickets_amount NUMERIC      NOT NULL,
  cost           FLOAT        NOT NULL,
  PRIMARY KEY (order_id)
);

CREATE TABLE PURCHASING (
  order_id       uuid NOT NULL,
  visitor_id     uuid REFERENCES visitor (visitor_id),
  session_id     uuid REFERENCES session (session_id),
  tickets_amount NUMERIC      NOT NULL,
  cost           FLOAT        NOT NULL,
  PRIMARY KEY (order_id)
);