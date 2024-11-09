# Procedimientos/Funciones de Nationality
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_nationality; 
CREATE PROCEDURE register_nationality(IN given_name VARCHAR(25))
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    -- Verificar si ya existe una nacionalidad con el nombre dado
    SELECT COUNT(*) INTO clone_checker
    FROM nationality
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM nationality
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO exists_checker
    FROM nationality
    WHERE name = given_name;

    IF exists_checker = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Nationality Entered';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred on register photo, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*)
    INTO photo_checker
    FROM photo
    WHERE path_photo = new_photo
    AND id_person = person_id_given;
    IF photo_checker > 0 THEN
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO clone_checker
    FROM flag_photo
    WHERE path_flag_photo = given_path_fp;

    IF clone_checker > 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM flag_photo
    WHERE path_flag_photo = given_path;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Flag Entered';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    
    SELECT path_flag_photo INTO path_found
    FROM flag_photo
    WHERE id_flag_photo = given_id;
    IF path_found IS NULL THEN
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*) INTO exists_checker
    FROM flag_photo
    WHERE path_flag_photo = given_path;
    IF exists_checker = 0 THEN
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO clone_checker
    FROM phone
    WHERE number_phone = given_num;

    IF clone_checker > 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM phone
    WHERE number_phone = given_num;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Phone Entered';
    ELSE
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
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    -- Check if the gender already exists
    SELECT COUNT(*) INTO clone_checker
    FROM gender
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM gender
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Gender Entered';
    ELSE
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
    SELECT id_gender, name
    FROM gender;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_gender; 
CREATE PROCEDURE remove_gender(IN given_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM gender
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Gender Entered';
    ELSE
        DELETE FROM gender
        WHERE name = given_name;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_gender_name; 
CREATE PROCEDURE get_id_by_gender_name(IN given_name VARCHAR(25), OUT id_found INT)
BEGIN
    SET id_found = 0;

    SELECT id_gender INTO id_found
    FROM gender
    WHERE name = given_name;

    IF id_found IS NULL THEN
        SET id_found = 0; 
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*) INTO clone_checker
    FROM identification_type
    WHERE name_identification_type = given_name;
    IF clone_checker > 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Identification type already registered in DB';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM identification_type
    WHERE name_identification_type = given_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
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
    SELECT id_identification_type, name_identification_type
    FROM identification_type;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_identification_type_name; 
CREATE PROCEDURE get_id_by_identification_type_name(IN given_name VARCHAR(25), OUT id_found INT)
BEGIN
    SET id_found = 0;

    SELECT id_identification_type INTO id_found
    FROM identification_type
    WHERE name_identification_type = given_name;

    IF id_found IS NULL THEN
        SET id_found = 0; 
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS remove_identification_type; 
CREATE PROCEDURE remove_identification_type(IN given_name VARCHAR(25))
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO exists_checker
    FROM identification_type
    WHERE name_identification_type = given_name;

    IF exists_checker = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unregistered Identification Type Entered';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*)
    INTO clone_checker
    FROM category
    WHERE title = given_title
      AND gender = given_gender;

    IF clone_checker > 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category already registered';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*)
    INTO exists_checker
    FROM category
    WHERE title = given_title
      AND gender = given_gender
      AND quantity = given_quantity;

    IF exists_checker = 0 THEN
		ROLLBACK;
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
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END CASE;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_categories;
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
    SET category_id = 0;

    SELECT id_category INTO category_id
    FROM category
    WHERE title = category_title
      AND gender = category_gender
      AND quantity = category_quantity;

    IF category_id IS NULL THEN
        SET category_id = 0;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS delete_category;
