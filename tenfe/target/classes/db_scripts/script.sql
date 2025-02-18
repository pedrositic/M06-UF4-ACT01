-- Definición de la tabla Companyia
CREATE TABLE companyia (
    id INTEGER PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

-- Definición de la tabla Tren
CREATE TABLE tren (
    id INTEGER PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    companyia_id INTEGER,
    FOREIGN KEY (companyia_id) REFERENCES companyia(id)
);

-- Definición de la tabla Trajecte
CREATE TABLE trajecte (
    id INTEGER PRIMARY KEY,
    origen VARCHAR(255),
    desti VARCHAR(255),
    tren_id INTEGER,
    FOREIGN KEY (tren_id) REFERENCES tren(id)
);

-- Definición de la tabla Estacio
CREATE TABLE estacio (
    id INTEGER PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

-- Relación N-M entre Trajecte y Estacio
CREATE TABLE trajecte_estacio (
    trajecte_id INTEGER,
    estacio_id INTEGER,
    FOREIGN KEY (trajecte_id) REFERENCES trajecte(id),
    FOREIGN KEY (estacio_id) REFERENCES estacio(id)
);