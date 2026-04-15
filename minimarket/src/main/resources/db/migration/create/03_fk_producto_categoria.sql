Alter table market.producto
Add constraint fk_producto_categoria
Foreign key (categoria_id)
References market.categoria (categoria_id);