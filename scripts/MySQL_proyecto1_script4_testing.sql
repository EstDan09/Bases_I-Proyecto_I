CALL register_nationality('Colombiana');  
CALL register_nationality('Mexicana');  
CALL register_nationality('Peruana');
CALL register_nationality('Costarricense');
CALL register_nationality('Española');
CALL register_nationality('Inglesa');
CALL register_nationality('Vietnamita');
CALL register_nationality('China');
CALL register_nationality('Sudafricana');
CALL register_nationality('Senegalés');
CALL get_all_nationalities(); 

CALL register_gender('Masculino');
CALL register_gender('Femenino');
CALL register_gender('No Binario');
CALL register_gender('Fluido');
CALL register_gender('Otro');
CALL get_all_genders();

CALL register_identification_type('Cedula');
CALL register_identification_type('Pasaporte');
CALL register_identification_type('Licencia de Conducir');
CALL get_all_identification_types();

CALL register_category('100m planos','Masculino','Individual');
CALL register_category('100m vallas relevo','Femenino','Grupal');
CALL register_category('Basquetbol','Masculino','Grupal');
CALL get_all_categories();

CALL register_country('Argentina', 'pathArgentina');
CALL register_country('Brasil', 'pathBrasil');
CALL register_country('Chile', 'pathChile');
CALL register_country('Colombia', 'pathColombia');
CALL register_country('México', 'pathMexico');
CALL register_country('Perú', 'pathPeru');
CALL register_country('Venezuela', 'pathVenezuela');
CALL register_country('Uruguay', 'pathUruguay');
CALL register_country('Paraguay', 'pathParaguay');
CALL register_country('Ecuador', 'pathEcuador');
CALL register_country('Estados Unidos', 'pathEstadosUnidos');
CALL register_country('Canadá', 'pathCanada');
CALL register_country('Francia', 'pathFrancia');
CALL register_country('Alemania', 'pathAlemania');
CALL register_country('Italia', 'pathItalia');
CALL register_country('España', 'pathEspaña');
CALL register_country('Reino Unido', 'pathReinoUnido');
CALL register_country('Portugal', 'pathPortugal');
CALL register_country('Suiza', 'pathSuiza');
CALL register_country('Bélgica', 'pathBelgica');
CALL register_country('Japón', 'pathJapon');
CALL register_country('China', 'pathChina');
CALL register_country('Corea del Sur', 'pathCoreaDelSur');
CALL register_country('India', 'pathIndia');
CALL register_country('Australia', 'pathAustralia');
CALL register_country('Nueva Zelanda', 'pathNuevaZelanda');
CALL register_country('Sudáfrica', 'pathSudafrica');
CALL register_country('Egipto', 'pathEgipto');
CALL register_country('Marruecos', 'pathMarruecos');
CALL register_country('Nigeria', 'pathNigeria');
CALL register_country('Arabia Saudita', 'pathArabiaSaudita');
CALL register_country('Israel', 'pathIsrael');
CALL register_country('Turquía', 'pathTurquia');
CALL register_country('Grecia', 'pathGrecia');
CALL register_country('Suecia', 'pathSuecia');
CALL get_all_countries();

SET @country_id = 0;
CALL get_country_id('Colombia', @country_id);
SELECT @country_id AS CountryID;

