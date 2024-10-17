CREATE TABLE nationality
(
    id_nationality NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT nationality_name_nn NOT NULL,
    CONSTRAINT nationality_name_unique UNIQUE (name),
    CONSTRAINT pk_nationality PRIMARY KEY (id_nationality)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_nationality_id CHECK (id_nationality > 0)
);

COMMENT ON TABLE nationality IS 'Tabla que almacena la información de las nacionalidades disponibles para el sistema';

CREATE TABLE photo
(
    id_photo NUMBER(8),
    pathD VARCHAR(25) CONSTRAINT photo_file_nn NOT NULL,
    id_person NUMBER(8), -- Columna para la clave foránea
    CONSTRAINT pk_photo PRIMARY KEY (id_photo)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_photo_id CHECK (id_photo > 0),
    CONSTRAINT fk_photo_person FOREIGN KEY (id_person)
    REFERENCES person(id_person)
);

COMMENT ON TABLE photo IS 'Tabla que almacena la información de las fotografias de las personas del sistema';

CREATE TABLE flag_photo
(
    id_flag_photo NUMBER(8) ,
    pathD VARCHAR(25)CONSTRAINT flag_photo_name_nn NOT NULL,
    CONSTRAINT pk_flag_photo PRIMARY KEY (id_flag_photo)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_flag_photo_id CHECK (id_flag_photo > 0)
);

COMMENT ON TABLE flag_photo IS 'Tabla que almacena la información de las fotografías de las banderas de los países del sistema';

CREATE TABLE phone
(
    id_phone NUMBER(8) ,
    number_phone NUMBER(25)CONSTRAINT phone_name_nn NOT NULL,
    CONSTRAINT pk_phone PRIMARY KEY (id_phone)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_phone_id CHECK (id_phone > 0),
    CONSTRAINT chk_phone_length CHECK (number_phone > 0)
);

COMMENT ON TABLE phone IS 'Tabla que almacena la información de los telefonos de las personas del sistema';

CREATE TABLE gender
(
    id_gender NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT gender_name_nn NOT NULL,
    CONSTRAINT gender_name_unique UNIQUE (name),
    CONSTRAINT pk_gender PRIMARY KEY (id_gender)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_gender_id CHECK (id_gender > 0)
);

COMMENT ON TABLE gender IS 'Tabla que almacena la información de los generos de las personas del sistema';

CREATE TABLE identification_type
(
    id_identification_type NUMBER(8) ,
    type_id VARCHAR(25)CONSTRAINT identification_type_name_nn NOT NULL,
    CONSTRAINT pk_identification_type PRIMARY KEY (id_identification_type)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_identification_type_id CHECK (id_identification_type > 0)
);

COMMENT ON TABLE identification_type IS 'Tabla que almacena la información de los tipos de ID de las personas del sistema';

CREATE TABLE category
(
    id_category NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT category_name_nn NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id_category)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_category_type_id CHECK (id_category > 0)
);

COMMENT ON TABLE category IS 'Tabla que almacena la información de las categorías del sistema';

CREATE TABLE country
(
    id_country NUMBER(8),
    name VARCHAR(50) CONSTRAINT country_name_nn NOT NULL,
    CONSTRAINT country_name_unique UNIQUE (name),
    id_flag_photo NUMBER(8),
    CONSTRAINT pk_country PRIMARY KEY (id_country)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_country_flag_photo FOREIGN KEY (id_flag_photo)
    REFERENCES flag_photo(id_flag_photo),
    CONSTRAINT chk_country_id CHECK (id_country > 0),
    CONSTRAINT chk_country_flag_photo_id CHECK (id_flag_photo > 0)
);

COMMENT ON TABLE country IS 'Tabla que almacena la información de los países del sistema';

