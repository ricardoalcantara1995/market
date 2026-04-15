 CREATE TABLE market.producto (
    producto_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT NOT NULL DEFAULT 'Comentario',
	precio	NUMERIC(12,2) NOT NULL CHECK (precio >= 0),
	stock	INTEGER	NOT NULL CHECK (stock >= 0),
	categoria_id UUID NOT NULL,
	fecha_creacion	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	activo BOOLEAN DEFAULT TRUE NOT NULL
);