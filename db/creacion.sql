CREATE SCHEMA if not exists chatlearn;

CREATE TABLE if not exists chatlearn.usuario (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
	apellido_1 VARCHAR(30) NOT NULL,
	apellido_2 VARCHAR(30) NOT NULL,
	userName VARCHAR(30) UNIQUE NOT NULL,
	email VARCHAR(50) NOT NULL,
	nacionalidad VARCHAR(30) NOT NULL,
	dni VARCHAR(9) UNIQUE NOT NULL,
	telefono VARCHAR(9) NOT NULL,
	contrasena VARCHAR(45) NOT NULL,
	PRIMARY KEY (id)
	);

CREATE TABLE if not exists chatlearn.chat (
	id INT PRIMARY KEY AUTO_INCREMENT,
	idioma VARCHAR(30) NOT NULL,
	profesor INT NOT NULL,
	FOREIGN KEY (profesor) references usuario(id)
	);

CREATE TABLE if not exists chatlearn.perfil (
	id INT PRIMARY KEY,
	foto VARCHAR(255) DEFAULT "../images/profiles/newUser.jpg",
	interes1 VARCHAR(255) DEFAULT "nada",
	interes2 VARCHAR(255) DEFAULT "nada",
	interes3 VARCHAR(255) DEFAULT "nada",
	interes4 VARCHAR(255) DEFAULT "nada",
	interes5 VARCHAR(255) DEFAULT "nada",
	lenguaMaterna VARCHAR(255),
	calificacion INT DEFAULT 0,
	historial VARCHAR(255) DEFAULT "",
	FOREIGN KEY (id) references usuario(id)
);

CREATE TABLE if not exists chatlearn.mensajes (
	id INT PRIMARY KEY AUTO_INCREMENT,
	mensaje VARCHAR(255),
	emisor VARCHAR(255),
	receptor VARCHAR(255),
	FOREIGN KEY (emisor) references usuario(userName),
	FOREIGN KEY (receptor) references usuario(userName)
);

CREATE TABLE if not exists chatlearn.contactos (
	usuario VARCHAR(255),
	contacto VARCHAR(255),
 	FOREIGN KEY (usuario) references usuario(userName),
	FOREIGN KEY (contacto) references usuario(userName),
	PRIMARY KEY (usuario, contacto)
);

CREATE TABLE if not exists chatlearn.solicitudes (
	id INT PRIMARY KEY AUTO_INCREMENT,
	emisor VARCHAR(255) NOT NULL,
	receptor VARCHAR(255) NOT NULL,
	aceptada INT DEFAULT 0,
	FOREIGN KEY (emisor) references usuario(userName),
	FOREIGN KEY (receptor) references usuario(userName),
	UNIQUE KEY (emisor, receptor)
	);