CREATE TABLE province
(
    id_province NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT province_name_nn NOT NULL,
    id_country NUMBER(8),
    CONSTRAINT pk_province PRIMARY KEY (id_province)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_province_id_country FOREIGN KEY (id_country)
    REFERENCES country(id_country),
    CONSTRAINT chk_province_id CHECK (id_province > 0),
    CONSTRAINT chk_province_id_country CHECK (id_country > 0)
);

COMMENT ON TABLE province IS 'Tabla que almacena la información de las provincias del sistema';

CREATE TABLE region
(
    id_region NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT region_name_nn NOT NULL,
    id_province NUMBER(8),
    CONSTRAINT region_name_province_unique UNIQUE (name, id_province),
    CONSTRAINT pk_region PRIMARY KEY (id_region)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_region_id_country FOREIGN KEY (id_province)
    REFERENCES province(id_province),
    CONSTRAINT chk_region_id CHECK (id_region > 0),
    CONSTRAINT chk_region_id_country CHECK (id_province > 0)
);

COMMENT ON TABLE region IS 'Tabla que almacena la información de las regiones del sistema';

CREATE TABLE district
(
    id_district NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT district_name_nn NOT NULL,
    id_region NUMBER(8),
    CONSTRAINT pk_district PRIMARY KEY (id_district)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_district_id_region FOREIGN KEY (id_region)
    REFERENCES region(id_region),
    CONSTRAINT chk_district_id CHECK (id_district > 0),
    CONSTRAINT chk_district_id_region CHECK (id_region > 0)
);

COMMENT ON TABLE district IS 'Tabla que almacena la información de los distritos del sistema';

CREATE TABLE parameter
(
    id_param NUMBER(8) ,
    name VARCHAR(25)CONSTRAINT param_name_nn NOT NULL,
    value_param VARCHAR(25)CONSTRAINT param_value_nn NOT NULL,
    description_param VARCHAR(25)CONSTRAINT param_description_nn NOT NULL,
    CONSTRAINT pk_param PRIMARY KEY (id_param)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT chk_param_id CHECK (id_param > 0)
);

COMMENT ON TABLE parameter IS 'Tabla que almacena la información de los parametros usados en el sistema';

CREATE TABLE person 
(
    id_person NUMBER(8),
    first_name VARCHAR(25)CONSTRAINT person_first_nn NOT NULL,
    last_name VARCHAR(25) CONSTRAINT person_last_nn NOT NULL,
    birth_date DATE CONSTRAINT person_date_nn NOT NULL,
    identification_number NUMBER(8) CONSTRAINT person_id_u_nn UNIQUE NOT NULL,
    id_gender NUMBER(8),
    id_country_represents NUMBER(8),
    id_district NUMBER(8),
    CONSTRAINT pk_person PRIMARY KEY (id_person)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_person_id_gender FOREIGN KEY (id_gender)
    REFERENCES gender(id_gender),
    CONSTRAINT fk_person_country_repr FOREIGN KEY (id_country_represents)
    REFERENCES country(id_country),
    CONSTRAINT fk_person_id_district FOREIGN KEY (id_district)
    REFERENCES district(id_district),
    CONSTRAINT chk_person_id CHECK (id_person > 0),
    CONSTRAINT chk_person_birth CHECK (birth_date <= TO_DATE('01-JAN-2024', 'DD-MON-YYYY'))
);

COMMENT ON TABLE person IS 'Tabla que almacena la información de las personas en el sistema';

CREATE TABLE trainer 
(
    id_trainer NUMBER(8),
    CONSTRAINT pk_trainer PRIMARY KEY (id_trainer)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_trainer_id_person FOREIGN KEY (id_trainer)
    REFERENCES person(id_person),
    CONSTRAINT chk_trainer_id CHECK (id_trainer > 0)
);

COMMENT ON TABLE trainer IS 'Tabla que almacena la información de las personas que son entrenadores en el sistema';

