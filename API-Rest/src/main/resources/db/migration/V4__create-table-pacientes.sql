
CREATE TABLE pacientes (
    id bigint not null auto_increment,
    nombre VARCHAR(100) not null,
    email VARCHAR(100) not null unique,
    telefono VARCHAR(100) not null,
    identidad VARCHAR(6) not null unique,
    calle VARCHAR(100) not null,
    distrito VARCHAR(100) not null,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    ciudad VARCHAR(100) not null,

    primary key(id)     );
