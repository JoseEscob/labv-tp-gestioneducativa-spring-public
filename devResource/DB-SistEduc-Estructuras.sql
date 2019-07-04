DROP DATABASE IF EXISTS `tp_sistema_escolar`;
CREATE DATABASE `tp_sistema_escolar`;

USE tp_sistema_escolar;
create table TipoExamen( 
    idTipoExamen int not null auto_increment,
    descripcion varchar(50) not null,
    constraint pk_tipo_examen primary key(idTipoExamen)
);

create table TipoPeriodo( 
    idPeriodo int not null auto_increment,
    descripcion varchar(50) not null,
    constraint pk_tipo_periodo primary key(idPeriodo)
);

create table tipoUsuarios( 
    idTipoUsuario int not null auto_increment,
    descripcion varchar(50) not null,
    constraint pk_tipo_usuarios primary key(idTipoUsuario)
);

create table Usuario( 
    idUsuario int not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    dni varchar(15) not null,
    direccion varchar(50) not null,
    fechaNac date not null,
    nroTelefono varchar(20),
    mail varchar(50) not null, -- unique
    clave varchar(20) not null,
    idTipoUsuario int not null,
    habilitado tinyint(1) not null default 1,
    constraint pk_usuarios primary key(idUsuario),
    constraint fk_tipo_usuarios foreign key(idTipoUsuario) references tipoUsuarios(idTipoUsuario),
    index dniIndex(dni)
);

create table cursos(
    idCurso int not null auto_increment,
    nombreCurso varchar (30) not null,
    anio int not null,
    idPeriodo int not null,-- cuatrimestre int not null,
    dniProfesor varchar(15) not null,
    constraint pk_cursos primary key(idCurso),
    constraint fk_usuarios_profesor foreign key(dniProfesor) references Usuario(dni),
    constraint fk_tipos_periodos foreign key(idPeriodo) references TipoPeriodo(idPeriodo)
);

create table cursosCalificaciones(
    idCursoCalif int not null auto_increment,
    idCurso int not null,
    dniAlumno varchar(15) not null,
    idTipoExamen int not null,
    nota int not null,
    fechaCalif date not null,
    fechaUltModif date not null,
    constraint pk_cursos_calif primary key(idCursoCalif),
    constraint fk_usuarios_Alumno foreign key(dniAlumno) references Usuario(dni),
    constraint fk_cursos_calif  foreign key(idCurso) references cursos(idCurso),
    constraint fk_cursos_tipoExamen  foreign key(idTipoExamen) references TipoExamen(idTipoExamen)
);

create table cursosAsistencias(
    idCursoAsist int not null auto_increment,
    idCurso int not null,
    dniAlumno varchar(15) not null,
    asistencia tinyint(1) not null default 0,
    fechaAsistencia date not null,
    constraint pk_cursos_asist primary key(idCursoAsist),
    constraint fk_usuarios_Alumno_asist foreign key(dniAlumno) references Usuario(dni),
    constraint fk_cursos_asist  foreign key(idCurso) references cursos(idCurso)
);


