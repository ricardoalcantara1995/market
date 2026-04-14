CREATE TABLE market.categoria (
    producto_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT NOT NULL DEFAULT 'Comentario',
	fecha_creacion	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	activo BOOLEAN DEFAULT TRUE NOT NULL;
);