CALL register_province('Macedonia', 'Grecia');
CALL register_province('Buenos Aires', 'Argentina');
CALL register_province('São Paulo', 'Brasil');
CALL register_province('Santiago', 'Chile');
CALL register_province('Bogotá', 'Colombia');
CALL register_province('Ciudad de México', 'México');
CALL register_province('Lima', 'Perú');
CALL register_province('Caracas', 'Venezuela');
CALL register_province('Montevideo', 'Uruguay');
CALL register_province('Asunción', 'Paraguay');
CALL register_province('Quito', 'Ecuador');
CALL register_province('Washington D.C.', 'Estados Unidos');
CALL register_province('Ottawa', 'Canadá');
CALL register_province('París', 'Francia');
CALL register_province('Berlín', 'Alemania');
CALL register_province('Roma', 'Italia');
CALL register_province('Madrid', 'España');
CALL register_province('Londres', 'Reino Unido');
CALL register_province('Lisboa', 'Portugal');
CALL register_province('Berna', 'Suiza');
CALL register_province('Bruselas', 'Bélgica');
CALL register_province('Tokio', 'Japón');
CALL register_province('Pekín', 'China');
CALL register_province('Seúl', 'Corea del Sur');
CALL register_province('Nueva Delhi', 'India');
CALL register_province('Canberra', 'Australia');
CALL register_province('Wellington', 'Nueva Zelanda');
CALL register_province('Ciudad del Cabo', 'Sudáfrica');
CALL register_province('El Cairo', 'Egipto');
CALL register_province('Rabat', 'Marruecos');
CALL register_province('Abuja', 'Nigeria');
CALL register_province('Riyadh', 'Arabia Saudita');
CALL register_province('Jerusalén', 'Israel');
CALL register_province('Estambul', 'Turquía');
CALL register_province('Estocolmos', 'Suecia');
CALL get_all_provinces();

CALL register_region('Thessaloniki', 'Macedonia', 'Grecia');
CALL register_region('Mar del Plata', 'Buenos Aires', 'Argentina');
CALL register_region('Campinas', 'São Paulo', 'Brasil');
CALL register_region('Valparaíso', 'Santiago', 'Chile');
CALL register_region('Medellín', 'Bogotá', 'Colombia');
CALL register_region('Guadalajara', 'Ciudad de México', 'México');
CALL register_region('Cusco', 'Lima', 'Perú');
CALL register_region('Valencia', 'Caracas', 'Venezuela');
CALL register_region('Punta del Este', 'Montevideo', 'Uruguay');
CALL register_region('Ciudad del Este', 'Asunción', 'Paraguay');
CALL register_region('Guayaquil', 'Quito', 'Ecuador');
CALL register_region('Los Angeles', 'Washington D.C.', 'Estados Unidos');
CALL register_region('Toronto', 'Ottawa', 'Canadá');
CALL register_region('Lyon', 'París', 'Francia');
CALL register_region('Hamburg', 'Berlín', 'Alemania');
CALL register_region('Naples', 'Roma', 'Italia');
CALL register_region('Barcelona', 'Madrid', 'España');
CALL register_region('Manchester', 'Londres', 'Reino Unido');
CALL register_region('Porto', 'Lisboa', 'Portugal');
CALL register_region('Zurich', 'Berna', 'Suiza');
CALL register_region('Antwerp', 'Bruselas', 'Bélgica');
CALL register_region('Kyoto', 'Tokio', 'Japón');
CALL register_region('Shanghai', 'Pekín', 'China');
CALL register_region('Busan', 'Seúl', 'Corea del Sur');
CALL register_region('Mumbai', 'Nueva Delhi', 'India');
CALL register_region('Sydney', 'Canberra', 'Australia');
CALL register_region('Auckland', 'Wellington', 'Nueva Zelanda');
CALL register_region('Johannesburg', 'Ciudad del Cabo', 'Sudáfrica');
CALL register_region('Alexandria', 'El Cairo', 'Egipto');
CALL register_region('Casablanca', 'Rabat', 'Marruecos');
CALL register_region('Lagos', 'Abuja', 'Nigeria');
CALL register_region('Mecca', 'Riyadh', 'Arabia Saudita');
CALL register_region('Haifa', 'Jerusalén', 'Israel');
CALL register_region('Izmir', 'Estambul', 'Turquía');
CALL register_region('Göteborg', 'Estocolmos', 'Suecia');
CALL get_all_regions();

SET @region_id = 0;
CALL get_region_id('Göteborg', 'Estocolmo', 'Suecia', @region_id);
SELECT @region_id AS REGIONID;

