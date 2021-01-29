CREATE DATABASE Buscaminas;
USE Buscaminas;

--TABLA NIVEL

CREATE TABLE Nivel
(
  idNivel int not null,
  numFilas int not null,
  numColumnas int not null,
  constraint nivel_pk
     primary key (idNivel)
);

INSERT INTO Nivel (idNivel, numFilas, numColumnas) VALUES (1,7,10);
INSERT INTO Nivel (idNivel, numFilas, numColumnas) VALUES (2,10,15);
INSERT INTO Nivel (idNivel, numFilas, numColumnas) VALUES (3,12,25);

--Tabla sonidosWin
CREATE TABLE SonidoWin(
  idSonidoWin char(5) not null,
  nombre varchar(15) not null,
  path varchar(60) not null,
  CONSTRAINT PK_SonidoWin PRIMARY KEY (idSonidoWin)
);
ALTER TABLE SonidoWin ADD UNIQUE (nombre);
INSERT INTO SonidoWin (idSonidoWin, nombre, path) VALUES ('sw000','Win','/sonidos_win/win.wav');
INSERT INTO SonidoWin (idSonidoWin, nombre, path) VALUES ('sw001','Triunfal','/sonidos_win/win-triunfal.wav');
INSERT INTO SonidoWin (idSonidoWin, nombre, path) VALUES ('sw002','Ta-Da','/sonidos_win/win-ta-da.wav');

--Tabla sonidosGameOver
CREATE TABLE SonidoGameOver(
  idSonidoGameOver char(5) not null,
  nombre varchar(15) not null,
  path varchar(60) not null,
  CONSTRAINT PK_SonidoGameOver PRIMARY KEY (idSonidoGameOver)
);
ALTER TABLE SonidoGameOver ADD UNIQUE (nombre);
INSERT INTO SonidoGameOver (idSonidoGameOver, nombre, path) VALUES ('sg000','You-Lose','/sonidos_gameover/lose.wav');
INSERT INTO SonidoGameOver (idSonidoGameOver, nombre, path) VALUES ('sg001','Game-Over','/sonidos_gameover/gameover.wav');
INSERT INTO SonidoGameOver (idSonidoGameOver, nombre, path) VALUES ('sg002','Pacman','/sonidos_gameover/pacman.wav');


--Tabla IconosTablero
CREATE TABLE IconosTablero(
  idIcoTablero char(5) not null,
  nombre varchar(15) not null,
  path varchar(60) not null,
  CONSTRAINT PK_IconosTablero PRIMARY KEY (idIcoTablero)
);
ALTER TABLE IconosTablero ADD UNIQUE (nombre);
INSERT INTO IconosTablero (idIcoTablero, nombre, path) VALUES ('it000','Default','/pack_iconos_tablero/pack1');
INSERT INTO IconosTablero (idIcoTablero, nombre, path) VALUES ('it001','Classic','/pack_iconos_tablero/pack2');
INSERT INTO IconosTablero (idIcoTablero, nombre, path) VALUES ('it002','Emoji','/pack_iconos_tablero/pack3');
INSERT INTO IconosTablero (idIcoTablero, nombre, path) VALUES ('it003','Classic2','/pack_iconos_tablero/pack4');


--Tabla Ranking
CREATE TABLE Ranking(
  idRanking char(5) not null,
  nivel int not null,
  nombreJugador varchar(60) not null,
  puntuacion int not null,
  CONSTRAINT PK_Jugador PRIMARY KEY (idRanking)
);

Insert Into Ranking (idRanking, nivel, nombreJugador, puntuacion) VALUES ('1',1,'alvaro',15);
Insert Into Ranking (idRanking, nivel, nombreJugador, puntuacion) VALUES ('2',2,'alain',12);
Insert Into Ranking (idRanking, nivel, nombreJugador, puntuacion) VALUES ('3',3,'asier',11);
Insert Into Ranking (idRanking, nivel, nombreJugador, puntuacion) VALUES ('4',1,'alvaro2',15);
Insert Into Ranking (idRanking, nivel, nombreJugador, puntuacion) VALUES ('5',2,'alain2',12);
Insert Into Ranking (idRanking, nivel, nombreJugador, puntuacion) VALUES ('6',3,'asier2',11);


