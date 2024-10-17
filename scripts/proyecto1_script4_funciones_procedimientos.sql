--Nationality Related functions and procedures-----------------------------------

CREATE OR REPLACE PROCEDURE register_nationality
(given_name IN VARCHAR2)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM nationality
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Nacionality already registered');
        dbms_output.put_line('Nacionality already registered');
    ELSE
        INSERT INTO nationality(id_nationality, name)
        VALUES (seq_nationality.nextval, given_name);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
         dbms_output.put_line('Error registering: ' || SQLERRM);
END register_nationality;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_nationality
(given_name IN VARCHAR2, new_name IN VARCHAR2)
AS
exists_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM nationality
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Unregistered Nationality Entered');
        dbms_output.put_line('Unregistered Nationality Entered');
    ELSE
        UPDATE nationality
        SET name = new_name
        WHERE name = given_name;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar: ' || SQLERRM);
        dbms_output.put_line('Error al actualizar: ' || SQLERRM);
END update_nationality;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_nationalities
RETURN SYS_REFCURSOR
AS
    nationality_cursor SYS_REFCURSOR;
BEGIN
    OPEN nationality_cursor FOR
        SELECT name
        FROM nationality;
    RETURN nationality_cursor;
END get_all_nationalities;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_nationality_id (nationality_name IN VARCHAR2)
RETURN NUMBER
AS
    nationality_id NUMBER;
BEGIN
    nationality_id := 0;
    RETURN nationality_id;
    SELECT id_nationality
    INTO nationality_id
    FROM nationality
    WHERE name = nationality_name;
    RETURN nationality_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- If no phone is found, return 0 and print message
        dbms_output.put_line('Nationality not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving N: ' || SQLERRM);
        dbms_output.put_line('Error retrieving nationality: ' || SQLERRM);
END get_nationality_id;

--TO DO: NATIONALITY REMOVAL----

-- Flag_Photo Related functions and procedures----------------------------------

CREATE OR REPLACE FUNCTION check_flag_photo(new_flag IN VARCHAR2)
RETURN NUMBER
AS
    flag_id NUMBER;
BEGIN
    BEGIN
        SELECT id_flag_photo
        INTO flag_id
        FROM flag_photo
        WHERE pathD = new_flag;
        RETURN flag_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            INSERT INTO flag_photo(id_flag_photo, pathD)
            VALUES (seq_flag_photo.NEXTVAL, new_flag)
            RETURNING id_flag_photo INTO flag_id;
            RETURN flag_id;
    END;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error retrieving or inserting flag: ' || SQLERRM);
        RETURN NULL;
END check_flag_photo;

--Photo Related functions and procedures----------------------------------------

CREATE OR REPLACE PROCEDURE register_photo(new_photo IN VARCHAR2, person_id_given IN NUMBER)
AS
    photo_checker NUMBER;
    photo_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO photo_checker
    FROM photo
    WHERE pathD = new_photo
    AND id_person = person_id_given;
    
    IF photo_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Photo for this person already registered');
        dbms_output.put_line('Photo for this person already registered');
    ELSE
        INSERT INTO photo(id_photo, pathD, id_person)
        VALUES (seq_photo.nextval, new_photo, person_id_given);
        COMMIT;
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving or inserting photo: ' || SQLERRM);
        dbms_output.put_line('Error retrieving or inserting photo: ' || SQLERRM);
END register_photo;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_photo_id(new_photo IN VARCHAR2, person_id_given IN NUMBER)
RETURN NUMBER
AS
    photo_id NUMBER;
BEGIN
    SELECT id_photo
    INTO photo_id
    FROM photo
    WHERE pathD = new_photo
    AND id_person = person_id_given;
    RETURN photo_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Photo not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving or inserting photo: ' || SQLERRM);
        dbms_output.put_line('Error retrieving or inserting photo: ' || SQLERRM);
END get_photo_id;

--Gender Related functions and procedures---------------------------------------

CREATE OR REPLACE PROCEDURE register_gender
(given_name in VARCHAR2)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM gender
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Gender option already registered');
        dbms_output.put_line('Gender option already registered');
    ELSE 
        INSERT INTO gender(id_gender, name)
        VALUES (seq_gender.nextval, given_name);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_gender;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_gender
(given_name IN VARCHAR2, new_name IN VARCHAR2)
AS
exists_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM gender
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Unregistered Gender Entered');
        dbms_output.put_line('Unregistered Gender Entered');
    ELSE
        UPDATE gender
        SET name = new_name
        WHERE name = given_name;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar: ' || SQLERRM);
        dbms_output.put_line('Error al actualizar: ' || SQLERRM);
END update_gender;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_genders
RETURN SYS_REFCURSOR
AS
    gender_cursor SYS_REFCURSOR;
BEGIN
    OPEN gender_cursor FOR
        SELECT name
        FROM gender;
    RETURN gender_cursor;
END get_all_genders;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_gender_id (gender_name IN VARCHAR2)
RETURN NUMBER
AS
    gender_id NUMBER;
BEGIN
    SELECT id_gender
    INTO gender_id
    FROM gender
    WHERE name = gender_name;
    RETURN gender_id; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Gender not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving gender: ' || SQLERRM);
END get_gender_id;

--TO DO: GENDER REMOVAL----

--ID Type Related functions and procedures--------------------------------------

CREATE OR REPLACE PROCEDURE register_identification_type
(given_name in VARCHAR2)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM identification_type
    WHERE type_id = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'ID Type option already registered');
        dbms_output.put_line('ID Type option already registered');
    ELSE 
        INSERT INTO identification_type(id_identification_type, type_id)
        VALUES (seq_identification_type.nextval, given_name);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_identification_type;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_identification_type
