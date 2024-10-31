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
CALL get_country_id('Suecia', @country_id);
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

CALL get_all_people();

CALL get_all_users();