CREATE PROCEDURE delete_category (
    IN category_title VARCHAR(25)
)
BEGIN
    DECLARE category_id INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT id_category INTO category_id
    FROM category
    WHERE title = category_title;

    IF category_id IS NULL THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category not found';
    ELSE
        DELETE FROM event WHERE id_category = category_id;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    -- Check if the country already exists
    SELECT COUNT(*) INTO clone_checker
    FROM country
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    -- Check if the country exists
    SELECT COUNT(*) INTO exists_checker
    FROM country
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country not registered';
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
            ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid option';
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
    SET country_id = 0;

    SELECT id_country INTO country_id
    FROM country
    WHERE name = country_name;

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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT id_country INTO country_id
    FROM country
    WHERE name = country_given;

    IF country_id IS NULL THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country not found';
    ELSE
        DELETE FROM person WHERE id_country_represents = country_id;
        
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
	CALL get_country_id(given_country_name, country_id_gotten);
    IF country_id_gotten <= 0 THEN
		ROLLBACK;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country isn´t registered';
	ELSE
		SELECT COUNT(*) INTO clone_checker
		FROM province
		WHERE name = given_name AND id_country = country_id_gotten;
        IF clone_checker > 0 THEN
			ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    -- Get the province ID based on the province and country names
    CALL get_province_id(province_name, country_name, province_id_gotten);

    -- Check if the province exists
    IF province_id_gotten = 0 THEN
		ROLLBACK;
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
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country is not registered';
            ELSE
                UPDATE province SET name = new_name, id_country = country_id_gotten WHERE id_province = province_id_gotten;
            END IF;

        ELSE
			ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    CALL get_province_id(province_name, country_name, province_id_gotten);

    IF province_id_gotten = 0 THEN
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    CALL get_province_id(given_province_name, given_country_name, province_id_gotten);
    IF province_id_gotten > 0 THEN
		SELECT COUNT(*) INTO clone_checker
		FROM region
		WHERE name = given_name AND id_province = province_id_gotten;
		IF clone_checker > 0 THEN
			ROLLBACK;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Region already registered';
		ELSE 
			INSERT INTO region(name, id_province)
			VALUES(given_name, province_id_gotten);
		END IF;
    ELSE
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SET region_id = 0;

    CALL get_province_id(province_name, country_name, province_id);

    IF province_id > 0 THEN
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    CALL get_region_id(region_name, province_name, country_name, region_id_gotten);

    IF region_id_gotten = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Region is not registered in the given province';
    ELSE
        IF choice = 'name' THEN
            UPDATE region SET name = new_name WHERE id_region = region_id_gotten;
        
        ELSEIF choice = 'province' THEN
            CALL get_province_id(new_province, country_name, new_province_id);
            IF new_province_id = 0 THEN
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New province is not registered';
            ELSE
                UPDATE region SET id_province = new_province_id WHERE id_region = region_id_gotten;
            END IF;

        ELSEIF choice = 'both' THEN
            CALL get_province_id(new_province, country_name, new_province_id);
            IF new_province_id = 0 THEN
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New province is not registered';
            ELSE
                UPDATE region SET name = new_name, id_province = new_province_id WHERE id_region = region_id_gotten;
            END IF;
        ELSE
			ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    CALL get_region_id(region_name, province_name, country_name, region_id_gotten);

    IF region_id_gotten = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Province is not registered in the given country';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    
    CALL get_region_id(given_region_name, given_province_name, given_country_name, region_id_gotten);
    IF region_id_gotten > 0 THEN
		SELECT COUNT(*) INTO clone_checker
		FROM district
		WHERE name = given_name AND id_region = region_id_gotten;
		IF clone_checker > 0 THEN
			ROLLBACK;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'District already registered';
		ELSE 
			INSERT INTO district(name, id_region)
			VALUES(given_name, region_id_gotten);
		END IF;
    ELSE
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    CALL get_district_id(district_name, region_name, province_name, country_name, district_id_gotten);

    IF district_id_gotten = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'District is not registered in the given region';
    ELSE
        IF choice = 'name' THEN
            UPDATE district SET name = new_name WHERE id_district = district_id_gotten;
        
        ELSEIF choice = 'region' THEN
            CALL get_region_id(new_region, province_name, country_name, new_region_id);
            IF new_region_id = 0 THEN
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New region is not registered';
            ELSE
                UPDATE district SET id_region = new_region_id WHERE id_district = district_id_gotten;
            END IF;

        ELSEIF choice = 'both' THEN
            CALL get_region_id(new_region, province_name, country_name, new_region_id);
            IF new_region_id = 0 THEN
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New region is not registered';
            ELSE
				UPDATE district SET name = new_name, id_region = new_region_id WHERE id_district = district_id_gotten;
            END IF;
        ELSE
			ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    CALL get_district_id(district_name, region_name, province_name, country_name, district_id_gotten);

    IF district_id_gotten = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'District is not registered in the given region';
    ELSE
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO clone_checker
    FROM role
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO exists_checker
    FROM role
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*) INTO exists_checker
    FROM role
    WHERE name = given_name;

    IF exists_checker = 0 THEN
		ROLLBACK;
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
    SET id_found = 0;

    SELECT id_role INTO id_found
    FROM role
    WHERE name = given_name;

    IF id_found IS NULL THEN
        SET id_found = 0;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
        
    SELECT COUNT(*)
    INTO email_checker
    FROM email
    WHERE email_direction = new_emaildir
    AND id_person = person_id_given;
    
    IF email_checker > 0 THEN
		ROLLBACK;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*)
    INTO clone_checker
    FROM trainer
    WHERE id_trainer = person_id_given;
    IF clone_checker > 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Trainer already registered';
    ELSE
        -- If not registered, insert the new trainer
        INSERT INTO trainer (id_trainer)
        VALUES (person_id_given);
        -- You don't need to commit as it's implicit in a stored procedure
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_trainers; 
CREATE PROCEDURE get_all_trainers()
BEGIN
    SELECT 
        t.id_trainer,
        p.first_name,
        p.last_name
    FROM 
        trainer t
    INNER JOIN 
        person p ON t.id_trainer = p.id_person;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_trainer_id_by_identification_number; 
CREATE PROCEDURE get_trainer_id_by_identification_number(
    IN input_identification_number INT,
    OUT output_trainer_id INT
)
BEGIN
    SELECT t.id_trainer
    INTO output_trainer_id
    FROM trainer t
    JOIN person p ON t.id_trainer = p.id_person
    WHERE p.identification_number = input_identification_number;
    
    IF output_trainer_id IS NULL THEN
        SET output_trainer_id = 0;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
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
			ROLLBACK;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Username already taken';
        ELSE
            CALL get_id_by_role_name(role_given, id_role_g);
            IF id_role_g = 0 THEN
				ROLLBACK;
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
    SELECT 
        up.id_user,
        p.first_name,
        p.last_name,
        up.username
    FROM 
        user_person up
    INNER JOIN 
        person p ON up.id_user = p.id_person;