CALL register_district('Nea Moudania', 'Thessaloniki', 'Macedonia', 'Grecia');
CALL register_district('Miramar', 'Mar del Plata', 'Buenos Aires', 'Argentina');
CALL register_district('Hortolândia', 'Campinas', 'São Paulo', 'Brasil');
CALL register_district('Concón', 'Valparaíso', 'Santiago', 'Chile');
CALL register_district('Bello', 'Medellín', 'Bogotá', 'Colombia');
CALL register_district('Zapopan', 'Guadalajara', 'Ciudad de México', 'México');
CALL register_district('Puno', 'Cusco', 'Lima', 'Perú');
CALL register_district('Maturín', 'Valencia', 'Caracas', 'Venezuela');
CALL register_district('La Barra', 'Punta del Este', 'Montevideo', 'Uruguay');
CALL register_district('San Lorenzo', 'Ciudad del Este', 'Asunción', 'Paraguay');
CALL register_district('Duran', 'Guayaquil', 'Quito', 'Ecuador');
CALL register_district('Silver Spring', 'Los Angeles', 'Washington D.C.', 'Estados Unidos');
CALL register_district('Mississauga', 'Toronto', 'Ottawa', 'Canadá');
CALL register_district('Villeurbanne', 'Lyon', 'París', 'Francia');
CALL register_district('Bremen', 'Hamburg', 'Berlín', 'Alemania');
CALL register_district('Salerno', 'Naples', 'Roma', 'Italia');
CALL register_district('Badalona', 'Barcelona', 'Madrid', 'España');
CALL register_district('Salford', 'Manchester', 'Londres', 'Reino Unido');
CALL register_district('Vila Nova de Gaia', 'Porto', 'Lisboa', 'Portugal');
CALL register_district('Winterthur', 'Zurich', 'Berna', 'Suiza');
CALL register_district('Sint-Niklaas', 'Antwerp', 'Bruselas', 'Bélgica');
CALL register_district('Kōbe', 'Kyoto', 'Tokio', 'Japón');
CALL register_district('Nanjing', 'Shanghai', 'Pekín', 'China');
CALL register_district('Gwangju', 'Busan', 'Seúl', 'Corea del Sur');
CALL register_district('Pune', 'Mumbai', 'Nueva Delhi', 'India');
CALL register_district('Wollongong', 'Sydney', 'Canberra', 'Australia');
CALL register_district('Hamilton', 'Auckland', 'Wellington', 'Nueva Zelanda');
CALL register_district('Pretoria', 'Johannesburg', 'Ciudad del Cabo', 'Sudáfrica');
CALL register_district('Tanta', 'Alexandria', 'El Cairo', 'Egipto');
CALL register_district('Marrakech', 'Casablanca', 'Rabat', 'Marruecos');
CALL register_district('Ibadan', 'Lagos', 'Abuja', 'Nigeria');
CALL register_district('Jeddah', 'Mecca', 'Riyadh', 'Arabia Saudita');
CALL register_district('Nazareth', 'Haifa', 'Jerusalén', 'Israel');
CALL register_district('Kocaeli', 'Izmir', 'Estambul', 'Turquía');
CALL register_district('Mölndal', 'Göteborg', 'Estocolmo', 'Suecia');
CALL get_all_districts();

SET @district_id = 0;
CALL get_district_id('Kocaeli', 'Izmir', 'Estambul', 'Turquía', @district_id);
SELECT @district_id AS REGIONID;

CALL register_role('user');
CALL register_role('admin');
CALL register_role('owner');
CALL get_all_roles();

CALL remove_role('owner');
CALL get_all_roles();