(given_name IN VARCHAR2, new_name IN VARCHAR2)
AS
exists_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM identification_type
    WHERE type_id = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Unregistered ID Type Entered');
        dbms_output.put_line('Unregistered ID Type Entered');
    ELSE
        UPDATE identification_type
        SET type_id = new_name
        WHERE type_id = given_name;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar: ' || SQLERRM);
        dbms_output.put_line('Error al actualizar: ' || SQLERRM);
END update_identification_type;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_id_types
RETURN SYS_REFCURSOR
AS
    id_types_cursor SYS_REFCURSOR;
BEGIN
    OPEN id_types_cursor FOR
        SELECT type_id
        FROM identification_type;
    RETURN id_types_cursor;
END get_all_id_types;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_id_type_id (identification_type_name IN VARCHAR2)
RETURN NUMBER
AS
    identification_type_id NUMBER;
BEGIN
    SELECT id_identification_type
    INTO identification_type_id
    FROM identification_type
    WHERE type_id = identification_type_name;
    RETURN identification_type_id; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('ID Type not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving N: ' || SQLERRM);
        dbms_output.put_line('Error retrieving nationality: ' || SQLERRM);
END get_id_type_id;

--TO DO: REMOVAL------------------------------

--Phone Related functions and procedures----------------------------------------

CREATE OR REPLACE PROCEDURE register_phone
(given_number in NUMBER)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM phone
    WHERE number_phone = given_number;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Phone number already registered');
        dbms_output.put_line('Phone number already registered');
    ELSE 
        INSERT INTO phone(id_phone, number_phone)
        VALUES (seq_phone.nextval, given_number);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_phone;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_phone
(given_number IN NUMBER, new_number IN NUMBER)
AS
exists_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM phone
    WHERE number_phone = given_number;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Unregistered Phone Number Entered');
        dbms_output.put_line('Unregistered Phone Number Entered');
    ELSE
        UPDATE phone
        SET number_phone = new_number
        WHERE number_phone = given_number;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar: ' || SQLERRM);
        dbms_output.put_line('Error al actualizar: ' || SQLERRM);
END update_phone;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_phones
RETURN SYS_REFCURSOR
AS
    phone_cursor SYS_REFCURSOR;
BEGIN
    OPEN phone_cursor FOR
        SELECT number_phone
        FROM phone;
    RETURN phone_cursor;
END get_all_phones;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_phone_id (phone_given IN NUMBER)
RETURN NUMBER
AS
    phone_id NUMBER;
BEGIN
    SELECT id_phone
    INTO phone_id
    FROM phone
    WHERE number_phone = phone_given;
    RETURN phone_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Phone number not registered');
        RETURN 0;
    WHEN OTHERS THEN
        dbms_output.put_line('Error retrieving phone: ' || SQLERRM);
        RETURN NULL;
END get_phone_id;

--TO DO: REMOVAL------------------------------

--Category Related Functions and Procedures-------------------------------------

CREATE OR REPLACE PROCEDURE register_category (given_name in VARCHAR2)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM category
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Category already registered');
        dbms_output.put_line('Category already registered');
    ELSE 
        INSERT INTO category(id_category, name)
        VALUES (seq_category.nextval, given_name);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_category;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_category
(given_name IN VARCHAR2, new_name IN VARCHAR2)
AS
exists_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM category
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Unregistered Category Number Entered');
        dbms_output.put_line('Unregistered Category Number Entered');
    ELSE
        UPDATE category
        SET name = new_name
        WHERE name = given_name;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar: ' || SQLERRM);
        dbms_output.put_line('Error al actualizar: ' || SQLERRM);
END update_category;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_categories
RETURN SYS_REFCURSOR
AS
category_cursor SYS_REFCURSOR;
BEGIN
    OPEN category_cursor FOR
        SELECT name
        FROM category;
    RETURN category_cursor;
END get_all_categories;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_category_id (category_name IN VARCHAR2)
RETURN NUMBER
AS
    category_id NUMBER;
BEGIN
    SELECT id_category
    INTO category_id
    FROM category
    WHERE name = category_name;
    RETURN category_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Category not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving N: ' || SQLERRM);
        dbms_output.put_line('Error retrieving nationality: ' || SQLERRM);
END get_category_id;

--TO DO: REMOVAL------------------------------

--Country Related Functions and Procedures--------------------------------------

CREATE OR REPLACE PROCEDURE register_country
(given_name IN VARCHAR2, given_flag_photo IN VARCHAR2)
AS
clone_checker NUMBER;
flag_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM country
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Country already registered');
        dbms_output.put_line('Country already registered');
    ELSE
        flag_id := check_flag_photo(given_flag_photo);
        INSERT INTO country(id_country, name, id_flag_photo)
        VALUES (seq_country.nextval, given_name, flag_id);
        COMMIT;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_country;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_country
( choice IN VARCHAR2, given_name IN VARCHAR2, new_name IN VARCHAR2, 
    new_flag IN VARCHAR2)
