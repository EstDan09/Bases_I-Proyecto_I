CREATE TABLESPACE AP_Data
    DATAFILE 'C:\app\esteban\oradata\Proyecto_1_Bases_I\apdata01.dbf'
    SIZE 10M
    REUSE
    AUTOEXTEND ON
    NEXT 51K
    MAXSIZE 200M;
    
CREATE TABLESPACE AP_Ind 
    DATAFILE 'C:\app\esteban\oradata\Proyecto_1_Bases_I\apind01.dbf'
    SIZE 10M
    REUSE
    AUTOEXTEND ON
    NEXT 51K
    MAXSIZE 200M;
    
    
----------------------------------------------------------------

CREATE USER AP
    IDENTIFIED BY donotgiveaway
    DEFAULT TABLESPACE AP_Data
    QUOTA 5M ON AP_Data
    QUOTA 5M ON AP_Ind
    TEMPORARY TABLESPACE temp
    QUOTA 5M ON system;
    
GRANT CONNECT TO AP;

GRANT CREATE SESSION TO AP;

GRANT CREATE TABLE TO AP;

GRANT CREATE VIEW TO AP;

GRANT CREATE SEQUENCE TO AP;

GRANT CREATE PROCEDURE TO AP;

GRANT EXECUTE ON DBMS_CRYPTO TO AP;
