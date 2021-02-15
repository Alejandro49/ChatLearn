insert into chatlearn.usuario(nombre, apellido_1, apellido_2, userName, email, nacionalidad, dni, telefono, contrasena) values('tpm','tpm','tpm','tpm','tpm@gmail.com','tpm','11111111T','111222333','tpm');
insert into chatlearn.usuario(nombre, apellido_1, apellido_2, userName, email, nacionalidad, dni, telefono, contrasena) values('a','a','a','a','a@gmail.com','a','11111111A','111111111','a');
insert into chatlearn.usuario(nombre, apellido_1, apellido_2, userName, email, nacionalidad, dni, telefono, contrasena) values('invitado','invitado','invitado','invitado','invitado@gmail.com','invitado','00000000I','999999999','invitado');

insert into chatlearn.perfil(id,foto,interes1,interes2,interes3,interes4,interes5,lenguaMaterna,calificacion) values (1,"../images/profiles/Mr_Hankey.png","deportes", "musica", "cocinar", "videojuegos", "naturaleza", "español", 5);
insert into chatlearn.perfil(id,foto,interes1,interes2,interes3,interes4,interes5,lenguaMaterna,calificacion) values (2,"../images/profiles/a.jpg","deportes", "musica", "cocinar", "videojuegos", "naturaleza", "frances", 3);
insert into chatlearn.perfil(id,foto,interes1,interes2,interes3,interes4,interes5,lenguaMaterna,calificacion) values (3,"../images/profiles/invitado.jpg","deportes", "musica", "cocinar", "videojuegos", "naturaleza", "ingles", 1);


insert into chatlearn.solicitudes(emisor, receptor) values ( "a", "tpm");
insert into chatlearn.solicitudes(emisor, receptor) values ( "invitado", "tpm");