AS
exists_checker NUMBER;
flag_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM country
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Country not registered');
        dbms_output.put_line('Country not registered');
    ELSE
        IF choice = 'name' THEN
            UPDATE country
            SET name = new_name
            WHERE name = given_name;

        ELSIF choice = 'flag' THEN
            flag_id := check_flag_photo(new_flag);
            UPDATE country
            SET id_flag_photo = flag_id
            WHERE name = given_name;
        
        ELSIF choice = 'both' THEN
            flag_id :=  check_flag_photo(new_flag);
            UPDATE country
            SET name = new_name,
                id_flag_photo = flag_id
            WHERE name = given_name;
        ELSE
            --RAISE_APPLICATION_ERROR(-20004, 'Trouble With Editing Choice');
            dbms_output.put_line('Trouble With Editing Choice');
        END IF;
        COMMIT;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error updating country: ' || SQLERRM);
        dbms_output.put_line('Error updating country: ' || SQLERRM);
END update_country;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_countries
RETURN SYS_REFCURSOR
AS
    country_cursor SYS_REFCURSOR;
BEGIN
    OPEN country_cursor FOR
        SELECT c.name, fp.pathD
        FROM country c
        JOIN flag_photo fp
        ON c.id_flag_photo = fp.id_flag_photo;
    RETURN country_cursor;
END get_all_countries;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_country_id (country_name IN VARCHAR2)
RETURN NUMBER
AS
    country_id NUMBER;
BEGIN
    SELECT id_country
    INTO country_id
    FROM country
    WHERE name = country_name;
    RETURN country_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Country not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Country: ' || SQLERRM);
END get_country_id;

--TO DO: REMOVAL------------------------------

--Province Related Functions and Procedures-------------------------------------

CREATE OR REPLACE PROCEDURE register_province
(given_name IN VARCHAR2, given_country IN VARCHAR2)
AS
clone_checker NUMBER;
country_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM province
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Province already registered');
        dbms_output.put_line('Province already registered');
    ELSE
        country_id := get_country_id(given_country);
        IF country_id > 0 THEN
            INSERT INTO province(id_province, name, id_country)
            VALUES (seq_province.nextval, given_name, country_id);
            COMMIT;            
        END IF;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_province;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_province
( choice IN VARCHAR2, given_name IN VARCHAR2, new_name IN VARCHAR2, 
    new_country IN VARCHAR2)
AS
exists_checker NUMBER;
country_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM province
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Province not registered');
        dbms_output.put_line('Province not registered');
    ELSE
        IF choice = 'name' THEN
            UPDATE province
            SET name = new_name
            WHERE name = given_name;

        ELSIF choice = 'country' THEN
            country_id := get_country_id(new_country);
            IF country_id > 0 THEN
                UPDATE province
                SET id_country = country_id
                WHERE name = given_name;
            END IF;
        ELSIF choice = 'both' THEN
            country_id := get_country_id(new_country);
            IF country_id > 0 THEN
                UPDATE province
            SET name = new_name,
                id_country = country_id
            WHERE name = given_name;
            END IF;
          
        ELSE
            --RAISE_APPLICATION_ERROR(-20004, 'Trouble With Editing Choice');
            dbms_output.put_line('Trouble With Editing Choice');
        END IF;
        COMMIT;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error updating country: ' || SQLERRM);
        dbms_output.put_line('Error updating country: ' || SQLERRM);
END update_province;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_province_id (province_name IN VARCHAR2)
RETURN NUMBER
AS
    province_id NUMBER;
BEGIN
    SELECT id_province
    INTO province_id
    FROM province
    WHERE name = province_name;
    RETURN province_id; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Province not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Country: ' || SQLERRM);
END get_province_id;

--Region Related Functions and Procedures-------------------------------------

CREATE OR REPLACE PROCEDURE register_region
(given_name IN VARCHAR2, given_province IN VARCHAR2)
AS
clone_checker NUMBER;
province_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM region
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Region already registered');
        dbms_output.put_line('Region already registered');
    ELSE
        province_id := get_province_id(given_province);
        IF province_id > 0 THEN
            INSERT INTO region(id_region, name, id_province)
            VALUES (seq_region.nextval, given_name, province_id);
            COMMIT;            
        END IF;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_region;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_region
( choice IN VARCHAR2, given_name IN VARCHAR2, new_name IN VARCHAR2, 
    new_province IN VARCHAR2)
AS
exists_checker NUMBER;
province_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM region
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Region not registered');
        dbms_output.put_line('Region not registered');
    ELSE
        IF choice = 'name' THEN
            UPDATE region
            SET name = new_name
            WHERE name = given_name;

        ELSIF choice = 'province' THEN
            province_id := get_province_id(new_province);
            IF province_id > 0 THEN
                UPDATE region
                SET id_province = province_id
                WHERE name = given_name;
            END IF;
        ELSIF choice = 'both' THEN
            province_id := get_province_id(new_province);
            IF province_id > 0 THEN
                UPDATE region
            SET name = new_name,
                id_province = province_id
            WHERE name = given_name;
            END IF;
          
        ELSE
            --RAISE_APPLICATION_ERROR(-20004, 'Trouble With Editing Choice');
            dbms_output.put_line('Trouble With Editing Choice');
        END IF;
        COMMIT;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error updating country: ' || SQLERRM);
        dbms_output.put_line('Error updating country: ' || SQLERRM);
END update_region;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_region_id (region_name IN VARCHAR2)
RETURN NUMBER
AS
    region_id NUMBER;
BEGIN
    SELECT id_region
    INTO region_id
    FROM region
    WHERE name = region_name;
    RETURN region_id; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Region not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Country: ' || SQLERRM);
END get_region_id;

--District Related Functions and Procedures-------------------------------------

