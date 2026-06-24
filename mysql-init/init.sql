-- crea la tabla inicial si no existe
CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    precio DOUBLE NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
    );

-- Productos base
INSERT INTO productos (nombre, descripcion, precio, disponible) VALUES
    ('Baguette Tradicional', 'Pan frances crujiente recien horneado', 1200.0, true),
    ('Croissant de Mantequilla', 'Hojaldrado suave y calientito', 1500.0, true),
    ('Empanada', 'Rellena de Queso, Carne ó Pollo', 1300.0, true),
    ('Cafe Negro', 'Cafe recien chorreado en su mesa', 800.0, true),
    ('Capuccino', 'Cafe equilibrio perfecto entre espresso, leche vaporizada y espuma', 2000.0, true);