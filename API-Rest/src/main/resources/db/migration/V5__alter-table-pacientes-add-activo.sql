ALTER TABLE pacientes add column activo tinyint;
UPDATE pacientes SET activo=1;