CREATE OR REPLACE PROCEDURE register_district
(given_name IN VARCHAR2, given_region IN VARCHAR2)
AS
clone_checker NUMBER;
region_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM district
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'District already registered');
        dbms_output.put_line('District already registered');
    ELSE
        region_id := get_region_id(given_region);
        IF region_id > 0 THEN
            INSERT INTO district(id_district, name, id_region)
            VALUES (seq_district.nextval, given_name, region_id);
            COMMIT;            
        END IF;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_district;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_district
( choice IN VARCHAR2, given_name IN VARCHAR2, new_name IN VARCHAR2, 
    new_region IN VARCHAR2)
AS
exists_checker NUMBER;
region_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM district
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'District not registered');
        dbms_output.put_line('District not registered');
    ELSE
        IF choice = 'name' THEN
            UPDATE district
            SET name = new_name
            WHERE name = given_name;

        ELSIF choice = 'region' THEN
            region_id := get_region_id(new_region);
            IF region_id > 0 THEN
                UPDATE region
                SET id_region = region_id
                WHERE name = given_name;
            END IF;
        ELSIF choice = 'both' THEN
            region_id := get_region_id(new_region);
            IF region_id > 0 THEN
                UPDATE district
            SET name = new_name,
                id_region = region_id
            WHERE name = given_name;
            END IF;
          
        ELSE
            --RAISE_APPLICATION_ERROR(-20004, 'Trouble With Editing Choice');
            dbms_output.put_line('Trouble With Editing Choice');
        END IF;
        COMMIT;
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error updating country: ' || SQLERRM);
        dbms_output.put_line('Error updating country: ' || SQLERRM);
END update_district;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_district_id (district_name IN VARCHAR2)
RETURN NUMBER
AS
    district_id NUMBER;
BEGIN
    SELECT id_district
    INTO district_id
    FROM district
    WHERE name = district_name;
    RETURN district_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('District not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Country: ' || SQLERRM);
END get_district_id;

--Trainer Fucntions and Procedures----------------------------------------------

CREATE OR REPLACE PROCEDURE register_trainer (person_id_given IN NUMBER)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM trainer
    WHERE id_trainer = person_id_given;
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Trainer already registered');
        dbms_output.put_line('Trainer already registered');
    ELSE
        INSERT INTO trainer(id_trainer)
        VALUES (person_id_given);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_trainer;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_trainer_id (trainer_name IN VARCHAR2)
RETURN NUMBER
AS
    trainer_id NUMBER;
BEGIN  
    SELECT t.id_trainer
    INTO trainer_id
    FROM trainer t
    INNER JOIN person p ON t.id_trainer = p.id_person
    WHERE p.first_name || ' ' || p.last_name = trainer_name;
    RETURN trainer_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Trainer not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Trainer: ' || SQLERRM);
END get_trainer_id;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_trainer_complete_id (trainer_name IN VARCHAR2, trainer_lastname IN VARCHAR2)
RETURN NUMBER
AS
    trainer_id NUMBER;
BEGIN
    SELECT t.id_trainer
    INTO trainer_id
    FROM trainer t
    INNER JOIN person p ON t.id_trainer = p.id_person
    WHERE p.first_name = trainer_name
    AND p.last_name = trainer_lastname;
    RETURN trainer_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Trainer not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Trainer: ' || SQLERRM);
END get_trainer_complete_id;

--Athlete Fucntions and Procedures----------------------------------------------

CREATE OR REPLACE PROCEDURE register_athlete (person_id_given IN NUMBER, trainer_id_given IN NUMBER)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM athlete
    WHERE id_athlete = person_id_given;
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Athlete already registered');
        dbms_output.put_line('Athlete already registered');
    ELSE
        INSERT INTO athlete(id_athlete, id_trainer)
        VALUES (person_id_given, trainer_id_given);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_athlete;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_athlete_id (athlete_name IN VARCHAR2, athlete_lastname IN VARCHAR2)
RETURN NUMBER
AS
    athlete_id NUMBER;
BEGIN
    SELECT a.id_athlete
    INTO athlete_id
    FROM athlete a
    INNER JOIN person p
    ON a.id_athlete = p.id_person
    WHERE p.first_name = athlete_name
    AND p.last_name = athlete_lastname;
    RETURN athlete_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Athlete not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving Trainer: ' || SQLERRM);
END get_athlete_id;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_athlete_trainer
(
    athlete_fname IN VARCHAR2,   
    athlete_lname IN VARCHAR2,   
    trainer_fname IN VARCHAR2,  
    trainer_lname IN VARCHAR2
)
AS
    athlete_id NUMBER;  
    trainer_id NUMBER;  
BEGIN
    athlete_id := get_athlete_id(athlete_fname, athlete_lname);
    IF athlete_id > 0 THEN
        trainer_id := get_trainer_complete_id(trainer_fname, trainer_lname);
        IF trainer_id > 0 THEN
            UPDATE athlete
            SET id_trainer = trainer_id
            WHERE id_athlete = athlete_id;
            COMMIT;

        ELSE
            RAISE_APPLICATION_ERROR(-20001, 'Trainer not registered');

        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'Athlete not registered');
    END IF; 
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error: ' || SQLERRM);
        ROLLBACK;  -- Roll back the transaction if there is any error
END update_athlete_trainer;

--Email Related Functions and Procedures---------------------------------