SET @district_id = 0;
CALL get_district_id('Kocaeli', 'Izmir', 'Estambul', 'Turquía', @district_id);
SELECT @district_id AS REGIONID;
CALL register_person_user(
    'John',              -- fname_given
    'Doe',               -- lname_given
    '15-08-1990',        -- bdate_given (format 'DD-MM-YYYY')
    123456789,           -- idnumber_given
    'Masculino',              -- gender_given (must exist in gender table)
    'Argentina',               -- country_given (must exist in country table)
    'Costarricense',          -- nationality_given (must exist in nationality table)
    @district_id,                   -- district_id (must exist in district table)
    'Pasaporte',          -- idtype_given (must exist in identification_type table)
    'john.jpg',  -- path_given (path to photo)
    9876,          -- phone_number_given
    'john.doe@example.com', -- email_given
    'user',              -- role_given (must exist in role table)
    'johndoe',           -- usern_given (unique username)
    'securepassword123'  -- passw_given
);
CALL register_person_user('Lucas', 'Gomez', '01-01-1991', 123456790, 'Masculino', 'Argentina', 'Colombiana', @district_id, 'Cedula', 'lucas.jpg', 1001, 'lucas.gomez@example.com', 'user', 'lucasgomez', 'passwordLucas1');
CALL register_person_user('Maria', 'Garcia', '02-02-1992', 123456791, 'Femenino', 'Chile', 'Mexicana', @district_id, 'Pasaporte', 'maria.jpg', 1002, 'maria.garcia@example.com', 'user', 'mariagarcia', 'passwordMaria2');
CALL register_person_user('Diego', 'Perez', '03-03-1993', 123456792, 'Masculino', 'Perú', 'Peruana', @district_id, 'Licencia de Conducir', 'diego.jpg', 1003, 'diego.perez@example.com', 'user', 'diegoperez', 'passwordDiego3');
CALL register_person_user('Laura', 'Lopez', '04-04-1994', 123456793, 'Femenino', 'México', 'Española', @district_id, 'Cedula', 'laura.jpg', 1004, 'laura.lopez@example.com', 'user', 'lauralopez', 'passwordLaura4');
CALL register_person_user('Carlos', 'Martinez', '05-05-1995', 123456794, 'Masculino', 'Venezuela', 'Costarricense', @district_id, 'Pasaporte', 'carlos.jpg', 1005, 'carlos.martinez@example.com', 'user', 'carlosmartinez', 'passwordCarlos5');
CALL register_person_user('Ana', 'Rodriguez', '06-06-1996', 123456795, 'Femenino', 'Brasil', 'Inglesa', @district_id, 'Licencia de Conducir', 'ana.jpg', 1006, 'ana.rodriguez@example.com', 'admin', 'anarodriguez', 'passwordAna6');
CALL register_person_user('Pedro', 'Hernandez', '07-07-1997', 123456796, 'Masculino', 'Uruguay', 'Vietnamita', @district_id, 'Cedula', 'pedro.jpg', 1007, 'pedro.hernandez@example.com', 'admin', 'pedrohernandez', 'passwordPedro7');
CALL register_person_user('Sofia', 'Ramirez', '08-08-1998', 123456797, 'Femenino', 'Paraguay', 'China', @district_id, 'Pasaporte', 'sofia.jpg', 1008, 'sofia.ramirez@example.com', 'admin', 'sofiaramirez', 'passwordSofia8');
CALL register_person_user('Juan', 'Fernandez', '09-09-1999', 123456798, 'Masculino', 'Ecuador', 'Sudafricana', @district_id, 'Licencia de Conducir', 'juan.jpg', 1009, 'juan.fernandez@example.com', 'admin', 'juanfernandez', 'passwordJuan9');
CALL register_person_user('Marta', 'Diaz', '10-10-2000', 123456799, 'Femenino', 'España', 'Senegalés', @district_id, 'Cedula', 'marta.jpg', 1010, 'marta.diaz@example.com', 'admin', 'martadiaz', 'passwordMarta10');
CALL get_all_people();
CALL get_all_users();

