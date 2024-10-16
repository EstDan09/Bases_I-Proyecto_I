BEGIN
    register_nationality('American');
    register_nationality('Canadian');
    register_nationality('Mexican');
END;

SELECT * FROM COUNTRY;
SELECT * FROM flag_photo;
---
BEGIN
    get_phone_id(788);
END;

DECLARE
    nationality_cursor SYS_REFCURSOR;
    nationality_name VARCHAR2(50);
BEGIN
    nationality_cursor := get_all_nationalities;
    LOOP
        FETCH nationality_cursor INTO nationality_name;
        EXIT WHEN nationality_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(nationality_name);
    END LOOP;
    CLOSE nationality_cursor;
END;

DECLARE
    flag_id NUMBER;
DECLARE
    v_phone_id NUMBER;
BEGIN
    v_phone_id := get_phone_id(788); -- Store the result of the function in a variable
    dbms_output.put_line('Phone ID: ' || v_phone_id); -- Print the result
END;



BEGIN
    register_country('United States', '/flags/usa_flag.png');
    register_country('Canada', '/flags/canada_flag.png');
    register_country('Mexico', '/flags/mexico_flag.png');
END;

-- Update only the name
BEGIN
    update_country('name', 'United States', 'USA', NULL);
END;
/
-- Update only the flag
BEGIN
    update_country('flag', 'Canada', NULL, '/flags/new_canada_flag.png');
END;
/
-- Update both name and flag
BEGIN
    update_country('both', 'Mexico', 'United Mexican States', '/flags/new_mexico_flag.png');
END;
/

ALTER TABLE PHOTO
MODIFY PATHD VARCHAR2(200);

truncate TABLE FLAG_PHOTO

DELETE FROM nationality;
DELETE FROM country;
DELETE FROM flag_photo;
DELETE FROM photo;
DELETE FROM gender;
DELETE FROM identification_type;
DELETE FROM phone;
DELETE FROM category;
COMMIT;

DELETE FROM nationality;
DELETE FROM country;
DELETE FROM flag_photo;
DELETE FROM photo;
DELETE FROM gender;
DELETE FROM identification_type;
DELETE FROM phone;
DELETE FROM category;
COMMIT; -- To finalize the deletion


ALTER SEQUENCE seq_nationality RESTART WITH 1;
DROP SEQUENCE seq_nationality;
DROP SEQUENCE seq_country;
DROP SEQUENCE seq_flag_photo;
DROP SEQUENCE seq_photo;
DROP SEQUENCE seq_gender;
DROP SEQUENCE seq_identification_type;
DROP SEQUENCE seq_phone;
DROP SEQUENCE seq_category;