CREATE OR REPLACE PROCEDURE register_email(new_emaildir IN VARCHAR2, person_id_given IN NUMBER)
AS
    email_checker NUMBER;
    email_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO email_checker
    FROM email
    WHERE email_direction = new_emaildir
    AND id_person = person_id_given;
    
    IF email_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Photo for this person already registered');
        dbms_output.put_line('Photo for this person already registered');
    ELSE
        INSERT INTO email(id_email, email_direction, id_person)
        VALUES (seq_email.nextval, new_emaildir, person_id_given);
        COMMIT;
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving or inserting photo: ' || SQLERRM);
        dbms_output.put_line('Error retrieving or inserting photo: ' || SQLERRM);
END register_email;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_email_id(new_emaildir IN VARCHAR2, person_id_given IN NUMBER)
RETURN NUMBER
AS
    email_id NUMBER;
BEGIN
    SELECT id_email
    INTO email_id
    FROM email
    WHERE email_direction = new_emaildir
    AND id_person = person_id_given;
    RETURN email_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Email not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving or inserting photo: ' || SQLERRM);
        dbms_output.put_line('Error retrieving or inserting photo: ' || SQLERRM);
END get_email_id;

--Role Related Functions and Procedures---------------------------------------

CREATE OR REPLACE PROCEDURE register_role
(given_name in VARCHAR2)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM role
    WHERE name = given_name;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Gender option already registered');
        dbms_output.put_line('Gender option already registered');
    ELSE 
        INSERT INTO role(id_role, name)
        VALUES (seq_role.nextval, given_name);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_role;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_role
(given_name IN VARCHAR2, new_name IN VARCHAR2)
AS
exists_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM role
    WHERE name = given_name;
    
    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Unregistered Gender Entered');
        dbms_output.put_line('Unregistered Gender Entered');
    ELSE
        UPDATE role
        SET name = new_name
        WHERE name = given_name;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error al actualizar: ' || SQLERRM);
        dbms_output.put_line('Error al actualizar: ' || SQLERRM);
END update_role;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_roles
RETURN SYS_REFCURSOR
AS
    role_cursor SYS_REFCURSOR;
BEGIN
    OPEN role_cursor FOR
        SELECT name
        FROM role;
    RETURN role_cursor;
END get_all_roles;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_role_id (role_name IN VARCHAR2)
RETURN NUMBER
AS
    role_id NUMBER;
BEGIN
    SELECT id_role
    INTO role_id
    FROM role
    WHERE name = role_name;
    RETURN role_id; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Role not registered');
        RETURN 0;
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20005, 'Error retrieving Country: ' || SQLERRM);
        dbms_output.put_line('Error retrieving role: ' || SQLERRM);
END get_role_id;

--User Related Functions and Procedures---------------------------------------

CREATE OR REPLACE PROCEDURE register_user
(
    id_given IN NUMBER, 
    username_given IN VARCHAR2,
    role_given IN VARCHAR2,
    password_given IN VARCHAR2
)
AS
    clone_checker NUMBER;
    clone_username NUMBER;
    hashed_password RAW(20);  -- SHA-1 produces a 20-byte hash
BEGIN
    -- Check if user with the given ID already exists
    SELECT COUNT(*)
    INTO clone_checker
    FROM user_person
    WHERE id_user = id_given;
    
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'User already registered');
        dbms_output.put_line('User already registered');
    ELSE
        -- Check if username is already taken
        SELECT COUNT(*)
        INTO clone_username
        FROM user_person
        WHERE username = username_given;
        
        IF clone_username > 0 THEN
            --RAISE_APPLICATION_ERROR(-20001, 'Username already taken');
            dbms_output.put_line('Username taken');
        ELSE
            -- Hash the password using SHA-1
            hashed_password := DBMS_CRYPTO.HASH(UTL_I18N.STRING_TO_RAW(password_given, 'AL32UTF8'), DBMS_CRYPTO.HASH_SH1);
            
            -- Insert the hashed password into the user_person table
            INSERT INTO user_person (id_user, username, password, id_role)
            VALUES (id_given, username_given, RAWTOHEX(hashed_password), role_given);
            COMMIT;
        END IF;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        -- Handle any errors that occur
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_user;

--Person Related Functions and Procedures---------------------------------------

CREATE OR REPLACE PROCEDURE register_person
    (
    type_given IN VARCHAR2, 
    fname_given IN VARCHAR2, 
    lname_given IN VARCHAR2, 
    bdate_given IN DATE, 
    idnumber_given IN NUMBER, 
    gender_given IN VARCHAR2,
    country_given IN VARCHAR2, 
    nationality_given IN VARCHAR2, 
    district_given IN VARCHAR2, 
    --region,province,country
    idtype_given IN VARCHAR2, 
    email_given IN VARCHAR2, 
    path_given IN VARCHAR2, 
    phone_numer_given IN NUMBER,
    --second
    fntrainer_given IN VARCHAR2, 
    lntrainer_given IN VARCHAR2,
    username_given IN VARCHAR2,
    password_given IN VARCHAR2,
    role_given IN VARCHAR2
    ) 
AS
    clone_checker NUMBER;
    gender_id NUMBER;
    nationality_id NUMBER;
    country_id NUMBER;
    district_id NUMBER;
    idtype_id NUMBER;
    phone_id NUMBER;
    email_id NUMBER;
    current_person_id NUMBER;
    trainer_id NUMBER;
    role_id NUMBER;