SET @district_id1 = 0;
SET @district_id2 = 0;
SET @district_id3 = 0;
SET @district_id4 = 0;
SET @district_id5 = 0;
SET @district_id6 = 0;
SET @district_id7 = 0;
SET @district_id8 = 0;
SET @district_id9 = 0;
SET @district_id10 = 0;
CALL get_district_id('Nea Moudania', 'Thessaloniki', 'Macedonia', 'Grecia', @district_id1);
CALL get_district_id('Miramar', 'Mar del Plata', 'Buenos Aires', 'Argentina', @district_id2);
CALL get_district_id('Concón', 'Valparaíso', 'Santiago', 'Chile', @district_id3);
CALL get_district_id('Bello', 'Medellín', 'Bogotá', 'Colombia', @district_id4);
CALL get_district_id('Zapopan', 'Guadalajara', 'Ciudad de México', 'México', @district_id5);
CALL get_district_id('Puno', 'Cusco', 'Lima', 'Perú', @district_id6);
CALL get_district_id('La Barra', 'Punta del Este', 'Montevideo', 'Uruguay', @district_id7);
CALL get_district_id('Duran', 'Guayaquil', 'Quito', 'Ecuador', @district_id8);
CALL get_district_id('Silver Spring', 'Los Angeles', 'Washington D.C.', 'Estados Unidos', @district_id9);
CALL get_district_id('Villeurbanne', 'Lyon', 'París', 'Francia', @district_id10);
CALL register_person_trainer('Andres', 'Castro', '01-01-1980', 100001, 'Masculino', 'Argentina', 'Colombiana', @district_id1, 'Cedula', 'maria.jpg', 2001, 'andres.castro@example.com');
CALL register_person_trainer('Lucia', 'Perez', '02-02-1981', 100002, 'Femenino', 'Chile', 'Mexicana', @district_id2, 'Pasaporte', 'lucia.jpg', 2002, 'lucia.perez@example.com');
CALL register_person_trainer('Mateo', 'Ruiz', '03-03-1982', 100003, 'Masculino', 'Perú', 'Peruana', @district_id3, 'Licencia de Conducir', 'mateo.jpg', 2003, 'mateo.ruiz@example.com');
CALL register_person_trainer('Sofia', 'Gomez', '04-04-1983', 100004, 'Femenino', 'México', 'Española', @district_id4, 'Cedula', 'sofia.jpg', 2004, 'sofia.gomez@example.com');
CALL register_person_trainer('Carlos', 'Martinez', '05-05-1984', 100005, 'Masculino', 'Venezuela', 'Costarricense', @district_id5, 'Pasaporte', 'carlos.jpg', 2005, 'car2los.martinez@example.com');
CALL register_person_trainer('Laura', 'Fernandez', '06-06-1985', 100006, 'Femenino', 'Brasil', 'Inglesa', @district_id6, 'Licencia de Conducir', 'laura.jpg', 2006, 'lau2ra.fernandez@example.com');
CALL register_person_trainer('Pedro', 'Hernandez', '07-07-1986', 100007, 'Masculino', 'Uruguay', 'Vietnamita', @district_id7, 'Cedula', 'pedro.jpg', 2007, 'pedro.hernan2dez@example.com');
CALL register_person_trainer('Ana', 'Ramirez', '08-08-1987', 100008, 'Femenino', 'Paraguay', 'China', @district_id8, 'Pasaporte', 'ana.jpg', 2008, 'ana.ramirez@exam2ple.com');
CALL register_person_trainer('Juan', 'Lopez', '09-09-1988', 100009, 'Masculino', 'Ecuador', 'Sudafricana', @district_id9, 'Licencia de Conducir', 'juan.jpg', 2009, 'juan.lopez@exampl2.com');
CALL register_person_trainer('Marta', 'Diaz', '10-10-1989', 100010, 'Femenino', 'España', 'Senegalés', @district_id10, 'Cedula', 'marta.jpg', 2010, 'marta.diaz@exampl2e.com');
CALL get_all_people();
CALL get_all_trainers();