END //
DELIMITER ;

# Procedimientos/Funciones de Athlete
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_athlete; 
CREATE PROCEDURE register_athlete(
    IN person_id_given INT,
    IN trainer_id_given INT
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred on register athlete, transaction rolled back';
    END;

    SELECT COUNT(*)
    INTO clone_checker
    FROM athlete
    WHERE id_athlete = person_id_given;

    IF clone_checker > 0 THEN
         ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Athlete already registered';
    ELSE
        -- Insert new athlete record
        INSERT INTO athlete (id_athlete, id_trainer)
        VALUES (person_id_given, trainer_id_given);
        
        -- Optionally, output a success message
        SELECT 'Athlete registered successfully' AS message;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_athletes; 
CREATE PROCEDURE get_all_athletes()
BEGIN
    SELECT 
    a.id_athlete,
    p.first_name AS athlete_first_name,
    p.last_name AS athlete_last_name,
    c.name AS country_name,
    pt.first_name AS trainer_first_name,
    pt.last_name AS trainer_last_name
	FROM 
    athlete a
	JOIN 
    person p ON a.id_athlete = p.id_person
	LEFT JOIN 
    trainer t ON a.id_trainer = t.id_trainer
	LEFT JOIN 
    person pt ON t.id_trainer = pt.id_person
	LEFT JOIN
    country c ON p.id_country_represents = c.id_country;

END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_athlete_id_by_identification_number; 
CREATE PROCEDURE get_athlete_id_by_identification_number(
    IN input_identification_number INT,
    OUT output_athlete_id INT
)
BEGIN
    SELECT a.id_athlete
    INTO output_athlete_id
    FROM athlete a
    JOIN person p ON a.id_athlete = p.id_person
    WHERE p.identification_number = input_identification_number;
    
    IF output_athlete_id IS NULL THEN
        SET output_athlete_id = 0;
    END IF;
END //
DELIMITER ;

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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO clone_checker
    FROM person
    WHERE identification_number = idnumber_given;

    IF clone_checker > 0 THEN
		ROLLBACK;
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
			ROLLBACK;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid foreign key data';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS register_person_trainer;
CREATE PROCEDURE register_person_trainer(
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
    IN email_given VARCHAR(50)
)
BEGIN

    DECLARE clone_checker INT DEFAULT 0;
    DECLARE gender_id INT DEFAULT 0;
    DECLARE nationality_id INT DEFAULT 0;
    DECLARE country_id INT DEFAULT 0;
    DECLARE idtype_id INT DEFAULT 0;
    DECLARE phone_id INT DEFAULT 0;
    DECLARE current_person_id INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred on Person-Trainerphone, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*) INTO clone_checker
    FROM person
    WHERE identification_number = idnumber_given;

    IF clone_checker > 0 THEN
        ROLLBACK;
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
            CALL register_trainer(current_person_id);

        ELSE
            ROLLBACK;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid foreign key data';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS register_person_athlete;