CREATE TABLE role 
(
    id_role NUMBER(8),
    CONSTRAINT pk_role PRIMARY KEY (id_role)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    name VARCHAR(25) CONSTRAINT role_name_nn NOT NULL,
    CONSTRAINT chk_role_id CHECK (id_role > 0)
);

COMMENT ON TABLE role IS 'Tabla que almacena la información de los roles de usuario en el sistema';

CREATE TABLE user_person 
(
    id_user NUMBER (8),
    CONSTRAINT pk_user PRIMARY KEY (id_user)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_user_id_person FOREIGN KEY (id_user)
    REFERENCES person(id_person),
    username VARCHAR(25) CONSTRAINT user_username_nn UNIQUE NOT NULL,
    password VARCHAR(64) CONSTRAINT user_password_nn NOT NULL,
    id_role NUMBER(8),
    CONSTRAINT fk_user_id_role FOREIGN KEY (id_role)
    REFERENCES role(id_role),
    CONSTRAINT chk_user_id CHECK (id_user > 0)
);

COMMENT ON TABLE user_person IS 'Tabla que almacena la información de los perfiles de usuario en el sistema';

CREATE TABLE athlete
(
    id_athlete NUMBER (8),
    CONSTRAINT pk_athlete PRIMARY KEY (id_athlete)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_athlete_id FOREIGN KEY (id_athlete)
    REFERENCES person(id_person),
    id_trainer NUMBER(8),
    CONSTRAINT fk_athlete_id_trainer FOREIGN KEY (id_trainer)
    REFERENCES trainer(id_trainer),
    CONSTRAINT chk_athlete_not_trainer CHECK (id_athlete != id_trainer),
    CONSTRAINT chk_athlete_id CHECK (id_athlete > 0)
);

COMMENT ON TABLE athlete IS 'Tabla que almacena la información de las personas que son atletas en el sistema';

CREATE TABLE sport 
(
    id_sport NUMBER(8),
    CONSTRAINT pk_sport PRIMARY KEY (id_sport)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    name VARCHAR(50) CONSTRAINT sport_name_nn NOT NULL,
    CONSTRAINT sport_name_unique UNIQUE (name),
    description VARCHAR(100) CONSTRAINT sport_desc_nn NOT NULL,
    rules VARCHAR(100) CONSTRAINT sport_rules_nn NOT NULL
);

COMMENT ON TABLE sport IS 'Tabla que almacena la información de los deportes en el sistema';

CREATE TABLE olympic 
(
    id_olympic NUMBER(8),
    CONSTRAINT pk_olympic PRIMARY KEY (id_olympic)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    year NUMBER(4) CONSTRAINT year_nn NOT NULL,
    CONSTRAINT chk_event_year_pos CHECK (year > 0),
    CONSTRAINT chk_event_year_range CHECK (year BETWEEN 1900 AND 2024),
    CONSTRAINT event_year_unique UNIQUE (year),
    id_country NUMBER(8) CONSTRAINT year_country_nn NOT NULL,
    CONSTRAINT fk_olympic_country FOREIGN KEY (id_country)
    REFERENCES country(id_country)
);

COMMENT ON TABLE olympic IS 'Tabla que almacena la información de las olimpiadas en el sistema';

CREATE TABLE medal
(
    id_medal NUMBER(8),
    CONSTRAINT pk_medal PRIMARY KEY (id_medal)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    name VARCHAR(25) CONSTRAINT medal_name_nn NOT NULL,
    CONSTRAINT medal_name_unique UNIQUE (name)
);

COMMENT ON TABLE medal IS 'Tabla que almacena la información de las medallas en el sistema';

CREATE TABLE email
(
    id_email NUMBER(8),
    CONSTRAINT pk_email PRIMARY KEY (id_email)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    email_direction VARCHAR(30) CONSTRAINT email_dir_unique_nn UNIQUE NOT NULL,
    id_person NUMBER(8) CONSTRAINT emaiÑ_id_person_nn NOT NULL,
    CONSTRAINT fk_email_id_person FOREIGN KEY (id_person)
    REFERENCES person(id_person)
);