SET @district_id1 = 0;
SET @district_id2 = 0;
SET @district_id3 = 0;
SET @district_id4 = 0;
SET @district_id5 = 0;
SET @district_id6 = 0;
SET @district_id7 = 0;
SET @district_id8 = 0;
SET @district_id9 = 0;
SET @district_id10 = 0;
CALL get_district_id('Nea Moudania', 'Thessaloniki', 'Macedonia', 'Grecia', @district_id1);
CALL get_district_id('Miramar', 'Mar del Plata', 'Buenos Aires', 'Argentina', @district_id2);
CALL get_district_id('Concón', 'Valparaíso', 'Santiago', 'Chile', @district_id3);
CALL get_district_id('Bello', 'Medellín', 'Bogotá', 'Colombia', @district_id4);
CALL get_district_id('Zapopan', 'Guadalajara', 'Ciudad de México', 'México', @district_id5);
CALL get_district_id('Puno', 'Cusco', 'Lima', 'Perú', @district_id6);
CALL get_district_id('La Barra', 'Punta del Este', 'Montevideo', 'Uruguay', @district_id7);
CALL get_district_id('Duran', 'Guayaquil', 'Quito', 'Ecuador', @district_id8);
CALL get_district_id('Silver Spring', 'Los Angeles', 'Washington D.C.', 'Estados Unidos', @district_id9);
CALL get_district_id('Villeurbanne', 'Lyon', 'París', 'Francia', @district_id10);
SET @trainerid1 = 0;
SET @trainerid2 = 0;
SET @trainerid3 = 0;
SET @trainerid4 = 0;
SET @trainerid5 = 0;
SET @trainerid6 = 0;
SET @trainerid7 = 0;
SET @trainerid8 = 0;
SET @trainerid9 = 0;
SET @trainerid10 = 0;
CALL get_trainer_id_by_identification_number(100004, @trainerid1);
CALL get_trainer_id_by_identification_number(100001, @trainerid2);
CALL get_trainer_id_by_identification_number(100002, @trainerid3);
CALL get_trainer_id_by_identification_number(100003, @trainerid4);
CALL get_trainer_id_by_identification_number(100005, @trainerid5);
CALL get_trainer_id_by_identification_number(100006, @trainerid6);
CALL get_trainer_id_by_identification_number(100007, @trainerid7);
CALL get_trainer_id_by_identification_number(100008, @trainerid8);
CALL get_trainer_id_by_identification_number(100009, @trainerid9);
CALL get_trainer_id_by_identification_number(100010, @trainerid10);
CALL register_person_athlete('Joe', 'Roger', '10-10-1989', 100012, 'Masculino', 'España', 'Senegalés', @district_id1, 'Cedula', 'mu.jpg', 2010, 'maz@exampl2e.com', @trainerid1);
CALL register_person_athlete('Nicolas', 'Cruz', '11-11-1989', 100013, 'Masculino', 'Argentina', 'Mexicana', @district_id2, 'Cedula', 'nicolas.jpg', 2011, 'nicolas.cruz@example.com', @trainerid2);
CALL register_person_athlete('Gabriela', 'Silva', '12-12-1988', 100014, 'Femenino', 'Chile', 'Senegalés', @district_id3, 'Pasaporte', 'gabriela.jpg', 2012, 'gabriela.silva@example.com', @trainerid3);
CALL register_person_athlete('Fernando', 'Morales', '13-01-1987', 100015, 'Masculino', 'Japón', 'Peruana', @district_id4, 'Licencia de Conducir', 'fernando.jpg', 2013, 'fernando.morales@example.com', @trainerid4);
CALL register_person_athlete('Isabella', 'Gonzalez', '14-02-1986', 100016, 'Femenino', 'Paraguay', 'Senegalés', @district_id5, 'Cedula', 'isabella.jpg', 2014, 'isabella.gonzalez@example.com', @trainerid5);
CALL register_person_athlete('Diego', 'Hernandez', '15-03-1985', 100017, 'Masculino', 'Uruguay', 'Senegalés', @district_id6, 'Pasaporte', 'diego.jpg', 2015, 'diego.hernandez@example.com', @trainerid6);
CALL register_person_athlete('Clara', 'Lopez', '16-04-1984', 100018, 'Femenino', 'Colombia', 'Inglesa', @district_id7, 'Licencia de Conducir', 'clara.jpg', 2016, 'clara.lopez@example.com', @trainerid7);
CALL register_person_athlete('Pablo', 'Jimenez', '17-05-1983', 100019, 'Masculino', 'Ecuador', 'China', @district_id8, 'Cedula', 'pablo.jpg', 2017, 'pablo.jimenez@example.com', @trainerid8);
CALL register_person_athlete('Sofia', 'Reyes', '18-06-1982', 100020, 'Femenino', 'Argentina', 'Española', @district_id9, 'Pasaporte', 'sofia.jpg', 2018, 'sofia.reyes@example.com', @trainerid9);
CALL register_person_athlete('Alberto', 'Nunez', '19-07-1981', 100021, 'Masculino', 'Chile', 'Vietnamita', @district_id10, 'Licencia de Conducir', 'alberto.jpg', 2019, 'alberto.nunez@example.com', @trainerid10);
CALL get_all_athletes();