BEGIN
SELECT COUNT(*)
    INTO clone_checker
    FROM person
    WHERE identification_number = idnumber_given;
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Person already registered');
        dbms_output.put_line('Person already registered');
    ELSE
        gender_id := get_gender_id(gender_given);
        country_id := get_country_id(country_given);
        district_id := get_district_id(district_given);
        nationality_id := get_nationality_id(nationality_given);
        idtype_id := get_id_type_id(idtype_given);
        current_person_id := seq_person.nextval;
        IF (gender_id > 0) AND (country_id > 0) AND (district_id > 0)  AND 
            (idtype_id > 0) AND (nationality_id > 0) THEN    
            INSERT INTO person (id_person, first_name, last_name, birth_date,
                identification_number, id_gender, id_country_represents, 
                id_district)
            VALUES (current_person_id, fname_given, lname_given, bdate_given, 
                idnumber_given,gender_id, country_id, district_id);
            COMMIT;
            register_person_nationality(current_person_id, nationality_id);           
            register_person_id_t(current_person_id, idtype_id);            
            register_photo(path_given, current_person_id);            
            register_email(email_given, current_person_id);       
            phone_id := get_phone_id(phone_numer_given);        
            IF phone_id = 0 THEN
            register_phone(phone_numer_given);
            phone_id := get_phone_id(phone_numer_given);
            END IF;          
            register_person_phone(current_person_id, phone_id);
        END IF;
        IF type_given = 'athlete' THEN
            trainer_id := get_trainer_complete_id(fntrainer_given, lntrainer_given);
            IF trainer_id > 0 THEN
                register_athlete(current_person_id, trainer_id);
            ELSE
                --RAISE_APPLICATION_ERROR(-20001, 'Person already registered');
                dbms_output.put_line('Trainer not registered');
            END IF;
        ELSIF type_given = 'trainer' THEN
            register_trainer(current_person_id);
        ELSIF type_given = 'user' THEN
            role_id := get_role_id(role_given);
            IF role_id > 0 THEN
                register_user(current_person_id, username_given, password_given, role_given);
            END IF;
        ELSE
            --RAISE_APPLICATION_ERROR(-20004, 'Trouble With Editing Choice');
            dbms_output.put_line('Trouble With Editing Choice');
        END IF;
            
            
    END IF;
END register_person;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_person
    (
    choice IN VARCHAR2,               
    idnumber_given IN NUMBER,          
    new_fname IN VARCHAR2,             
    new_lname IN VARCHAR2,           
    new_gender IN VARCHAR2,           
    new_country IN VARCHAR2,           
    new_district IN VARCHAR2           
    )
AS
    exists_checker NUMBER;
    gender_id NUMBER;
    country_id NUMBER;
    district_id NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO exists_checker
    FROM person
    WHERE identification_number = idnumber_given;
    
    IF exists_checker = 0 THEN
        dbms_output.put_line('Person not registered');
    ELSE
        IF choice = 'name' THEN
            UPDATE person
            SET first_name = new_fname,
                last_name = new_lname
            WHERE identification_number = idnumber_given;
            dbms_output.put_line('Name updated');
            
        ELSIF choice = 'gender' THEN
            gender_id := get_gender_id(new_gender);
            IF gender_id > 0 THEN
                UPDATE person
                SET id_gender = gender_id
                WHERE identification_number = idnumber_given;
                dbms_output.put_line('Gender updated');
            ELSE
                dbms_output.put_line('Invalid gender');
            END IF;
            
        ELSIF choice = 'country' THEN
            country_id := get_country_id(new_country);
            IF country_id > 0 THEN
                UPDATE person
                SET id_country_represents = country_id
                WHERE identification_number = idnumber_given;
                dbms_output.put_line('Country updated');
            ELSE
                dbms_output.put_line('Invalid country');
            END IF;
            
        ELSIF choice = 'district' THEN
            district_id := get_district_id(new_district);
            IF district_id > 0 THEN
                UPDATE person
                SET id_district = district_id
                WHERE identification_number = idnumber_given;
                dbms_output.put_line('District updated');
            ELSE
                dbms_output.put_line('Invalid district');
            END IF;
            
        ELSIF choice = 'all' THEN
            gender_id := get_gender_id(new_gender);
            country_id := get_country_id(new_country);
            district_id := get_district_id(new_district);
            IF gender_id > 0 AND country_id > 0 AND district_id > 0 THEN
                UPDATE person
                SET first_name = new_fname,
                    last_name = new_lname,
                    id_gender = gender_id,
                    id_country_represents = country_id,
                    id_district = district_id
                WHERE identification_number = idnumber_given;
                dbms_output.put_line('All fields updated');
            ELSE
                dbms_output.put_line('Invalid values for one or more fields');
            END IF;
        ELSE
            dbms_output.put_line('Invalid update choice');
        END IF;
        
        COMMIT; 
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error updating person: ' || SQLERRM);
END update_person;

--Person_Nationality Related Functions and Procedures---------------------------

CREATE OR REPLACE PROCEDURE register_person_nationality (person_id_given IN NUMBER, 
    nationality_id_given IN NUMBER)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_nationality
    WHERE id_person = person_id_given
    AND id_nationality = nationality_id_given;
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Athlete already registered');
        dbms_output.put_line('This nationality for this athlete already registered');
    ELSE
        INSERT INTO person_nationality(id_person, id_nationality)
        VALUES (person_id_given, nationality_id_given);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_person_nationality;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_specific_nationality 
(
    person_id_given IN NUMBER,
    old_nationality IN VARCHAR2,
    new_nationality IN VARCHAR2
)
AS
exists_checker NUMBER;
new_nat_id NUMBER;
old_nat_id NUMBER;
BEGIN
    old_nat_id := get_nationality_id(old_nationality);
    new_nat_id := get_nationality_id(new_nationality);
    
    SELECT COUNT(*)
    INTO exists_checker
    FROM person_nationality
    WHERE id_person = person_id_given
    AND id_nationality = old_nat_id;
    
    IF exists_checker > 0 THEN
        UPDATE person_nationality
        SET id_nationality = new_nat_id
        WHERE id_person = person_id_given
        AND id_nationality = old_nat_id;
        COMMIT;
    ELSE
        dbms_output.put_line('Person does not have the specified old nationality');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error updating nationality: ' || SQLERRM);
        ROLLBACK;
