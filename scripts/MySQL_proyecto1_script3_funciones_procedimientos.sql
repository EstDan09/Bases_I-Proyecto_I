# Procedimientos/Funciones de Nationality
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_nationality; 
CREATE PROCEDURE register_nationality(IN given_name VARCHAR(25))
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    -- Verificar si ya existe una nacionalidad con el nombre dado
    SELECT COUNT(*) INTO clone_checker
    FROM nationality
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        -- Lanzar un error personalizado
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nationality already registered on DB';
    ELSE
        -- Insertar la nueva nacionalidad
        INSERT INTO nationality(name)
        VALUES (given_name);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_nationality_name; 
CREATE PROCEDURE get_id_by_nationality_name(IN given_nationality VARCHAR(50), OUT id_found INT)
BEGIN
    SET id_found = 0;
    SELECT id_nationality INTO id_found
    FROM nationality
    WHERE name = given_nationality;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_nationality; 
CREATE PROCEDURE update_nationality(IN given_name VARCHAR(25), IN new_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Verificar si la nacionalidad actual existe
    SELECT COUNT(*) INTO exists_checker
    FROM nationality
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        -- Lanzar un error personalizado si la nacionalidad no está registrada
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Nationality Entered';
    ELSE
        -- Actualizar el nombre de la nacionalidad
        UPDATE nationality
        SET name = new_name
        WHERE name = given_name;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_nationalities; 
CREATE PROCEDURE get_all_nationalities()
BEGIN
    -- Seleccionar id y nombre de todas las nacionalidades
    SELECT id_nationality, name
    FROM nationality;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_nationality; 
CREATE PROCEDURE remove_nationality(IN given_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Verificar si la nacionalidad existe
    SELECT COUNT(*) INTO exists_checker
    FROM nationality
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        -- Lanzar un error personalizado si la nacionalidad no está registrada
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Nationality Entered';
    ELSE
        -- Eliminar la nacionalidad
        DELETE FROM nationality
        WHERE name = given_name;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Photo
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_photo;
CREATE PROCEDURE register_photo(
    IN new_photo VARCHAR(255),
    IN person_id_given INT
)
BEGIN
    DECLARE photo_checker INT DEFAULT 0;
    SELECT COUNT(*)
    INTO photo_checker
    FROM photo
    WHERE path_photo = new_photo
    AND id_person = person_id_given;
    IF photo_checker > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Photo for this person already registered';
    ELSE
        INSERT INTO photo (path_photo, id_person)
        VALUES (new_photo, person_id_given);
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Flag
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_flag_photo; 
CREATE PROCEDURE register_flag_photo(IN given_path_fp VARCHAR(25))
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    -- Verificar si ya existe una nacionalidad con el nombre dado
    SELECT COUNT(*) INTO clone_checker
    FROM flag_photo
    WHERE path_flag_photo = given_path_fp;

    IF clone_checker > 0 THEN
        -- Lanzar un error personalizado
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Flag already registered on DB';
    ELSE
        -- Insertar la nueva nacionalidad
        INSERT INTO flag_photo(path_flag_photo)
        VALUES (given_path_fp);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_flag_photo; 
CREATE PROCEDURE update_flag_photo(IN given_path VARCHAR(25), IN new_path VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Verificar si la nacionalidad actual existe
    SELECT COUNT(*) INTO exists_checker
    FROM flag_photo
    WHERE path_flag_photo = given_path;

    IF exists_checker = 0 THEN
        -- Lanzar un error personalizado si la nacionalidad no está registrada
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Flag Entered';
    ELSE
        -- Actualizar el nombre de la nacionalidad
        UPDATE flag_photo
        SET path_flag_photo = new_path
        WHERE path_flag_photo = given_path;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_flag_path_by_id; 
CREATE PROCEDURE get_flag_path_by_id(IN given_id INT)
BEGIN
    DECLARE path_found VARCHAR(255);
    SELECT path_flag_photo INTO path_found
    FROM flag_photo
    WHERE id_flag_photo = given_id;
    IF path_found IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No flag path found for the given ID';
    ELSE
        SELECT path_found AS path_flag_photo;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_flag_photo_by_path; 
CREATE PROCEDURE remove_flag_photo_by_path(IN given_path VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    SELECT COUNT(*) INTO exists_checker
    FROM flag_photo
    WHERE path_flag_photo = given_path;
    IF exists_checker = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No flag photo found for the given path';
    ELSE
        DELETE FROM flag_photo
        WHERE path_flag_photo = given_path;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS check_flag_photo; 
CREATE PROCEDURE check_flag_photo(
    IN new_flag VARCHAR(255),
    OUT flag_id INT
)
BEGIN
    SET flag_id = 0;
    SELECT id_flag_photo INTO flag_id
    FROM flag_photo
    WHERE path_flag_photo = new_flag;
    IF flag_id = 0 THEN
        INSERT INTO flag_photo(path_flag_photo)
        VALUES (new_flag);
        SET flag_id = LAST_INSERT_ID();
    END IF;
    SELECT flag_id AS "Generated Flag ID";
END //
DELIMITER ;

# Procedimientos/Funciones de Phone
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_phone; 
CREATE PROCEDURE register_phone(IN given_num INT)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    -- Verificar si ya existe una nacionalidad con el nombre dado
    SELECT COUNT(*) INTO clone_checker
    FROM phone
    WHERE number_phone = given_num;

    IF clone_checker > 0 THEN
        -- Lanzar un error personalizado
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Phone already registered on DB';
    ELSE
        -- Insertar la nueva nacionalidad
        INSERT INTO phone(number_phone)
        VALUES (given_num);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_phone; 
CREATE PROCEDURE update_phone(IN given_num INT, IN new_num INT)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Verificar si la nacionalidad actual existe
    SELECT COUNT(*) INTO exists_checker
    FROM phone
    WHERE number_phone = given_num;

    IF exists_checker = 0 THEN
        -- Lanzar un error personalizado si no está registrada
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Phone Entered';
    ELSE
        -- Actualizar
        UPDATE phone
        SET number_phone = new_num
        WHERE number_phone = given_num;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_phone_number; 
CREATE PROCEDURE get_id_by_phone_number(IN given_number INT, OUT id_found INT)
BEGIN
    SET id_found = 0; -- Initialize to 0
    SELECT id_phone INTO id_found
    FROM phone
    WHERE number_phone = given_number;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_phones; 
CREATE PROCEDURE get_all_phones()
BEGIN
    -- Seleccionar id y nombre de todas las nacionalidades
    SELECT id_phone, number_phone
    FROM phone;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_phone; 
CREATE PROCEDURE remove_phone(IN given_num INT)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Verificar si la nacionalidad existe
    SELECT COUNT(*) INTO exists_checker
    FROM phone
    WHERE number_phone = given_num;

    IF exists_checker = 0 THEN
        -- Lanzar un error personalizado si no está registrada
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Phone Entered';
    ELSE
        -- Eliminar la nacionalidad
        DELETE FROM phone
        WHERE number_phone = given_num;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Gender
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_gender; 
CREATE PROCEDURE register_gender(IN given_name VARCHAR(25))
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    -- Check if the gender already exists
    SELECT COUNT(*) INTO clone_checker
    FROM gender
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        -- Signal a custom error if the gender already exists
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Gender already registered in DB';
    ELSE
        -- Insert the new gender
        INSERT INTO gender(name)
        VALUES (given_name);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_gender; 
CREATE PROCEDURE update_gender(IN given_name VARCHAR(25), IN new_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Check if the current gender exists
    SELECT COUNT(*) INTO exists_checker
    FROM gender
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        -- Signal a custom error if the gender is not registered
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Gender Entered';
    ELSE
        -- Update the gender name
        UPDATE gender
        SET name = new_name
        WHERE name = given_name;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_genders; 
CREATE PROCEDURE get_all_genders()
BEGIN
    -- Select id and name of all genders
    SELECT id_gender, name
    FROM gender;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_gender; 
CREATE PROCEDURE remove_gender(IN given_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Check if the gender exists
    SELECT COUNT(*) INTO exists_checker
    FROM gender
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        -- Signal a custom error if the gender is not registered
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Gender Entered';
    ELSE
        -- Delete the gender
        DELETE FROM gender
        WHERE name = given_name;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_gender_name; 
CREATE PROCEDURE get_id_by_gender_name(IN given_name VARCHAR(25), OUT id_found INT)
BEGIN
    -- Initialize the output variable to 0 (or any value that indicates not found)
    SET id_found = 0;

    -- Check if the gender name exists
    SELECT id_gender INTO id_found
    FROM gender
    WHERE name = given_name;

    -- Optionally check if an ID was found
    IF id_found IS NULL THEN
        -- Set the output variable to 0 if no ID is found
        SET id_found = 0; -- This is optional since we initialized it to 0.
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de ID Type
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_identification_type; 
CREATE PROCEDURE register_identification_type(IN given_name VARCHAR(25))
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    -- Check if the identification type already exists
    SELECT COUNT(*) INTO clone_checker
    FROM identification_type
    WHERE name_identification_type = given_name;

    IF clone_checker > 0 THEN
        -- Signal a custom error if the identification type already exists
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Identification type already registered in DB';
    ELSE
        -- Insert the new identification type
        INSERT INTO identification_type(name_identification_type)
        VALUES (given_name);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_identification_type; 
CREATE PROCEDURE update_identification_type(IN given_name VARCHAR(25), IN new_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Check if the current identification type exists
    SELECT COUNT(*) INTO exists_checker
    FROM identification_type
    WHERE name_identification_type = given_name;

    IF exists_checker = 0 THEN
        -- Signal a custom error if the identification type is not registered
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Identification Type Entered';
    ELSE
        -- Update the identification type name
        UPDATE identification_type
        SET name_identification_type = new_name
        WHERE name_identification_type = given_name;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_identification_types; 
CREATE PROCEDURE get_all_identification_types()
BEGIN
    -- Select id and name of all identification types
    SELECT id_identification_type, name_identification_type
    FROM identification_type;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_identification_type_name; 
CREATE PROCEDURE get_id_by_identification_type_name(IN given_name VARCHAR(25), OUT id_found INT)
BEGIN
    -- Initialize the output variable to 0
    SET id_found = 0;

    -- Check if the identification type name exists
    SELECT id_identification_type INTO id_found
    FROM identification_type
    WHERE name_identification_type = given_name;

    -- Optionally check if an ID was found
    IF id_found IS NULL THEN
        SET id_found = 0; -- This is optional since we initialized it to 0.
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_identification_type; 
CREATE PROCEDURE remove_identification_type(IN given_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Check if the identification type exists
    SELECT COUNT(*) INTO exists_checker
    FROM identification_type
    WHERE name_identification_type = given_name;

    IF exists_checker = 0 THEN
        -- Signal a custom error if the identification type is not registered
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Identification Type Entered';
    ELSE
        -- Delete the identification type
        DELETE FROM identification_type
        WHERE name_identification_type = given_name;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Category
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_category; 
CREATE PROCEDURE register_category (
    IN given_title VARCHAR(25),
    IN given_gender VARCHAR(25),
    IN given_quantity VARCHAR(25)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    -- Check if the category already exists
    SELECT COUNT(*)
    INTO clone_checker
    FROM category
    WHERE title = given_title
      AND gender = given_gender;

    IF clone_checker > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category already registered';
    ELSE
        -- Insert new category
        INSERT INTO category (title, gender, quantity)
        VALUES (given_title, given_gender, given_quantity);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_category; 
CREATE PROCEDURE update_category (
    IN choice VARCHAR(25),
    IN given_title VARCHAR(25),
    IN given_gender VARCHAR(25),
    IN given_quantity VARCHAR(25),
    IN new_title VARCHAR(25),
    IN new_gender VARCHAR(25),
    IN new_quantity VARCHAR(25)
)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    -- Check if the category exists
    SELECT COUNT(*)
    INTO exists_checker
    FROM category
    WHERE title = given_title
      AND gender = given_gender
      AND quantity = given_quantity;

    IF exists_checker = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category not registered';
    ELSE
        CASE choice
            WHEN 'title' THEN
                UPDATE category
                SET title = new_title
                WHERE title = given_title
                  AND gender = given_gender
                  AND quantity = given_quantity;
            WHEN 'gender' THEN
                UPDATE category
                SET gender = new_gender
                WHERE title = given_title
                  AND gender = given_gender
                  AND quantity = given_quantity;
            WHEN 'quantity' THEN
                UPDATE category
                SET quantity = new_quantity
                WHERE title = given_title
                  AND gender = given_gender
                  AND quantity = given_quantity;
            WHEN 'all' THEN
                UPDATE category
                SET title = new_title,
                    gender = new_gender,
                    quantity = new_quantity
                WHERE title = given_title
                  AND gender = given_gender
                  AND quantity = given_quantity;
            ELSE
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END CASE;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_categories()
BEGIN
    -- Retrieve all categories
    SELECT id_category, title, gender, quantity
    FROM category;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_category_id_by_details; 
CREATE PROCEDURE get_category_id_by_details(
    IN category_title VARCHAR(25),
    IN category_gender VARCHAR(25),
    IN category_quantity VARCHAR(25),
    OUT category_id INT
)
BEGIN
    -- Initialize the output variable to 0
    SET category_id = 0;

    -- Retrieve the ID of the category if it exists
    SELECT id_category INTO category_id
    FROM category
    WHERE title = category_title
      AND gender = category_gender
      AND quantity = category_quantity;

    -- Check if an ID was found; if NULL, set it to 0
    IF category_id IS NULL THEN
        SET category_id = 0;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_category (
    IN category_title VARCHAR(25)
)
BEGIN
    DECLARE category_id INT DEFAULT 0;

    -- Retrieve the ID of the category
    SELECT id_category INTO category_id
    FROM category
    WHERE title = category_title;

    IF category_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category not found';
    ELSE
        -- Delete associated events
        DELETE FROM event WHERE id_category = category_id;
        -- Delete the category itself
        DELETE FROM category WHERE id_category = category_id;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Country
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_country; 
CREATE PROCEDURE register_country(
    IN given_name VARCHAR(50),
    IN given_flag_photo VARCHAR(255) 
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE flag_id INT DEFAULT 0;

    -- Check if the country already exists
    SELECT COUNT(*) INTO clone_checker
    FROM country
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country already registered';
    ELSE
        -- Call check_flag_photo to get the flag ID
        CALL check_flag_photo(given_flag_photo, flag_id);  -- Pass flag_id to retrieve the output

        -- Insert the new country with the retrieved flag_id
        INSERT INTO country(name, id_flag_photo)
        VALUES (given_name, flag_id);
    END IF;

END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_country;
CREATE PROCEDURE update_country(
    IN choice VARCHAR(10),
    IN given_name VARCHAR(50),
    IN new_name VARCHAR(50),
    IN new_flag VARCHAR(50)
)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    DECLARE flag_id INT DEFAULT 0;

    -- Check if the country exists
    SELECT COUNT(*) INTO exists_checker
    FROM country
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        SELECT 'Country not registered' AS message;
    ELSE
        IF choice = 'name' THEN
            UPDATE country SET name = new_name WHERE name = given_name;

        ELSEIF choice = 'flag' THEN
            CALL check_flag_photo(new_flag, flag_id);
            UPDATE country SET id_flag_photo = flag_id WHERE name = given_name;
        
        ELSEIF choice = 'both' THEN
            CALL check_flag_photo(new_flag, flag_id);
            UPDATE country SET name = new_name, id_flag_photo = flag_id WHERE name = given_name;
        ELSE
            SELECT 'Invalid choice for update operation' AS message;
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_countries;
CREATE PROCEDURE get_all_countries()
BEGIN
    SELECT c.name AS name, c.id_country AS ID, fp.path_flag_photo AS Path
    FROM country c
    JOIN flag_photo fp ON c.id_flag_photo = fp.id_flag_photo;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_country_id;
CREATE PROCEDURE get_country_id(
    IN country_name VARCHAR(50),
    OUT country_id INT
)
BEGIN
    -- Initialize output to 0
    SET country_id = 0;

    -- Retrieve the country ID if it exists
    SELECT id_country INTO country_id
    FROM country
    WHERE name = country_name;

    -- If no country was found, set country_id to 0
    IF country_id IS NULL THEN
        SET country_id = 0;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS delete_country;
CREATE PROCEDURE delete_country(
    IN country_given VARCHAR(50)
)
BEGIN
    DECLARE country_id INT DEFAULT 0;

    -- Check if country exists and get its ID
    SELECT id_country INTO country_id
    FROM country
    WHERE name = country_given;

    IF country_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country not found';
    ELSE
        -- Delete related records in `person` table
        DELETE FROM person WHERE id_country_represents = country_id;
        
        -- Delete the country itself
        DELETE FROM country WHERE id_country = country_id;
        
        SELECT CONCAT('Country and associated records deleted: ', country_given) AS message;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Province
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_province; 
CREATE PROCEDURE register_province(
    IN given_name VARCHAR(50),
    IN given_country_name VARCHAR(255) 
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE country_id_gotten INT DEFAULT 0;
	CALL get_country_id(given_country_name, country_id_gotten);
    IF country_id_gotten <= 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country isn´t registered';
	ELSE
		SELECT COUNT(*) INTO clone_checker
		FROM province
		WHERE name = given_name AND id_country = country_id_gotten;
        IF clone_checker > 0 THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province already registered';
		ELSE 
			INSERT INTO province(name, id_country)
            VALUES(given_name, country_id_gotten);
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_provinces;
CREATE PROCEDURE get_all_provinces()
BEGIN
    SELECT p.id_province AS ID, p.name AS Name, c.name AS Country
    FROM province p
    INNER JOIN country c ON p.id_country = c.id_country;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_province_id;
CREATE PROCEDURE get_province_id(
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50),
    OUT province_id INT
)
BEGIN
    SET province_id = 0;

    SELECT p.id_province INTO province_id
    FROM province p
    INNER JOIN country c ON p.id_country = c.id_country
    WHERE p.name = province_name AND c.name = country_name;

END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_province;
CREATE PROCEDURE update_province(
    IN choice VARCHAR(10),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50),
    IN new_name VARCHAR(50),
    IN new_country VARCHAR(50)
)
BEGIN
    DECLARE province_id_gotten INT DEFAULT 0;
    DECLARE country_id_gotten INT DEFAULT 0;

    -- Get the province ID based on the province and country names
    CALL get_province_id(province_name, country_name, province_id_gotten);

    -- Check if the province exists
    IF province_id_gotten = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province is not registered in the given country';
    ELSE
        -- Perform updates based on the choice
        IF choice = 'name' THEN
            UPDATE province SET name = new_name WHERE id_province = province_id_gotten;
        
        ELSEIF choice = 'country' THEN
            CALL get_country_id(new_country, country_id_gotten);
            IF country_id_gotten = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country is not registered';
            ELSE
                UPDATE province SET id_country = country_id_gotten WHERE id_province = province_id_gotten;
            END IF;

        ELSEIF choice = 'both' THEN
            CALL get_country_id(new_country, country_id_gotten);
            IF country_id_gotten = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country is not registered';
            ELSE
                UPDATE province SET name = new_name, id_country = country_id_gotten WHERE id_province = province_id_gotten;
            END IF;

        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS delete_province;
CREATE PROCEDURE delete_province(
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50)
)
BEGIN
    DECLARE province_id_gotten INT DEFAULT 0;

    -- Get the province ID based on the province and country names
    CALL get_province_id(province_name, country_name, province_id_gotten);

    -- Check if the province exists
    IF province_id_gotten = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province is not registered in the given country';
    ELSE
        -- Delete the province based on the retrieved ID
        DELETE FROM province WHERE id_province = province_id_gotten;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Region
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_region; 
CREATE PROCEDURE register_region(
    IN given_name VARCHAR(50),
    IN given_province_name VARCHAR(50),
    IN given_country_name VARCHAR(50)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE province_id_gotten INT DEFAULT 0;
    CALL get_province_id(given_province_name, given_country_name, province_id_gotten);
    IF province_id_gotten > 0 THEN
		SELECT COUNT(*) INTO clone_checker
		FROM region
		WHERE name = given_name AND id_province = province_id_gotten;
		IF clone_checker > 0 THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Region already registered';
		ELSE 
			INSERT INTO region(name, id_province)
			VALUES(given_name, province_id_gotten);
		END IF;
    ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province isnt registered on given country';
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_regions;
CREATE PROCEDURE get_all_regions()
BEGIN
    SELECT r.id_region AS ID, r.name AS Name, p.name AS Province, c.name as Country
    FROM region r
    INNER JOIN province p ON r.id_province = p.id_province
    INNER JOIN country c on p.id_country = c.id_country;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_region_id;
CREATE PROCEDURE get_region_id(
    IN region_name VARCHAR(50),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50),
    OUT region_id INT
)
BEGIN
    DECLARE province_id INT DEFAULT 0;
    SET region_id = 0;

    -- Get the province ID based on the province and country names
    CALL get_province_id(province_name, country_name, province_id);

    -- Check if the province exists
    IF province_id > 0 THEN
        -- Get the region ID based on the region name and the retrieved province ID
        SELECT r.id_region INTO region_id
        FROM region r
        WHERE r.name = region_name AND r.id_province = province_id;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_region;
CREATE PROCEDURE update_region(
    IN choice VARCHAR(10),
    IN region_name VARCHAR(50),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50),
    IN new_name VARCHAR(50),
    IN new_province VARCHAR(50)
)
BEGIN
    DECLARE region_id_gotten INT DEFAULT 0;
    DECLARE new_province_id INT DEFAULT 0;

    CALL get_region_id(region_name, province_name, country_name, region_id_gotten);

    IF region_id_gotten = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Region is not registered in the given province';
    ELSE
        IF choice = 'name' THEN
            UPDATE region SET name = new_name WHERE id_region = region_id_gotten;
        
        ELSEIF choice = 'province' THEN
            CALL get_province_id(new_province, country_name, new_province_id);
            IF new_province_id = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New province is not registered';
            ELSE
                UPDATE region SET id_province = new_province_id WHERE id_region = region_id_gotten;
            END IF;

        ELSEIF choice = 'both' THEN
            CALL get_province_id(new_province, country_name, new_province_id);
            IF new_province_id = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New province is not registered';
            ELSE
                UPDATE region SET name = new_name, id_province = new_province_id WHERE id_region = region_id_gotten;
            END IF;
        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS delete_region;
CREATE PROCEDURE delete_region(
	IN region_name VARCHAR(50),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50)
)
BEGIN
    DECLARE region_id_gotten INT DEFAULT 0;

    -- Get the province ID based on the province and country names
    CALL get_region_id(region_name, province_name, country_name, region_id_gotten);

    -- Check if the province exists
    IF region_id_gotten = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province is not registered in the given country';
    ELSE
        -- Delete the province based on the retrieved ID
        DELETE FROM region WHERE id_region = region_id_gotten;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de District
#----------------------------------------#

DELIMITER //
DROP PROCEDURE IF EXISTS register_district; 
CREATE PROCEDURE register_district(
    IN given_name VARCHAR(50),
    IN given_region_name VARCHAR(50),
    IN given_province_name VARCHAR(50),
    IN given_country_name VARCHAR(50)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE region_id_gotten INT DEFAULT 0;
    CALL get_region_id(given_region_name, given_province_name, given_country_name, region_id_gotten);
    IF region_id_gotten > 0 THEN
		SELECT COUNT(*) INTO clone_checker
		FROM district
		WHERE name = given_name AND id_region = region_id_gotten;
		IF clone_checker > 0 THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'District already registered';
		ELSE 
			INSERT INTO district(name, id_region)
			VALUES(given_name, region_id_gotten);
		END IF;
    ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Region isnt registered on given country';
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_districts;
CREATE PROCEDURE get_all_districts()
BEGIN
    SELECT d.id_district AS ID, d.name AS Name, r.name AS Region,p.name AS Province, c.name as Country
    FROM district d
    INNER JOIN region r ON d.id_region = r.id_region
    INNER JOIN province p ON r.id_province = p.id_province
    INNER JOIN country c ON p.id_country = c.id_country;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_district_id;
CREATE PROCEDURE get_district_id(
	IN district_name VARCHAR(50),
    IN region_name VARCHAR(50),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50),
    OUT district_id INT
)
BEGIN
    DECLARE region_id INT DEFAULT 0;
    SET district_id = 0;

    CALL get_region_id(region_name, province_name, country_name, region_id);

    IF region_id > 0 THEN
        SELECT d.id_district INTO district_id
        FROM district d
        WHERE d.name = district_name AND d.id_region = region_id;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_district;
CREATE PROCEDURE update_district(
    IN choice VARCHAR(10),
    IN district_name VARCHAR(50),
    IN region_name VARCHAR(50),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50),
    IN new_name VARCHAR(50),
    IN new_region VARCHAR(50)
)
BEGIN
    DECLARE district_id_gotten INT DEFAULT 0;
    DECLARE new_region_id INT DEFAULT 0;

    CALL get_district_id(district_name, region_name, province_name, country_name, district_id_gotten);

    IF district_id_gotten = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'District is not registered in the given region';
    ELSE
        IF choice = 'name' THEN
            UPDATE district SET name = new_name WHERE id_district = district_id_gotten;
        
        ELSEIF choice = 'region' THEN
            CALL get_region_id(new_region, province_name, country_name, new_region_id);
            IF new_region_id = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New region is not registered';
            ELSE
                UPDATE district SET id_region = new_region_id WHERE id_district = district_id_gotten;
            END IF;

        ELSEIF choice = 'both' THEN
            CALL get_region_id(new_region, province_name, country_name, new_region_id);
            IF new_region_id = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New region is not registered';
            ELSE
				UPDATE district SET name = new_name, id_region = new_region_id WHERE id_district = district_id_gotten;
            END IF;
        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS delete_district;
CREATE PROCEDURE delete_district(
	IN district_name VARCHAR(50),
	IN region_name VARCHAR(50),
    IN province_name VARCHAR(50),
    IN country_name VARCHAR(50)
)
BEGIN
    DECLARE district_id_gotten INT DEFAULT 0;

    -- Get the province ID based on the province and country names
    CALL get_district_id(district_name, region_name, province_name, country_name, district_id_gotten);

    -- Check if the province exists
    IF district_id_gotten = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province is not registered in the given country';
    ELSE
        -- Delete the province based on the retrieved ID
        DELETE FROM district WHERE id_district = district_id_gotten;
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Role
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_role; 
CREATE PROCEDURE register_role(IN given_name VARCHAR(25))
BEGIN
    DECLARE clone_checker INT DEFAULT 0;

    SELECT COUNT(*) INTO clone_checker
    FROM role
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        -- Signal a custom error if the gender already exists
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Role already registered in DB';
    ELSE
        -- Insert the new gender
        INSERT INTO role(name)
        VALUES (given_name);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_role; 
CREATE PROCEDURE update_role(
	IN given_role VARCHAR(25), 
    IN new_role VARCHAR(25)
)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;

    SELECT COUNT(*) INTO exists_checker
    FROM role
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        -- Signal a custom error if the gender is not registered
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered role Entered';
    ELSE
        -- Update the gender name
        UPDATE role
        SET name = new_name
        WHERE name = given_name;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_roles; 
CREATE PROCEDURE get_all_roles()
BEGIN
    -- Select id and name of all genders
    SELECT id_role, name
    FROM role;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_role; 
CREATE PROCEDURE remove_role(IN given_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
	DECLARE role_id INT DEFAULT 0;
    SELECT COUNT(*) INTO exists_checker
    FROM role
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered role Entered';
    ELSE
        CALL get_id_by_role_name(given_name, role_id);
        DELETE FROM role
        WHERE id_role = role_id;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_role_name; 
CREATE PROCEDURE get_id_by_role_name(IN given_name VARCHAR(25), OUT id_found INT)
BEGIN
    -- Initialize the output variable to 0 (or any value that indicates not found)
    SET id_found = 0;

    -- Check if the gender name exists
    SELECT id_role INTO id_found
    FROM role
    WHERE name = given_name;

    -- Optionally check if an ID was found
    IF id_found IS NULL THEN
        -- Set the output variable to 0 if no ID is found
        SET id_found = 0; -- This is optional since we initialized it to 0.
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Email 
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_email;
CREATE PROCEDURE register_email(
    IN new_emaildir VARCHAR(30),
    IN person_id_given INT
)
BEGIN
    DECLARE email_checker INT DEFAULT 0;
    
    SELECT COUNT(*)
    INTO email_checker
    FROM email
    WHERE email_direction = new_emaildir
    AND id_person = person_id_given;
    
    IF email_checker > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Email for this person already registered';
    ELSE
        INSERT INTO email (email_direction, id_person)
        VALUES (new_emaildir, person_id_given);
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Trainer
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_trainer;
CREATE PROCEDURE register_trainer(
    IN person_id_given INT
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    -- Check if the trainer is already registered
    SELECT COUNT(*)
    INTO clone_checker
    FROM trainer
    WHERE id_trainer = person_id_given;
    IF clone_checker > 0 THEN
        -- If already registered, raise an error
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Trainer already registered';
    ELSE
        -- If not registered, insert the new trainer
        INSERT INTO trainer (id_trainer)
        VALUES (person_id_given);
        -- You don't need to commit as it's implicit in a stored procedure
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de User
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_user;
CREATE PROCEDURE register_user(
    IN id_given INT,
    IN username_given VARCHAR(50),
    IN role_given VARCHAR(50),
    IN password_given VARCHAR(50)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE clone_username INT DEFAULT 0;
    DECLARE id_role_g INT DEFAULT 0;
    DECLARE hashed_password VARCHAR(40);  
    SELECT COUNT(*)
    INTO clone_checker
    FROM user_person
    WHERE id_user = id_given;
    IF clone_checker > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User already registered';
    ELSE
        SELECT COUNT(*)
        INTO clone_username
        FROM user_person
        WHERE username = username_given;
        IF clone_username > 0 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Username already taken';
        ELSE
            CALL get_id_by_role_name(role_given, id_role_g);
            IF id_role_g = 0 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Role not found';
            ELSE
                SET hashed_password = SHA1(password_given);
                INSERT INTO user_person (id_user, username, password, id_role)
                VALUES (id_given, username_given, hashed_password, id_role_g);
            END IF;
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_users; 
CREATE PROCEDURE get_all_users()
BEGIN
    SELECT *
    FROM user_person;
END //
DELIMITER ;

# Procedimientos/Funciones de Athlete
#----------------------------------------#

# Procedimientos/Funciones de Person 
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_person_user;
CREATE PROCEDURE register_person_user(
    IN fname_given VARCHAR(25),
    IN lname_given VARCHAR(25),
    IN bdate_given VARCHAR(10),  -- 'DD-MM-YYYY'
    IN idnumber_given INT,
    IN gender_given VARCHAR(25),
    IN country_given VARCHAR(50),
    IN nationality_given VARCHAR(50),
    IN district_id INT,
    IN idtype_given VARCHAR(25),
    IN path_given VARCHAR(255),
    IN phone_number_given INT,
    IN email_given VARCHAR(50),
    IN role_given VARCHAR(25),
    IN usern_given VARCHAR(25),
    IN passw_given VARCHAR(50)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE gender_id INT DEFAULT 0;
    DECLARE nationality_id INT DEFAULT 0;
    DECLARE country_id INT DEFAULT 0;
    DECLARE idtype_id INT DEFAULT 0;
    DECLARE phone_id INT DEFAULT 0;
    DECLARE current_person_id INT DEFAULT 0;

    -- Check if person with the given ID already exists
    SELECT COUNT(*) INTO clone_checker
    FROM person
    WHERE identification_number = idnumber_given;

    IF clone_checker > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Person already registered';
    ELSE
        -- Retrieve foreign key IDs
        CALL get_id_by_gender_name(gender_given, gender_id);
        CALL get_country_id(country_given, country_id);
        CALL get_id_by_nationality_name(nationality_given, nationality_id);
        CALL get_id_by_identification_type_name(idtype_given, idtype_id);

        IF (gender_id > 0) AND (country_id > 0) AND (district_id > 0) AND 
           (idtype_id > 0) AND (nationality_id > 0) THEN
           
            INSERT INTO person (first_name, last_name, birth_date, 
                                identification_number, id_gender, id_id_type, 
                                id_country_represents, id_district)
            VALUES (fname_given, lname_given, STR_TO_DATE(bdate_given, '%d-%m-%Y'), 
                    idnumber_given, gender_id, idtype_id, country_id, district_id);
		
            SET current_person_id = LAST_INSERT_ID();

            CALL register_person_nationality(current_person_id, nationality_id);
            CALL register_photo(path_given, current_person_id);
            CALL register_email(email_given, current_person_id);

            CALL get_id_by_phone_number(phone_number_given, phone_id);
            IF phone_id = 0 THEN
                CALL register_phone(phone_number_given);
                CALL get_id_by_phone_number(phone_number_given, phone_id);
            END IF;
            CALL register_person_phone(current_person_id, phone_id);
            CALL register_user(current_person_id, usern_given, role_given, passw_given);
        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid foreign key data';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_people; 
CREATE PROCEDURE get_all_people()
BEGIN
    SELECT *
    FROM person;
END //
DELIMITER ;
# Procedimientos/Funciones de Person-Nationality 
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_person_nationality;
CREATE PROCEDURE register_person_nationality(
    IN person_id_given INT,
    IN nationality_id_given INT
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_nationality
    WHERE id_person = person_id_given
    AND id_nationality = nationality_id_given;
    IF clone_checker > 0 THEN
        SELECT 'This nationality for this athlete is already registered' AS message;
    ELSE
        INSERT INTO person_nationality (id_person, id_nationality)
        VALUES (person_id_given, nationality_id_given);
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Person-Phone 
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_person_phone;
CREATE PROCEDURE register_person_phone(
    IN person_id_given INT,
    IN phone_id_given INT
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_phone
    WHERE id_person = person_id_given
    AND id_phone = phone_id_given;
    IF clone_checker > 0 THEN
        SELECT 'This phone for this person is already registered' AS message;
    ELSE
        INSERT INTO person_phone (id_person, id_phone)
        VALUES (person_id_given, phone_id_given);
person_nationality    END IF;
END //
DELIMITER ;