CALL register_sport('Soccer', 'A team sport played with a spherical ball between two teams of 11 players.', 'No hands allowed, offside rule applies.');
CALL register_sport('Basketball', 'A team sport where two teams try to score points by throwing a ball into a hoop.', 'Players must dribble the ball while moving.');
CALL register_sport('Tennis', 'A racket sport that can be played individually against a single opponent or between two teams.', 'Ball must land in bounds on serve; one bounce allowed.');
CALL register_sport('Swimming', 'An individual or team sport that involves using arms and legs to move through water.', 'Different strokes apply; no false starts allowed.');
CALL register_sport('Athletics', 'A collection of sporting events involving competitive running, jumping, throwing, and walking.', 'Rules vary per event; start blocks for sprints.');
CALL register_sport('Cycling', 'A sport in which individuals or teams race on bicycles.', 'Drafting allowed; no contact between bikes.');
CALL register_sport('Boxing', 'A combat sport in which two people throw punches at each other.', 'Punch above the waist only; referee can stop the fight.');
CALL register_sport('Judo', 'A martial art where opponents try to throw or pin each other.', 'No striking; scoring based on throws, holds.');
CALL register_sport('Gymnastics', 'A sport involving exercises requiring strength, flexibility, and balance.', 'Different apparatus have unique scoring criteria.');
CALL register_sport('Rugby', 'A team sport where players try to carry or kick the ball across the opponent’s goal line.', 'Forward passes are illegal; tackling allowed.');
CALL register_sport('Badminton', 'A racket sport played with a shuttlecock over a net.', 'Only one hit per side; must land within bounds.');
CALL register_sport('Golf', 'An individual sport where players use clubs to hit balls into holes on a course.', 'Fewest strokes wins; certain penalties apply.');
CALL register_sport('Rowing', 'A team or individual sport involving propelling a boat on water.', 'Race against the clock or other boats.');
CALL register_sport('Skiing', 'A sport using skis to glide on snow.', 'Various events like slalom; certain gates must be passed.');
CALL register_sport('Table Tennis', 'A racket sport played on a table with a small ball.', 'Two bounces allowed on serve; win by 2-point margin.');
CALL get_all_sports();