END update_specific_nationality;
    
--Person_Phone Related Functions and Procedures---------------------------------

CREATE OR REPLACE PROCEDURE register_person_phone (person_id_given IN NUMBER, 
    phone_id_given IN NUMBER)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_phone
    WHERE id_person = person_id_given
    AND id_phone = phone_id_given;
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Athlete already registered');
        dbms_output.put_line('This phone for this person already registered');
    ELSE
        INSERT INTO person_phone(id_person, id_phone)
        VALUES (person_id_given, phone_id_given);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_person_phone;

--Person_ID_Type Related Functions and Procedures-------------------------------

CREATE OR REPLACE PROCEDURE register_person_id_t (person_id_given IN NUMBER, 
    id_type_id_given IN NUMBER)
AS
clone_checker NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO clone_checker
    FROM person_identification_type
    WHERE id_person = person_id_given
    AND id_identification_type = id_type_id_given;
    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Athlete already registered');
        dbms_output.put_line('This ID Type for this user already registered');
    ELSE
        INSERT INTO person_identification_type(id_person, id_identification_type)
        VALUES (person_id_given, id_type_id_given);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_person_id_t;

--Logs Related Functions and Procedures-----------------------------------------

CREATE OR REPLACE PROCEDURE register_log
(
    name_log_given IN VARCHAR2,
    change_type_given IN VARCHAR2,
    username_given IN VARCHAR2,
    object_log_given IN VARCHAR2,
    description_log_given IN VARCHAR2,
    id_user_given IN NUMBER
)
AS
BEGIN
    INSERT INTO log_entries(id_log, date_log, time_log, name_log, change_type, username, object_log, decription_log, id_user)
    VALUES (seq_log.nextval, SYSDATE, SYSTIMESTAMP - TRUNC(SYSTIMESTAMP), name_log_given,
     change_type_given, username_given, object_log_given, description_log_given, id_user_given);
    COMMIT;
    dbms_output.put_line('Log entry registered successfully.');
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error registering log entry: ' || SQLERRM);
        ROLLBACK;
END register_log;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE delete_log(id_log_given IN NUMBER)
AS
    log_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO log_exists
    FROM log_entries
    WHERE id_log = id_log_given;
    
    IF log_exists = 0 THEN
        dbms_output.put_line('Log entry with ID ' || id_log_given || ' does not exist.');
    ELSE
        DELETE FROM log_entries
        WHERE id_log = id_log_given;        
        COMMIT;
        dbms_output.put_line('Log entry with ID ' || id_log_given || ' has been deleted successfully.');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        -- Handle errors
        dbms_output.put_line('Error deleting log entry: ' || SQLERRM);
        ROLLBACK;
END delete_log;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_logs
RETURN SYS_REFCURSOR
AS
    log_cursor SYS_REFCURSOR;
BEGIN
    OPEN log_cursor FOR
        SELECT id_log, name_log
        FROM log_entries;
    RETURN log_cursor;
END get_logs;

--Sport Related Functions and Procedures----------------------------------------

CREATE OR REPLACE PROCEDURE register_sport
(
    given_name IN VARCHAR2,
    given_description IN VARCHAR2,
    given_rules IN VARCHAR2
)
AS
    clone_checker NUMBER;
BEGIN
    -- Check if the sport already exists
    SELECT COUNT(*)
    INTO clone_checker
    FROM sport
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Sport already registered');
        dbms_output.put_line('Sport already registered');
    ELSE
        -- Insert the new sport
        INSERT INTO sport(id_sport, name, description, rules)
        VALUES (seq_sport.nextval, given_name, given_description, given_rules);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
        dbms_output.put_line('Error registering: ' || SQLERRM);
END register_sport;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_sport
(
    given_name IN VARCHAR2,
    new_name IN VARCHAR2,
    new_description IN VARCHAR2,
    new_rules IN VARCHAR2
)
AS
    exists_checker NUMBER;
BEGIN
    -- Check if the sport exists
    SELECT COUNT(*)
    INTO exists_checker
    FROM sport
    WHERE name = given_name;

    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Sport not registered');
        dbms_output.put_line('Sport not registered');
    ELSE
        -- Update the sport details
        UPDATE sport
        SET name = new_name, description = new_description, rules = new_rules
        WHERE name = given_name;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error updating: ' || SQLERRM);
        dbms_output.put_line('Error updating: ' || SQLERRM);
END update_sport;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_sports
RETURN SYS_REFCURSOR
AS
    sport_cursor SYS_REFCURSOR;
BEGIN
    -- Open cursor to return all sports
    OPEN sport_cursor FOR
        SELECT name, description, rules
        FROM sport;
    RETURN sport_cursor;
END get_all_sports;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_sport_id (sport_name IN VARCHAR2)
RETURN NUMBER
AS
    sport_id NUMBER;
BEGIN
    sport_id := 0; -- Default value if sport is not found
    SELECT id_sport
    INTO sport_id
    FROM sport
    WHERE name = sport_name;
    RETURN sport_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Sport not registered');
        RETURN 0;
    WHEN OTHERS THEN
        dbms_output.put_line('Error retrieving sport: ' || SQLERRM);
        RETURN 0;
