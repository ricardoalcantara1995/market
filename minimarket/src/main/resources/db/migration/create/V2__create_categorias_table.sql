CREATE TABLE market.categoria (
    categoria_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
	fecha_creacion	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP
);
