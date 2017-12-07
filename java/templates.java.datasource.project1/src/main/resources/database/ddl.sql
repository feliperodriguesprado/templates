DROP SCHEMA templates CASCADE;
CREATE SCHEMA templates;

CREATE SEQUENCE templates.people_id_seq;

CREATE TABLE templates.people (
	id integer NOT NULL DEFAULT nextval ('templates.people_id_seq'),
	first_name varchar(40) NOT NULL,
	last_name varchar(40) NOT NULL,
	birth_date date NOT NULL,

	CONSTRAINT pk_people PRIMARY KEY (id)
);

insert into templates.people (first_name, last_name, birth_date) values ('Paulo', 'Silva', '1991-08-17');
insert into templates.people (first_name, last_name, birth_date) values ('José', 'Filho', '1990-05-23');
insert into templates.people (first_name, last_name, birth_date) values ('Emanuel', 'Costa', '1990-10-05');

select * from templates.people;

select * from pg_stat_activity where datname = 'postgres' order by query_start;
select pid, client_addr, query_start, state, query from pg_stat_activity where datname = 'postgres' order by query_start;