COMMENT ON TABLE email IS 'Tabla que almacena la información de las direcciones de correo en el sistema';

CREATE TABLE event
(
    id_event NUMBER(8),
    CONSTRAINT pk_event PRIMARY KEY (id_event)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    date_event DATE CONSTRAINT event_date_nn NOT NULL,
    time_event INTERVAL DAY TO SECOND CONSTRAINT event_time_nn NOT NULL,
    name VARCHAR(25) CONSTRAINT event_name_nn NOT NULL,
    id_olympic NUMBER(8) CONSTRAINT event_id_olympic_nn NOT NULL,
    CONSTRAINT fk_event_id_olympic FOREIGN KEY (id_olympic)
    REFERENCES olympic(id_olympic),
    id_sport NUMBER(8) CONSTRAINT event_id_sport_nn NOT NULL,
    CONSTRAINT fk_event_id_sport FOREIGN KEY (id_sport)
    REFERENCES sport(id_sport)
);

ALTER TABLE event

COMMENT ON TABLE event IS 'Tabla que almacena la información de los eventos en el sistema';

CREATE TABLE team
(
    id_team NUMBER(8),
    CONSTRAINT pk_team PRIMARY KEY (id_team)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    name VARCHAR(25) CONSTRAINT team_name_nn NOT NULL,
    id_trainer NUMBER(8) CONSTRAINT team_id_trainer_nn NOT NULL,
    CONSTRAINT fk_team_id_trainer FOREIGN KEY (id_trainer)
    REFERENCES trainer(id_trainer)
);

COMMENT ON TABLE team IS 'Tabla que almacena la información de los equipos en el sistema';

CREATE TABLE log_entries
(
    id_log NUMBER(8),
    CONSTRAINT pk_log PRIMARY KEY (id_log)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    date_log DATE CONSTRAINT log_date_nn NOT NULL,
    time_log INTERVAL DAY TO SECOND CONSTRAINT log_time_nn NOT NULL,
    name_log VARCHAR(25) CONSTRAINT log_name_nn NOT NULL,
    change_type VARCHAR(25) CONSTRAINT log_change_type_nn NOT NULL,
    username VARCHAR(25) CONSTRAINT log_username_nn NOT NULL,
    CONSTRAINT fk_log_username FOREIGN KEY (username)
    REFERENCES user_person(username),
    object_log VARCHAR(25) CONSTRAINT log_object_nn NOT NULL,
    decription_log VARCHAR(100) CONSTRAINT log_desc_nn NOT NULL,
    id_user NUMBER(8) CONSTRAINT log_id_user_nn NOT NULL,
    CONSTRAINT fk_log_id_user FOREIGN KEY (id_user)
    REFERENCES user_person(id_user)
);

COMMENT ON TABLE log_entries IS 'Tabla que almacena la información de las logs entries en el sistema';

CREATE TABLE person_phone
(
    id_person NUMBER(8),
    id_phone NUMBER(8),
    CONSTRAINT pk_person_phone PRIMARY KEY (id_person, id_phone)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_person_phone_id_person FOREIGN KEY (id_person) REFERENCES person(id_person), 
    CONSTRAINT fk_person_phone_id_phone FOREIGN KEY (id_phone) REFERENCES phone(id_phone)
);

COMMENT ON TABLE person_phone IS 'Tabla que almacena la información de las fotos de las personas en el sistema';

CREATE TABLE person_identification_type
(
    id_person NUMBER(8),
    id_identification_type NUMBER(8),
    CONSTRAINT pk_person_id_type PRIMARY KEY (id_person, id_identification_type)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_person_id_person FOREIGN KEY (id_person) REFERENCES person(id_person), 
    CONSTRAINT fk_person_id_type FOREIGN KEY (id_identification_type) REFERENCES identification_type(id_identification_type)
);

