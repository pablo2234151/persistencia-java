-- Ejecutar sobre una base de práctica con MySQL.
CREATE DATABASE IF NOT EXISTS aula_persistencia
    CHARACTER SET utf8mb4;
USE aula_persistencia;
CREATE TABLE IF NOT EXISTS alumno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);