CREATE PROCEDURE register_person_athlete(
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
    IN trainer_id INT
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE gender_id INT DEFAULT 0;
    DECLARE nationality_id INT DEFAULT 0;
    DECLARE country_id INT DEFAULT 0;
    DECLARE idtype_id INT DEFAULT 0;
    DECLARE phone_id INT DEFAULT 0;
    DECLARE current_person_id INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred during registration, transaction rolled back';
    END;

    START TRANSACTION;

    SELECT COUNT(*)
    INTO clone_checker
    FROM person
    WHERE identification_number = idnumber_given;

    IF clone_checker > 0 THEN
        ROLLBACK;
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

            -- Insert related records
            CALL register_person_nationality(current_person_id, nationality_id);
            CALL register_photo(path_given, current_person_id);
            CALL register_email(email_given, current_person_id);
            
            -- Handle phone registration
            CALL get_id_by_phone_number(phone_number_given, phone_id);
            IF phone_id = 0 THEN
                CALL register_phone(phone_number_given);
                CALL get_id_by_phone_number(phone_number_given, phone_id);
            END IF;
            CALL register_person_phone(current_person_id, phone_id);
            
            CALL register_athlete(current_person_id, trainer_id);
        ELSE
            ROLLBACK;
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

# Procedimientos/Funciones de Sport
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_sport; 
CREATE PROCEDURE register_sport(
	IN given_name VARCHAR(50),
    IN given_desc VARCHAR(200),
    IN given_rules VARCHAR(200)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred on registering sport, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*) INTO clone_checker
    FROM sport
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sport already registered on DB';
    ELSE
        -- Insertar la nueva nacionalidad
        INSERT INTO sport(name, description_sport, rules)
        VALUES (given_name, given_desc, given_rules);
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_sport_name; 
CREATE PROCEDURE get_id_by_sport_name(IN given_sport VARCHAR(50), OUT id_found INT)
BEGIN
    SET id_found = 0;
    SELECT id_sport INTO id_found
    FROM sport
    WHERE name = given_sport;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_sport; 
CREATE PROCEDURE update_sport (
    IN choice VARCHAR(25),
    IN given_name VARCHAR(50),
    IN new_name VARCHAR(50),
    IN new_desc VARCHAR(200),
    IN new_rules VARCHAR(200)
)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;

    SELECT COUNT(*)
    INTO exists_checker
    FROM sport
    WHERE name = given_name;

    IF exists_checker = 0 THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sport not registered';
    ELSE
        CASE choice
            WHEN 'name' THEN
                UPDATE sport
                SET name = new_name
                WHERE name = given_name;
            WHEN 'description' THEN
                UPDATE sport
                SET description_sport = new_desc
                WHERE name = given_name;
            WHEN 'rules' THEN
                UPDATE sport
                SET rules = new_rules
                WHERE name = given_name;
            WHEN 'all' THEN
                UPDATE sport
                SET name = new_name,
                    description_sport = new_desc,
                    rules = new_rules
                WHERE name = given_name;
            ELSE
				ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END CASE;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_sports; 
CREATE PROCEDURE get_all_sports()
BEGIN
    -- Seleccionar id y nombre de todas las nacionalidades
    SELECT id_sport, name, description_sport, rules
    FROM sport;
END //
DELIMITER ;

# Procedimientos/Funciones de Person-Olympic
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_olympic; 
CREATE PROCEDURE register_olympic(
    IN given_name VARCHAR(50),
    IN given_year INT,
    IN given_country VARCHAR(50)
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE id_found_country INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred on registering olympic, transaction rolled back';
    END;
    
    START TRANSACTION;
    
    -- Check if an Olympic event already exists for the given year
    SELECT COUNT(*) INTO clone_checker
    FROM olympic
    WHERE year = given_year;

    IF clone_checker > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Olympic already registered on DB';
    ELSE
        -- Fetch the ID of the given country name
        CALL get_country_id(given_country, id_found_country);
        
        IF id_found_country > 0 THEN
            INSERT INTO olympic(name, year, id_country)
            VALUES (given_name, given_year, id_found_country);
        ELSE 
            ROLLBACK;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Country is not registered on DB';
        END IF;
    END IF;
    
    COMMIT;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_olympics; 
CREATE PROCEDURE get_all_olympics()
BEGIN
    -- Seleccionar id y nombre de todas las nacionalidades
    SELECT o.id_olympic, o.name AS olympic_name, o.year, c.name AS country_name
	FROM olympic o
	INNER JOIN country c ON o.id_country = c.id_country;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_id_by_olympic_year; 
CREATE PROCEDURE get_id_by_olympic_year(IN given_year INT, OUT id_found INT)
BEGIN
    SET id_found = 0;
    SELECT id_olympic INTO id_found
    FROM olympic
    WHERE year = given_year;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_olympic; 
CREATE PROCEDURE update_olympic (
    IN choice VARCHAR(25),
    IN given_year INT,
    IN new_name VARCHAR(100),
    IN new_country INT,
    IN new_year INT
)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    DECLARE year_conflict_checker INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;

    START TRANSACTION;

    -- Check if the Olympic event with the given year exists
    SELECT COUNT(*)
    INTO exists_checker
    FROM olympic
    WHERE year = given_year;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Olympic event not registered';
    ELSE
        CASE choice
            WHEN 'name' THEN
                -- Update only the name
                UPDATE olympic
                SET name = new_name
                WHERE year = given_year;
            WHEN 'country' THEN
                -- Update only the country
                UPDATE olympic
                SET id_country = new_country
                WHERE year = given_year;
            WHEN 'year' THEN
                -- Check if the new year already exists to maintain uniqueness
                SELECT COUNT(*)
                INTO year_conflict_checker
                FROM olympic
                WHERE year = new_year;

                IF year_conflict_checker > 0 THEN
                    ROLLBACK;
                    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Year already exists for another Olympic event';
                ELSE
                    -- Update only the year
                    UPDATE olympic
                    SET year = new_year
                    WHERE year = given_year;
                END IF;
            WHEN 'all' THEN
                -- Update name, country, and year
                SELECT COUNT(*)
                INTO year_conflict_checker
                FROM olympic
                WHERE year = new_year;

                IF year_conflict_checker > 0 THEN
                    ROLLBACK;
                    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Year already exists for another Olympic event';
                ELSE
                    UPDATE olympic
                    SET name = new_name,
                        id_country = new_country,
                        year = new_year
                    WHERE year = given_year;
                END IF;
            ELSE
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END CASE;
    END IF;

    COMMIT;
END //
DELIMITER ;

# Procedimientos/Funciones de team
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_team; 
CREATE PROCEDURE register_team(
    IN given_name VARCHAR(25),
    IN given_id_trainer_num INT
)
BEGIN
    DECLARE clone_checker INT DEFAULT 0;
    DECLARE given_id_trainer INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred on registering team, transaction rolled back';
    END;

    START TRANSACTION;
	
    
    -- Check if the team name already exists
    SELECT COUNT(*) INTO clone_checker
    FROM team
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Team already registered in the database';
    ELSE
		CALL get_trainer_id_by_identification_number(given_id_trainer_num, given_id_trainer);
        INSERT INTO team(name, id_trainer)
        VALUES (given_name, given_id_trainer);
    END IF;

    COMMIT;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_teams; 
CREATE PROCEDURE get_all_teams()
BEGIN
    SELECT t.id_team, 
           t.name AS team_name, p.first_name, p.last_name
    FROM team t
    INNER JOIN trainer tr ON t.id_trainer = tr.id_trainer
    INNER JOIN person p ON tr.id_trainer = p.id_person;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_team_id_by_name; 
CREATE PROCEDURE get_team_id_by_name(
    IN input_team_name VARCHAR(25),
    OUT output_team_id INT
)
BEGIN
    -- Select the team ID based on the given name
    SELECT id_team
    INTO output_team_id
    FROM team
    WHERE name = input_team_name;
    
    -- If no team is found, set the output to 0
    IF output_team_id IS NULL THEN
        SET output_team_id = 0;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_country_of_team; 
CREATE PROCEDURE get_country_of_team(
    IN team_name VARCHAR(25),
    OUT country_name VARCHAR(50)
)
BEGIN
    SELECT c.name INTO country_name
    FROM team t
    INNER JOIN trainer tr ON t.id_trainer = tr.id_trainer
    INNER JOIN person p ON tr.id_trainer = p.id_person
    INNER JOIN country c ON p.id_country_represents = c.id_country
    WHERE t.name = team_name;
    
    IF country_name IS NULL THEN
        SET country_name = 'Country not found';
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS update_team;
CREATE PROCEDURE update_team (
    IN choice VARCHAR(25),
    IN given_team_name VARCHAR(25),
    IN new_team_name VARCHAR(25),
    IN new_trainer_identification_number INT
)
BEGIN
    DECLARE exists_checker INT DEFAULT 0;
    DECLARE new_trainer_id INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;

    START TRANSACTION;

    -- Check if the team exists
    SELECT COUNT(*)
    INTO exists_checker
    FROM team
    WHERE name = given_team_name;

    IF exists_checker = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Team not registered';
    ELSE
        CASE choice
            WHEN 'name' THEN
                -- Update the team's name
                UPDATE team
                SET name = new_team_name
                WHERE name = given_team_name;
                
            WHEN 'trainer' THEN
                -- Use the identification number to retrieve the new trainer's ID
                CALL get_trainer_id_by_identification_number(new_trainer_identification_number, new_trainer_id);

                -- Check if the trainer ID was found
                IF new_trainer_id = 0 THEN
                    ROLLBACK;
                    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Trainer with given identification number not found';
                ELSE
                    -- Update the team's trainer
                    UPDATE team
                    SET id_trainer = new_trainer_id
                    WHERE name = given_team_name;
                END IF;
                
            ELSE
                -- Invalid choice
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid choice for update operation';
        END CASE;
    END IF;

    COMMIT;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_teams;
CREATE PROCEDURE get_all_teams()
BEGIN
    SELECT 
        t.name AS team_name,
        c.name AS country_name,
        CONCAT(trainer_person.first_name, ' ', trainer_person.last_name) AS trainer_name,
        GROUP_CONCAT(CONCAT(athlete_person.first_name, ' ', athlete_person.last_name) ORDER BY athlete_person.last_name SEPARATOR ', ') AS athlete_names
    FROM 
        team t
    JOIN 
        trainer tr ON t.id_trainer = tr.id_trainer
    JOIN 
        person trainer_person ON tr.id_trainer = trainer_person.id_person
    LEFT JOIN 
        country c ON trainer_person.id_country_represents = c.id_country
    LEFT JOIN 
        athlete_team at ON t.id_team = at.id_team
    LEFT JOIN 
        athlete a ON at.id_athlete = a.id_athlete
    LEFT JOIN 
        person athlete_person ON a.id_athlete = athlete_person.id_person
    GROUP BY 
        t.id_team, t.name, c.name, trainer_name
    ORDER BY 
        team_name;
END //
DELIMITER ;

#
#
DELIMITER //
DROP PROCEDURE IF EXISTS register_athlete_to_team; 
CREATE PROCEDURE register_athlete_to_team(
    IN given_athlete_id_num INT,
    IN given_team VARCHAR(50)
)
BEGIN
    DECLARE trainer_country_id INT;
    DECLARE athlete_country_id INT;
	DECLARE given_team_id INT;
    DECLARE given_athlete_id INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;

    START TRANSACTION;
    
    CALL get_athlete_id_by_identification_number(given_athlete_id_num, given_athlete_id);
    CALL get_team_id_by_name(given_team, given_team_id);
    select given_athlete_id;
    select given_team_id;
    -- Fetch the country ID of the trainer for the specified team
    SELECT p.id_country_represents
    INTO trainer_country_id
    FROM team t
    JOIN trainer tr ON t.id_trainer = tr.id_trainer
    JOIN person p ON tr.id_trainer = p.id_person
    WHERE t.id_team = given_team_id;

    -- Fetch the country ID of the athlete
    SELECT p.id_country_represents
    INTO athlete_country_id
    FROM athlete a
    JOIN person p ON a.id_athlete = p.id_person
    WHERE a.id_athlete = given_athlete_id;

    -- Check if the athlete's country matches the trainer's country
    IF trainer_country_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Team not found or trainer does not exist.';
    ELSEIF athlete_country_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Athlete not found.';
    ELSEIF trainer_country_id != athlete_country_id THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Athlete and trainer represent different countries.';
    ELSE
        -- Insert the athlete-team relationship
        INSERT INTO athlete_team (id_athlete, id_team)
        VALUES (given_athlete_id, given_team_id);
    END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Medal 
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_event_medal; 
CREATE PROCEDURE register_event_medal(
    IN given_event INT,  -- Assuming id_event is an INT in MySQL
    IN given_medal INT   -- Assuming id_medal is an INT in MySQL
)
BEGIN
    DECLARE clone_checker INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- Rollback on error
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error registering event medal.';
    END;

    START TRANSACTION;

    -- Check if the event-medal combination is already registered
    SELECT COUNT(*)
    INTO clone_checker
    FROM event_medal
    WHERE id_event = given_event
    AND id_medal = given_medal;

    IF clone_checker > 0 THEN
        -- Handle the case where the combination already exists
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Event-Medal already registered.';
    ELSE
        -- Insert the new event-medal relationship
        INSERT INTO event_medal (id_event, id_medal)
        VALUES (given_event, given_medal);
    END IF;

    COMMIT;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_medal_id_by_event; 
CREATE PROCEDURE get_medal_id_by_event (
    IN event_id INT,
    IN medal_name VARCHAR(25),
    OUT medal_id INT
)
BEGIN
    -- Busca el id de la medalla en base al evento y al nombre de la medalla
    SELECT m.id_medal 
    INTO medal_id
    FROM medal m
    JOIN event_medal em ON em.id_medal = m.id_medal
    WHERE em.id_event = event_id AND m.name = medal_name;
END //
DELIMITER ;

# Procedimientos/Funciones de Event 
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_event;
CREATE PROCEDURE register_event(
    IN date_given VARCHAR(10),        -- Expected format: 'DD-MM-YYYY'
    IN time_given VARCHAR(8),         -- Expected format: 'HH:MM:SS'
    IN name_given VARCHAR(50),        
    IN year_olympic_given INT,    
    IN sport_name VARCHAR(50),
    IN category_title VARCHAR(25),
    IN category_gender VARCHAR(25),
    IN category_quantity VARCHAR(25)
)
BEGIN
    DECLARE id_event_current INT;
    DECLARE id_category_given INT;
	DECLARE id_olympic_given INT;
    DECLARE sport_name_given INT;
    DECLARE g_medal_id INT;
    DECLARE s_medal_id INT;
    DECLARE b_medal_id INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error registering event.';
    END;
    START TRANSACTION;
    
    CALL get_category_id_by_details(category_title, category_gender, category_quantity, id_category_given);
	CALL get_id_by_olympic_year(year_olympic_given, id_olympic_given);
    CALL get_id_by_sport_name(sport_name, sport_name_given);

    INSERT INTO event (date_event, time_event, name, id_olympic, id_sport, id_category)
    VALUES (STR_TO_DATE(date_given, '%d-%m-%Y'), TIME_FORMAT(time_given, '%H:%i:%s'), name_given, id_olympic_given, sport_name_given, id_category_given);
    SET id_event_current = LAST_INSERT_ID();

    -- Register medals for the event (gold, silver, bronze)
    INSERT INTO medal (name) VALUES ('gold');
    SET g_medal_id = LAST_INSERT_ID();

    INSERT INTO medal (name) VALUES ('silver');
    SET s_medal_id = LAST_INSERT_ID();

    INSERT INTO medal (name) VALUES ('bronze');
    SET b_medal_id = LAST_INSERT_ID();

    -- Register each medal with the event
    CALL register_event_medal(id_event_current, g_medal_id);
    CALL register_event_medal(id_event_current, s_medal_id);
    CALL register_event_medal(id_event_current, b_medal_id);

    COMMIT;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_events; 
CREATE PROCEDURE get_all_events()
BEGIN
    SELECT e.id_event AS id,
			e.name AS event_name, 
			c.title AS category_title, 
			o.name AS olympic_name
    FROM event e
    JOIN category c ON e.id_category = c.id_category
    JOIN olympic o ON e.id_olympic = o.id_olympic;
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_nationality
    WHERE id_person = person_id_given
    AND id_nationality = nationality_id_given;
    IF clone_checker > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This nationality for the sleceted person is alrady registered';
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred, transaction rolled back';
    END;
    
    START TRANSACTION;
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_phone
    WHERE id_person = person_id_given
    AND id_phone = phone_id_given;
    IF clone_checker > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Phone already registered for this person';
    ELSE
        INSERT INTO person_phone (id_person, id_phone)
        VALUES (person_id_given, phone_id_given);
	END IF;
END //
DELIMITER ;

# Procedimientos/Funciones de Medal-Team
#----------------------------------------#
DELIMITER //
DROP PROCEDURE IF EXISTS register_medal_for_team;
CREATE PROCEDURE register_medal_for_team(
    IN team_id INT, 
    IN medal_id INT
)
BEGIN
    INSERT INTO team_medal (id_team, id_medal, created_at, updated_at)
    VALUES (team_id, medal_id, NOW(), NOW());
END //
DELIMITER ;

#
#
DELIMITER //
DROP PROCEDURE IF EXISTS add_team_to_event;
CREATE PROCEDURE add_team_to_event (
    IN given_team_id INT,
    IN given_event_id INT,
    IN given_score DECIMAL(10, 2),
    IN given_record DECIMAL(10, 2),
    IN given_position INT
)
BEGIN
    DECLARE medal_id INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'An error occurred in add_team_to_event.';
    END;
    START TRANSACTION;
    INSERT INTO team_event (id_team, id_event, save_score, record, position)
    VALUES (given_team_id, given_event_id, given_score, given_record, given_position);

    -- Award medals based on position
    IF given_position = 1 THEN
        CALL get_medal_id_by_event(given_event_id, 'gold', medal_id);
        CALL register_medal_for_team(given_team_id, medal_id);
    ELSEIF given_position = 2 THEN
        CALL get_medal_id_by_event(given_event_id, 'silver', medal_id);
        CALL register_medal_for_team(given_team_id, medal_id);
    ELSEIF given_position = 3 THEN
        CALL get_medal_id_by_event(given_event_id, 'bronze', medal_id);
        CALL register_medal_for_team(given_team_id, medal_id);
    END IF;

    COMMIT;
END //
DELIMITER ;

# STATS
#
DELIMITER //
DROP PROCEDURE IF EXISTS get_age_statistics;
CREATE PROCEDURE get_age_statistics()
BEGIN
    DECLARE count_18_25 INT DEFAULT 0;
    DECLARE count_25_35 INT DEFAULT 0;
    DECLARE count_35_45 INT DEFAULT 0;
    DECLARE count_above_45 INT DEFAULT 0;

    -- Calcular la cantidad de atletas en cada rango de edad y almacenar en variables
    SELECT 
        SUM(CASE WHEN TIMESTAMPDIFF(YEAR, p.birth_date, CURDATE()) BETWEEN 18 AND 25 THEN 1 ELSE 0 END),
        SUM(CASE WHEN TIMESTAMPDIFF(YEAR, p.birth_date, CURDATE()) BETWEEN 26 AND 35 THEN 1 ELSE 0 END),
        SUM(CASE WHEN TIMESTAMPDIFF(YEAR, p.birth_date, CURDATE()) BETWEEN 36 AND 45 THEN 1 ELSE 0 END),
        SUM(CASE WHEN TIMESTAMPDIFF(YEAR, p.birth_date, CURDATE()) > 45 THEN 1 ELSE 0 END)
    INTO 
        count_18_25, count_25_35, count_35_45, count_above_45
    FROM 
        person p
    INNER JOIN 
        athlete a ON p.id_person = a.id_athlete; -- Asegúrate de que solo cuentas atletas

    -- Devolver los resultados
    SELECT 
        '18-25' AS age_range, count_18_25 AS athlete_count
    UNION ALL
    SELECT 
        '26-35', count_25_35
    UNION ALL
    SELECT 
        '36-45', count_35_45
    UNION ALL
    SELECT 
        '45+', count_above_45;
END //
DELIMITER ;


# Views
#
DELIMITER //
DROP PROCEDURE IF EXISTS get_participants_filter; 
CREATE PROCEDURE get_participants_filter (
    IN filter_type VARCHAR(25),
    IN filter_value VARCHAR(50),
    IN year_value INT
)
BEGIN
    -- Selecciona a los atletas y entrenadores registrados en equipos de eventos, filtrando por deporte o año si se proporciona.
    IF filter_type = 'sport' AND filter_value IS NOT NULL THEN
        -- Filtrar por nombre de deporte
        SELECT 
            e.name AS event_name,
            s.name AS sport_name,
            o.year AS olympic_year,
            t.name AS team_name,
            p.first_name AS participant_first_name,
            p.last_name AS participant_last_name,
            CASE 
                WHEN a.id_athlete IS NOT NULL THEN 'Athlete'
                WHEN tr.id_trainer IS NOT NULL THEN 'Trainer'
            END AS role
        FROM 
            event e
        JOIN 
            sport s ON e.id_sport = s.id_sport
        JOIN 
            olympic o ON e.id_olympic = o.id_olympic
        JOIN 
            team_event te ON e.id_event = te.id_event
        JOIN 
            team t ON te.id_team = t.id_team
        LEFT JOIN 
            athlete_team at ON t.id_team = at.id_team
        LEFT JOIN 
            athlete a ON at.id_athlete = a.id_athlete
        LEFT JOIN 
            person p ON (a.id_athlete = p.id_person OR t.id_trainer = p.id_person)
        LEFT JOIN 
            trainer tr ON t.id_trainer = tr.id_trainer
        WHERE 
            s.name = filter_value;
    
    ELSEIF filter_type = 'year' AND year_value IS NOT NULL THEN
        -- Filtrar por año olímpico
        SELECT 
            e.name AS event_name,
            s.name AS sport_name,
            o.year AS olympic_year,
            t.name AS team_name,
            p.first_name AS participant_first_name,
            p.last_name AS participant_last_name,
            CASE 
                WHEN a.id_athlete IS NOT NULL THEN 'Athlete'
                WHEN tr.id_trainer IS NOT NULL THEN 'Trainer'
            END AS role
        FROM 
            event e
        JOIN 
            sport s ON e.id_sport = s.id_sport
        JOIN 
            olympic o ON e.id_olympic = o.id_olympic
        JOIN 
            team_event te ON e.id_event = te.id_event
        JOIN 
            team t ON te.id_team = t.id_team
        LEFT JOIN 
            athlete_team at ON t.id_team = at.id_team
        LEFT JOIN 
            athlete a ON at.id_athlete = a.id_athlete
        LEFT JOIN 
            person p ON (a.id_athlete = p.id_person OR t.id_trainer = p.id_person)
        LEFT JOIN 
            trainer tr ON t.id_trainer = tr.id_trainer
        WHERE 
            o.year = year_value;
    ELSE
        -- Si no hay filtro, devolver todos los participantes de los eventos
        SELECT 
            e.name AS event_name,
            s.name AS sport_name,
            o.year AS olympic_year,
            t.name AS team_name,
            p.first_name AS participant_first_name,
            p.last_name AS participant_last_name,
            CASE 
                WHEN a.id_athlete IS NOT NULL THEN 'Athlete'
                WHEN tr.id_trainer IS NOT NULL THEN 'Trainer'
            END AS role
        FROM 
            event e
        JOIN 
            sport s ON e.id_sport = s.id_sport
        JOIN 
            olympic o ON e.id_olympic = o.id_olympic
        JOIN 
            team_event te ON e.id_event = te.id_event
        JOIN 
            team t ON te.id_team = t.id_team
        LEFT JOIN 
            athlete_team at ON t.id_team = at.id_team
        LEFT JOIN 
            athlete a ON at.id_athlete = a.id_athlete
        LEFT JOIN 
            person p ON (a.id_athlete = p.id_person OR t.id_trainer = p.id_person)
        LEFT JOIN 
            trainer tr ON t.id_trainer = tr.id_trainer;
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_medal_ranking_by_olympic;
CREATE PROCEDURE get_medal_ranking_by_olympic(
    IN olympic_name VARCHAR(100)
)
BEGIN
    SELECT 
        fp.path_flag_photo AS flag,
        c.name AS country,
        SUM(CASE WHEN m.name = 'gold' THEN 1 ELSE 0 END) AS gold_medals,
        SUM(CASE WHEN m.name = 'silver' THEN 1 ELSE 0 END) AS silver_medals,
        SUM(CASE WHEN m.name = 'bronze' THEN 1 ELSE 0 END) AS bronze_medals,
        COUNT(m.id_medal) AS total_medals
    FROM 
        olympic o
    JOIN 
        event e ON o.id_olympic = e.id_olympic
    JOIN 
        event_medal em ON e.id_event = em.id_event
    JOIN 
        medal m ON em.id_medal = m.id_medal
    JOIN 
        country c ON o.id_country = c.id_country
    LEFT JOIN 
        flag_photo fp ON c.id_flag_photo = fp.id_flag_photo
    WHERE 
        olympic_name IS NULL OR o.name = olympic_name
    GROUP BY 
        c.id_country
    ORDER BY 
        gold_medals DESC,
        silver_medals DESC,
        bronze_medals DESC,
        total_medals DESC;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_event_list;
CREATE PROCEDURE get_event_list(
    IN choice VARCHAR(25),
    IN olympic_name VARCHAR(100),
    IN sport_name VARCHAR(50)
)
BEGIN
    SELECT 
        e.id_event AS event_id,
        e.name AS event_name,
        o.name AS olympic_name,
        s.name AS sport_name,
        e.date_event AS event_date,
        e.time_event AS event_time,
        GROUP_CONCAT(CONCAT(p.first_name, ' ', p.last_name) ORDER BY p.last_name ASC SEPARATOR ', ') AS participants
    FROM 
        event e
    JOIN 
        olympic o ON e.id_olympic = o.id_olympic
    JOIN 
        sport s ON e.id_sport = s.id_sport
    LEFT JOIN 
        team_event te ON e.id_event = te.id_event
    LEFT JOIN 
        athlete_team at ON te.id_team = at.id_team
    LEFT JOIN 
        athlete a ON at.id_athlete = a.id_athlete
    LEFT JOIN 
        person p ON a.id_athlete = p.id_person
    WHERE
        (choice = 'all') OR
        (choice = 'olympic' AND o.name = olympic_name) OR
        (choice = 'sport' AND s.name = sport_name) OR
        (choice = 'both' AND o.name = olympic_name AND s.name = sport_name)
    GROUP BY 
        e.id_event
    ORDER BY 
        o.name, e.date_event, e.time_event;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS get_top_scores_by_sport;
CREATE PROCEDURE get_top_scores_by_sport()
BEGIN
    SELECT 
        CONCAT(p.first_name, ' ', p.last_name) AS competitor_name,
        s.name AS sport_name,
        c.name AS country_name,
        te.record AS score,
        o.name AS olympic_name
    FROM 
        team_event te
    JOIN 
        team t ON te.id_team = t.id_team
    JOIN 
        athlete_team at ON t.id_team = at.id_team
    JOIN 
        athlete a ON at.id_athlete = a.id_athlete
    JOIN 
        person p ON a.id_athlete = p.id_person
    JOIN 
        country c ON p.id_country_represents = c.id_country
    JOIN 
        event e ON te.id_event = e.id_event
    JOIN 
        sport s ON e.id_sport = s.id_sport
    JOIN 
        olympic o ON e.id_olympic = o.id_olympic
    WHERE 
        te.record IS NOT NULL
    ORDER BY 
        s.name, te.record DESC
    LIMIT 5;
END //
DELIMITER ;
