-- Creación de un usuario con contraseña
CREATE USER 'AP'@'localhost' IDENTIFIED BY 'donotgiveaway';

-- Otorgar permisos básicos
GRANT CREATE, SELECT, INSERT, UPDATE, DELETE ON *.* TO 'AP'@'localhost';

-- Permitir creación de tablas y vistas
GRANT CREATE, CREATE VIEW ON `proyecto_bases1`.* TO 'AP'@'localhost';

-- Permitir creación de secuencias (en MySQL usarías AUTO_INCREMENT en lugar de secuencias)
GRANT ALTER ON `proyecto_bases1`.* TO 'AP'@'localhost';

-- Permitir creación de procedimientos y triggers
GRANT CREATE ROUTINE ON `proyecto_bases1`.* TO 'AP'@'localhost';
GRANT TRIGGER ON `proyecto_bases1`.* TO 'AP'@'localhost';

-- Otorgar permisos para usar el programador de eventos
GRANT EVENT ON `proyecto_bases1`.* TO 'AP'@'localhost';

-- Aplicar los permisos
FLUSH PRIVILEGES;