CALL update_sport('name', 'Football', 'Football', NULL, NULL);
CALL update_sport('description', 'Basketball', NULL, 'A fast-paced sport played on a court, where players try to score by shooting a ball into a hoop.', NULL);
CALL update_sport('rules', 'Tennis', NULL, NULL, 'Must win by 2 games in the final set; no coaching allowed during play.');
CALL update_sport('all', 'Swimming', 'Aquatics', 'A water-based sport involving swimming techniques.', 'No touching the lane ropes; must surface within 15 meters after diving.');
CALL get_all_sports();

CALL register_olympic('Buenos Aires 97', 1997, 'Argentina');
CALL register_olympic('Rio de Janeiro 99', 1999, 'Brasil');
CALL register_olympic('Santiago 00', 2000, 'Chile');
CALL register_olympic('Bogotá 01', 2001, 'Colombia');
CALL register_olympic('Mexico City 02', 2002, 'México');
CALL register_olympic('Lima 03', 2003, 'Perú');
CALL register_olympic('Caracas 04', 2004, 'Venezuela');
CALL register_olympic('Montevideo 05', 2005, 'Uruguay');
CALL register_olympic('Asunción 06', 2006, 'Paraguay');
CALL register_olympic('Quito 07', 2007, 'Ecuador');
CALL get_all_olympics();

CALL register_team('Team Alpha', 100001);
CALL register_team('Team Bravo', 100002);
CALL register_team('Team Charlie', 100003);
CALL register_team('Team Delta', 100004);
CALL register_team('Team Echo', 100005);
CALL register_team('Team Foxtrot',100006);
CALL register_team('Team Golf', 100007);
CALL register_team('Team Rogo', 100008);
CALL get_all_teams();

SET @country_name = '';
CALL get_country_of_team('Team Alpha', @country_name);
SELECT @country_name AS CountryRepresented;

CALL register_athlete_to_team(100013 , 'Team Alpha');
CALL get_all_teams();

CALL register_event(
    '15-07-2001',       -- date_given (using format DD-MM-YYYY)
    '10:00:00',         -- time_given (using format HH:MM:SS)
    '100m Sprint',      -- name_given (event name)
    2001,               -- year_olympic_given (Olympic year)
    'Athletics',                  -- sport_name_given (assuming the ID for 'Athletics' is 1, replace if different)
    '100m planos',      -- category_title
    'Masculino',        -- category_gender
    'Individual'        -- category_quantity
);

CALL register_event(
    '20-08-2001',       -- date_given (using format DD-MM-YYYY)
    '14:30:00',         -- time_given (using format HH:MM:SS)
    'Final', -- name_given (event name)
    2001,               -- year_olympic_given (Olympic year)
    'Athletics',                  -- sport_name_given (assuming the ID for 'Athletics' is 1, replace if different)
    '100m vallas relevo', -- category_title
    'Femenino',         -- category_gender
    'Grupal'            -- category_quantity
);

CALL register_event(
    '25-09-2001',       -- date_given (using format DD-MM-YYYY)
    '18:00:00',         -- time_given (using format HH:MM:SS)
    'Basketball Finals',-- name_given (event name)
    2001,               -- year_olympic_given (Olympic year)
    'Basketball',                  -- sport_name_given (assuming the ID for 'Basketball' is 2, replace if different)
    'Basquetbol',       -- category_title
    'Masculino',        -- category_gender
    'Grupal'            -- category_quantity
);

CALL get_all_events();

CAll add_team_to_event(1,1,5.0,5.0,2);

set @r = 0;
CALL get_medal_id_by_event(1, 'gold', @r);
SELECT @r;

CALL get_age_statistics();

CALL get_participants_filter('sport', 'Athletics', NULL);
CALL get_participants_filter('year', NULL, 2023);
CALL get_participants_filter(NULL, NULL, NULL); -- Para todos los participantes sin filtro

CALL get_medal_ranking_by_olympic('Bogotá 01');

CALL get_event_list('all', NULL, NULL);
CALL get_event_list('sport', NULL, 'Athletics');
CALL get_event_list('olympic', 'Bogotá 01', NULL);

CALL get_top_scores_by_sport();