COMMENT ON TABLE person_identification_type IS 'Tabla que almacena la información de las personas y su tipo de ID en el sistema - RELACION';

CREATE TABLE event_category
(
    id_event NUMBER(8),
    id_category NUMBER(8),
    CONSTRAINT pk_event_category PRIMARY KEY (id_event, id_category)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_event_category_id_event FOREIGN KEY (id_event) REFERENCES event(id_event), 
    CONSTRAINT fk_event_category_id_category FOREIGN KEY (id_category) REFERENCES category(id_category)
);

COMMENT ON TABLE event_category IS 'Tabla que almacena la información de los eventos y sus categorías en el sistema - RELACION';

CREATE TABLE event_medal
(
    id_event NUMBER(8),
    id_medal NUMBER(8),
    CONSTRAINT pk_event_medal PRIMARY KEY (id_event, id_medal)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_event_medal_id_event FOREIGN KEY (id_event) REFERENCES event(id_event), 
    CONSTRAINT fk_event_medal_id_medal FOREIGN KEY (id_medal) REFERENCES medal(id_medal)
);

COMMENT ON TABLE event_medal IS 'Tabla que almacena la información de los eventos y sus medallas en el sistema - RELACION';

CREATE TABLE team_event
(
    id_team NUMBER(8),
    id_event NUMBER(8),
    CONSTRAINT pk_team_event PRIMARY KEY (id_team, id_event)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_team_event_id_team FOREIGN KEY (id_team) REFERENCES team(id_team), 
    CONSTRAINT fk_team_event_id_event FOREIGN KEY (id_event) REFERENCES event(id_event),
    save_score VARCHAR(25) CONSTRAINT team_event_save_score_nn NOT NULL,
    record VARCHAR(25) CONSTRAINT team_event_record_nn NOT NULL,
    position NUMBER(2) CONSTRAINT team_event_pos_nn NOT NULL
);

COMMENT ON TABLE team_event IS 'Tabla que almacena la información de los eventos y sus equipos en el sistema - RELACION';

CREATE TABLE team_medal
(
    id_team NUMBER(8),
    id_medal NUMBER(8),
    CONSTRAINT pk_team_medal PRIMARY KEY (id_team, id_medal),
    CONSTRAINT fk_team_medal_id_team FOREIGN KEY (id_team) REFERENCES team(id_team), 
    CONSTRAINT fk_team_medal_id_medal FOREIGN KEY (id_medal) REFERENCES medal(id_medal)
);

COMMENT ON TABLE team_medal IS 'Tabla que almacena la información de los equipos y sus medallas en el sistema - RELACION';

CREATE TABLE athlete_team
(
    id_athlete NUMBER(8),
    id_team NUMBER(8),
    CONSTRAINT pk_athlete_team PRIMARY KEY (id_athlete, id_team)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_athlete_team_id_athlete FOREIGN KEY (id_athlete) REFERENCES athlete(id_athlete), 
    CONSTRAINT fk_athlete_team_id_team FOREIGN KEY (id_team) REFERENCES team(id_team)
);

COMMENT ON TABLE athlete_team IS 'Tabla que almacena la información de lxs atletas y sus equipos en el sistema - RELACION';

CREATE TABLE person_nationality
(
    id_person NUMBER(8),
    id_nationality NUMBER(8),
    CONSTRAINT pk_person_nationality PRIMARY KEY (id_person, id_nationality)
    USING INDEX
    TABLESPACE AP_Ind PCTFREE 20
    STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0),
    CONSTRAINT fk_person_nat_id_person FOREIGN KEY (id_person) REFERENCES person(id_person), 
    CONSTRAINT fk_person_nat_id_nat FOREIGN KEY (id_nationality) REFERENCES nationality(id_nationality)
);

COMMENT ON TABLE person_nationality IS 'Tabla que almacena la información de las nacionalidades de las personas en el sistema';
