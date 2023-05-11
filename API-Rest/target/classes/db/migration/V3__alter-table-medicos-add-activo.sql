ALTER TABLE medicos add column activo tinyint;
UPDATE medicos set activo=1;
