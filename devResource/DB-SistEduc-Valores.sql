USE EjemploProgAvanzadaII;


INSERT into tipoUsuarios SET idTipoUsuario=1, descripcion='Alumno';
INSERT into tipoUsuarios SET idTipoUsuario=2, descripcion='Profesor';
INSERT into tipoUsuarios SET idTipoUsuario=3, descripcion='Administrador';

INSERT into usuario SET idUsuario=1, nombre='admin', apellido='admin', dni='0000000001', calleNombre='nombreCalle', calleAltura='000', fechaNac=STR_TO_DATE('01/12/1990','%d/%m/%Y') , nroTelefono='00000', mail='admin@admin.com', clave='admin', idTipoUsuario=3, habilitado=1 ;
INSERT into usuario SET idUsuario=2, nombre='utn1', apellido='frgp1', dni='3333333331', calleNombre='nombreCalle', calleAltura='000', fechaNac=STR_TO_DATE('01/12/1990','%d/%m/%Y') , nroTelefono='33333', mail='utn1frgp1@gmail.com', clave='utn123', idTipoUsuario=3, habilitado=1 ;
INSERT into usuario SET idUsuario=3, nombre='utn2', apellido='frgp2', dni='3333333332', calleNombre='nombreCalle', calleAltura='000', fechaNac=STR_TO_DATE('01/12/1990','%d/%m/%Y') , nroTelefono='33333', mail='utn2frgp2@gmail.com', clave='utn123', idTipoUsuario=3, habilitado=1 ;
INSERT into usuario SET idUsuario=4, nombre='utn3', apellido='frgp3', dni='3333333333', calleNombre='nombreCalle', calleAltura='000', fechaNac=STR_TO_DATE('01/12/1990','%d/%m/%Y') , nroTelefono='33333', mail='utn3frgp3@gmail.com', clave='utn123', idTipoUsuario=3, habilitado=1 ;
INSERT into usuario SET idUsuario=5, nombre='Tamara', apellido='A. Profe', dni='221', calleNombre='calle profe ', calleAltura='222', fechaNac=STR_TO_DATE('01/01/2000','%d/%m/%Y') , nroTelefono='22222', mail='profe@gmail.com1', clave='utn123', idTipoUsuario=2, habilitado=1 ;
INSERT into usuario SET idUsuario=6, nombre='Tamara', apellido='A. Profe', dni='222', calleNombre='calle profe ', calleAltura='222', fechaNac=STR_TO_DATE('01/01/2000','%d/%m/%Y') , nroTelefono='22222', mail='profe@gmail.com2', clave='utn123', idTipoUsuario=2, habilitado=1 ;
INSERT into usuario SET idUsuario=7, nombre='Tamara', apellido='A. Profe', dni='223', calleNombre='calle profe ', calleAltura='222', fechaNac=STR_TO_DATE('01/01/2000','%d/%m/%Y') , nroTelefono='22222', mail='profe@gmail.com3', clave='utn123', idTipoUsuario=2, habilitado=1 ;
INSERT into usuario SET idUsuario=8, nombre='Juan', apellido='C. Alumno', dni='11111', calleNombre='calle alumno', calleAltura='111', fechaNac=STR_TO_DATE('01/12/2000','%d/%m/%Y') , nroTelefono='11111', mail='alumno@gmail.com1', clave='utn123', idTipoUsuario=1, habilitado=1 ;
INSERT into usuario SET idUsuario=9, nombre='Juan', apellido='C. Alumno', dni='11112', calleNombre='calle alumno', calleAltura='111', fechaNac=STR_TO_DATE('01/12/2000','%d/%m/%Y') , nroTelefono='11111', mail='alumno@gmail.com2', clave='utn123', idTipoUsuario=1, habilitado=1 ;
INSERT into usuario SET idUsuario=10, nombre='Juan', apellido='C. Alumno', dni='11113', calleNombre='calle alumno', calleAltura='111', fechaNac=STR_TO_DATE('01/12/2000','%d/%m/%Y') , nroTelefono='11111', mail='alumno@gmail.com3', clave='utn123', idTipoUsuario=1, habilitado=1 ;
INSERT into usuario SET idUsuario=11, nombre='Juan', apellido='C. Alumno', dni='11114', calleNombre='calle alumno', calleAltura='111', fechaNac=STR_TO_DATE('01/12/2000','%d/%m/%Y') , nroTelefono='11111', mail='alumno@gmail.com4', clave='utn123', idTipoUsuario=1, habilitado=1 ;


=CONCATENATE("INSERT into usuario SET idUsuario=",A1,", nombre='",B1,
"', apellido='",C1,"', dni='",D1,"', calleNombre='",E1, "', calleAltura='",F1,
"', fechaNac=STR_TO_DATE('",G1,"','%d/%m/%Y') , nroTelefono='",H1,
"', mail='",I1,"', clave='",J1,"', idTipoUsuario=",K1,", habilitado=",L1," ;")