END get_sport_id;

--Olympic Related Functions and Procedures--------------------------------------

CREATE OR REPLACE PROCEDURE register_olympic
(
    given_year IN NUMBER,
    given_country IN VARCHAR2
)
AS
    clone_checker NUMBER;
    country_id NUMBER;
BEGIN
    -- Check if the Olympic event is already registered
    SELECT COUNT(*)
    INTO clone_checker
    FROM olympic
    WHERE year = given_year;

    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Olympic event already registered for this year');
        dbms_output.put_line('Olympic event already registered for this year');
    ELSE
        -- Retrieve the country ID
        country_id := get_country_id(given_country);
        
        -- Insert the new Olympic event
        IF country_id > 0 THEN
            INSERT INTO olympic(id_olympic, year, id_country)
            VALUES (seq_olympic.nextval, given_year, country_id);
            COMMIT;
        ELSE
            --RAISE_APPLICATION_ERROR(-20002, 'Country not found');
            dbms_output.put_line('Country not found');
        END IF;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20003, 'Error registering Olympic event: ' || SQLERRM);
        dbms_output.put_line('Error registering Olympic event: ' || SQLERRM);
END register_olympic;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_olympic
(
    given_year IN NUMBER,
    new_year IN NUMBER,
    new_country IN VARCHAR2
)
AS
    exists_checker NUMBER;
    country_id NUMBER;
BEGIN
    -- Check if the Olympic event exists
    SELECT COUNT(*)
    INTO exists_checker
    FROM olympic
    WHERE year = given_year;

    IF exists_checker = 0 THEN
        --RAISE_APPLICATION_ERROR(-20004, 'Olympic event not registered');
        dbms_output.put_line('Olympic event not registered');
    ELSE
        -- Retrieve the new country ID
        country_id := get_country_id(new_country);
        
        -- Update the Olympic event details
        IF country_id > 0 THEN
            UPDATE olympic
            SET year = new_year, id_country = country_id
            WHERE year = given_year;
            COMMIT;
        ELSE
            --RAISE_APPLICATION_ERROR(-20005, 'Country not found');
            dbms_output.put_line('Country not found');
        END IF;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20006, 'Error updating Olympic event: ' || SQLERRM);
        dbms_output.put_line('Error updating Olympic event: ' || SQLERRM);
END update_olympic;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_all_olympic_events
RETURN SYS_REFCURSOR
AS
    olympic_cursor SYS_REFCURSOR;
BEGIN
    -- Open cursor to return all Olympic events
    OPEN olympic_cursor FOR
        SELECT year, (SELECT name FROM country WHERE id_country = olympic.id_country) AS host_country
        FROM olympic;
    RETURN olympic_cursor;
END get_all_olympic_events;

--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_olympic_event_id (given_year IN NUMBER)
RETURN NUMBER
AS
    olympic_id NUMBER;
BEGIN
    olympic_id := 0; -- Default value if Olympic event is not found
    SELECT id_olympic
    INTO olympic_id
    FROM olympic
    WHERE year = given_year;
    RETURN olympic_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Olympic event not registered');
        RETURN 0;
    WHEN OTHERS THEN
        dbms_output.put_line('Error retrieving Olympic event: ' || SQLERRM);
        RETURN 0;
END get_olympic_event_id;

--Team Related Functions and Procedures-----------------------------------------

CREATE OR REPLACE PROCEDURE register_team
(
    given_name IN VARCHAR2, 
    given_trainer_id IN NUMBER
)
AS
    clone_checker NUMBER;
    BEGIN
    -- Check if the Olympic event is already registered
    SELECT COUNT(*)
    INTO clone_checker
    FROM team
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Nacionality already registered');
        dbms_output.put_line('Team already registered');
    ELSE
        INSERT INTO team(id_team, id_trainer)
        VALUES (seq_team.nextval, given_trainer_id);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
         dbms_output.put_line('Error registering: ' || SQLERRM);
END register_team;

--Medal Related Functions and Procedures----------------------------------------

CREATE OR REPLACE PROCEDURE register_medal
(
    given_name in VARCHAR2
)
AS
    clone_checker NUMBER;
    BEGIN
    -- Check if the Olympic event is already registered
    SELECT COUNT(*)
    INTO clone_checker
    FROM medal
    WHERE name = given_name;

    IF clone_checker > 0 THEN
        --RAISE_APPLICATION_ERROR(-20001, 'Nacionality already registered');
        dbms_output.put_line('Team already registered');
    ELSE
        INSERT INTO medal(id_medal, name)
        VALUES (seq_medal.nextval, given_name);
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20002, 'Error registering: ' || SQLERRM);
         dbms_output.put_line('Error registering: ' || SQLERRM);
END register_medal;

--Event Related Functions and Procedures----------------------------------------

CREATE OR REPLACE PROCEDURE register_event(
    date_given IN DATE,
    time_given IN INTERVAL DAY TO SECOND,
    name_given IN VARCHAR2,
    id_olympic_given IN NUMBER,
    sport_name_given IN NUMBER
)
AS
BEGIN
    -- Insert the event into the event table
    INSERT INTO event (id_event, date_event, time_event, name, id_olympic, id_sport)
    VALUES (seq_event.nextval, p_date_event, p_time_event, p_name_event, p_id_olympic, p_id_sport);

    -- Commit the transaction
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Handle any errors that occur during insertion
        ROLLBACK;
        dbms_output.put_line('Error registering event: ' || SQLERRM);
END register_event;

