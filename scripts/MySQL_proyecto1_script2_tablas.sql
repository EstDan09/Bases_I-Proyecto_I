USE proyecto_bases1;

CREATE TABLE nationality (
    id_nationality INT NOT NULL auto_increment,
    name VARCHAR(25) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT nationality_name_unique UNIQUE (name),
    CONSTRAINT pk_nationality PRIMARY KEY (id_nationality)
) COMMENT='Tabla que almacena la información de las nacionalidades disponibles para el sistema';

CREATE TABLE photo ( #pending because it needs person
id_photo INT NOT NULL auto_increment,
path_photo VARCHAR(25) NOT NULL,
id_person INT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
created_by VARCHAR(100),
updated_by VARCHAR(100),
CONSTRAINT photo_path_unique UNIQUE (path_photo),
CONSTRAINT pk_photo PRIMARY KEY (id_photo)
) COMMENT= 'Tabla que almacena la información de las photos disponibles para el sistema';

CREATE TABLE flag_photo (
    id_flag_photo INT NOT NULL auto_increment,
    path_flag_photo VARCHAR(25) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	created_by VARCHAR(100),
	updated_by VARCHAR(100),
    CONSTRAINT flag_path_unique UNIQUE (path_flag_photo),
    CONSTRAINT pk_flag_photo PRIMARY KEY (id_flag_photo)
) COMMENT='Tabla que almacena la información de las fotografías de las banderas de los países del sistema';

CREATE TABLE phone (
    id_phone INT NOT NULL auto_increment,
    number_phone INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	created_by VARCHAR(100),
	updated_by VARCHAR(100),
    CONSTRAINT phone_unique UNIQUE (number_phone),
    CONSTRAINT pk_phone PRIMARY KEY (id_phone)
) COMMENT='Tabla que almacena la información de los telefonos de las personas del sistema';

CREATE TABLE gender (
    id_gender INT NOT NULL auto_increment,
    name VARCHAR(25) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	created_by VARCHAR(100),
	updated_by VARCHAR(100),
    CONSTRAINT gender_unique UNIQUE (name),
    CONSTRAINT pk_gender PRIMARY KEY (id_gender)
) COMMENT='Tabla que almacena la información de los generos de las personas del sistema';

CREATE TABLE identification_type (
    id_identification_type INT NOT NULL auto_increment,
    name_identification_type VARCHAR(25) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	created_by VARCHAR(100),
	updated_by VARCHAR(100),
    CONSTRAINT idt_unique UNIQUE (name_identification_type),
    CONSTRAINT pk_identification_type PRIMARY KEY (id_identification_type)
) COMMENT='Tabla que almacena la información de los id types del sistema';

CREATE TABLE category (
    id_category INT NOT NULL auto_increment,
    title VARCHAR(25) NOT NULL,
    gender VARCHAR(25) NOT NULL,
    quantity VARCHAR(25) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_category PRIMARY KEY (id_category)
) COMMENT='Table that stores information about categories in the system';

CREATE TABLE country (
    id_country INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    id_flag_photo INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_country PRIMARY KEY (id_country),
    UNIQUE KEY country_name_unique (name),
    CONSTRAINT fk_country_flag_photo FOREIGN KEY (id_flag_photo)
        REFERENCES flag_photo(id_flag_photo),
    CHECK (id_flag_photo > 0)
) COMMENT='Table that stores information about countries in the system';

CREATE TABLE province (
    id_province INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    id_country INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_province PRIMARY KEY (id_province),
    UNIQUE KEY country_name_unique (name),
    CONSTRAINT fk_province_country FOREIGN KEY (id_country)
        REFERENCES country(id_country) ON DELETE CASCADE,
    CHECK (id_country > 0)
) COMMENT='Table that stores information about provinces in the system';

CREATE TABLE region (
    id_region INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    id_province INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_region PRIMARY KEY (id_region),
    UNIQUE KEY region_name_unique (name, id_province),
    CONSTRAINT fk_region_province FOREIGN KEY (id_province)
        REFERENCES province(id_province)
        ON DELETE CASCADE,
    CHECK (id_province > 0)
) COMMENT='Table that stores information about regions in a province';

CREATE TABLE district (
    id_district INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    id_region INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_district PRIMARY KEY (id_district),
    UNIQUE KEY district_name_unique (name, id_region),
    CONSTRAINT fk_district_region FOREIGN KEY (id_region)
        REFERENCES region(id_region)
        ON DELETE CASCADE,
    CHECK (id_region > 0)
) COMMENT='Table that stores information about districts in a region';

