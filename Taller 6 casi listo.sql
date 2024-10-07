
COPY public.traspaso 
FROM 'D:/INGENIERIA EN INFORMATICA/registrosss.csv' 
DELIMITER ';' 
CSV HEADER 
ENCODING 'LATIN1';


CREATE TYPE tipo_compras AS (
    nombre_organismo VARCHAR,   -- Nombre del organismo público
    sucursal VARCHAR,           -- Nombre de la sucursal
    tipo_licitacion VARCHAR,    -- Tipo de licitación
    trato_directo BOOLEAN,      -- Si es trato directo
    monto_pagado NUMERIC        -- Monto pagado
);


DROP FUNCTION listar_compras(character varying, character varying, date, date, character varying, numeric);

CREATE OR REPLACE FUNCTION listar_compras(
    organismo VARCHAR, 
    suc_param VARCHAR DEFAULT NULL, 
    fecha_desde DATE DEFAULT NULL, 
    fecha_hasta DATE DEFAULT NULL, 
    proveedor VARCHAR DEFAULT NULL, 
    monto_maximo NUMERIC DEFAULT NULL
)
RETURNS SETOF tipo_compras AS $$
DECLARE
    -- Cursor para manejar la consulta con filtros dinámicos
    micursor CURSOR FOR
        SELECT 
            t.Nombre AS nombre_organismo,
            t.Sucursal AS sucursal,
            t.Tipo AS tipo_licitacion,
            t.EsTratoDirecto = 'Sí' AS trato_directo,  -- Convertir a BOOLEAN
            t.totalLineaNeto::NUMERIC AS monto_pagado
        FROM traspaso t  -- Usar el nombre de la tabla correcta con alias "t"
        WHERE 
            t.Nombre = organismo  -- Filtro obligatorio por organismo
            AND (suc_param IS NULL OR t.Sucursal = suc_param)  -- Filtro opcional por sucursal
            AND (fecha_desde IS NULL OR t.FechaCreacion::DATE >= fecha_desde)  -- Conversión explícita a DATE
            AND (fecha_hasta IS NULL OR t.FechaCreacion::DATE <= fecha_hasta)  -- Conversión explícita a DATE
            AND (proveedor IS NULL OR t.NombreProveedor = proveedor)  -- Filtro opcional por proveedor
            AND (monto_maximo IS NULL OR t.totalLineaNeto::NUMERIC <= monto_maximo);  -- Filtro opcional por monto máximo

    registro tipo_compras;  -- Variable para almacenar cada registro del cursor
BEGIN
    OPEN micursor;  -- Abre el cursor para comenzar a recorrer los registros

    LOOP
        FETCH NEXT FROM micursor INTO registro;  -- Obtiene el siguiente registro
        EXIT WHEN NOT FOUND;  -- Sale del bucle si no hay más registros

        RETURN NEXT registro;  -- Retorna el registro actual
    END LOOP;

    CLOSE micursor;  -- Cierra el cursor al finalizar
    RETURN;  -- Finaliza la función
END; 
$$ LANGUAGE plpgsql;





SELECT * FROM listar_compras(
    'Ministerio de Salud',     -- Nombre del organismo (obligatorio)
    'Sucursal Santiago',       -- Nombre de la sucursal (puede ser NULL)
    '2023-01-01',              -- Fecha desde (puede ser NULL)
    NULL,                      -- Fecha hasta (puede ser NULL, para incluir todas las fechas posteriores a 'fecha_desde')
    NULL,                      -- Proveedor (puede ser NULL, para incluir todos los proveedores)
    1000000                    -- Monto máximo (puede ser NULL, para incluir todos los montos)
);
