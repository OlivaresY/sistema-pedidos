-- crea la tabla inicial si no existe
CREATE TABLE IF NOT EXISTS productos (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    precio DOUBLE NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
    );

-- Producto de prueba
INSERT INTO productos (nombre, descripcion, precio, disponible)
VALUES ('Pan Baguette', 'Pan artesanal crujiente', 1.50, true);