CREATE TABLE person (
    id_person INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    birth_date DATE NOT NULL,
    identification_number INT NOT NULL UNIQUE,
    id_gender INT,
    id_id_type INT,
    id_country_represents INT,
    id_district INT,
    CONSTRAINT pk_person PRIMARY KEY (id_person),
    CONSTRAINT fk_person_id_gender FOREIGN KEY (id_gender)
        REFERENCES gender(id_gender) ON DELETE CASCADE,
    CONSTRAINT fk_person_country_repr FOREIGN KEY (id_country_represents)
        REFERENCES country(id_country) ON DELETE CASCADE,
    CONSTRAINT fk_person_id_district FOREIGN KEY (id_district)
        REFERENCES district(id_district) ON DELETE CASCADE,
    CONSTRAINT chk_person_birth CHECK (birth_date <= '2024-01-01'),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about persons';

CREATE TABLE trainer (
    id_trainer INT NOT NULL,
    CONSTRAINT pk_trainer PRIMARY KEY (id_trainer),
    CONSTRAINT fk_trainer_id_person FOREIGN KEY (id_trainer)
        REFERENCES person(id_person) ON DELETE CASCADE,
    CONSTRAINT chk_trainer_id CHECK (id_trainer > 0),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about persons who are trainers in the system';

CREATE TABLE role (
    id_role INT(8) NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_role PRIMARY KEY (id_role),
    name VARCHAR(25) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about user roles in the system';

CREATE TABLE user_person (
    id_user INT NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id_user),
    CONSTRAINT fk_user_id_person FOREIGN KEY (id_user)
        REFERENCES person(id_person) ON DELETE CASCADE,
    username VARCHAR(25) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    id_role INT(8),
    CONSTRAINT fk_user_id_role FOREIGN KEY (id_role)
        REFERENCES role(id_role) ON DELETE CASCADE,
    CONSTRAINT chk_user_id CHECK (id_user > 0),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about user profiles in the system';

CREATE TABLE athlete (
    id_athlete INT NOT NULL,
    CONSTRAINT pk_athlete PRIMARY KEY (id_athlete),
    CONSTRAINT fk_athlete_id FOREIGN KEY (id_athlete)
        REFERENCES person(id_person) ON DELETE CASCADE,
    id_trainer INT,
    CONSTRAINT fk_athlete_id_trainer FOREIGN KEY (id_trainer)
        REFERENCES trainer(id_trainer) ON DELETE CASCADE,
    CONSTRAINT chk_athlete_not_trainer CHECK (id_athlete != id_trainer),
    CONSTRAINT chk_athlete_id CHECK (id_athlete > 0),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about persons who are athletes in the system';

CREATE TABLE email (
    id_email INT NOT NULL auto_increment,
    email_direction VARCHAR(30) NOT NULL,
    id_person INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    
    -- Constraints
    CONSTRAINT pk_email PRIMARY KEY (id_email),
    CONSTRAINT email_dir_unique_nn UNIQUE (email_direction),
    CONSTRAINT fk_email_id_person FOREIGN KEY (id_person)
        REFERENCES person(id_person) ON DELETE CASCADE
) COMMENT='Tabla que almacena la información de las direcciones de correo en el sistema';

CREATE TABLE person_phone (
    id_person INT auto_increment,
    id_phone INT,
    PRIMARY KEY (id_person, id_phone),
    FOREIGN KEY (id_person) REFERENCES person(id_person) ON DELETE CASCADE,
    FOREIGN KEY (id_phone) REFERENCES phone(id_phone) ON DELETE CASCADE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about person-phone relationships in the system';

CREATE TABLE person_identification_type (
    id_person INT auto_increment,
    id_identification_type INT,
    PRIMARY KEY (id_person, id_identification_type),
    FOREIGN KEY (id_person) REFERENCES person(id_person) ON DELETE CASCADE,
    FOREIGN KEY (id_identification_type) REFERENCES identification_type(id_identification_type),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about person identification types in the system';

CREATE TABLE person_nationality (
    id_person INT auto_increment,
    id_nationality INT,
    PRIMARY KEY (id_person, id_nationality),
    FOREIGN KEY (id_person) REFERENCES person(id_person) ON DELETE CASCADE,
    FOREIGN KEY (id_nationality) REFERENCES nationality(id_nationality) ON DELETE CASCADE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) COMMENT='Table that stores information about person nationalities in the system';

DROP TABLE if exists photo;
DROP TABLE IF EXISTS person_phone;
DROP TABLE IF EXISTS person_identification_type;
DROP TABLE IF EXISTS person_nationality;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS athlete;
DROP TABLE IF EXISTS user_person;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS trainer;
DROP TABLE IF EXISTS person;
