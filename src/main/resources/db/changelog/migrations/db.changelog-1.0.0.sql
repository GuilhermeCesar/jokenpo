--liquibase formatted sql

--changeset medeiros:1
CREATE TABLE jokenpo.jogador
(
    id    int NOT NULL AUTO_INCREMENT,
    nome  VARCHAR(100),
    ativo BIT,
    PRIMARY KEY (id)
);

--changeset medeiros:2
CREATE TABLE jokenpo.partida
(
    id       int NOT NULL AUTO_INCREMENT,
    nome     VARCHAR(100),
    ganhador INT,
    ativo    BIT,
    PRIMARY KEY (id)
);

ALTER TABLE jokenpo.partida
    ADD FOREIGN KEY (ganhador) REFERENCES jokenpo.jogador (id);


--changeset medeiros:3
CREATE TABLE jokenpo.turno
(
    id         int NOT NULL AUTO_INCREMENT,
    id_partida int NOT NULL,
    finalizado BIT,
    PRIMARY KEY (id)
);
ALTER TABLE jokenpo.turno
    ADD FOREIGN KEY (id_partida) REFERENCES jokenpo.partida (id);

--changeset medeiros:4
CREATE TABLE jokenpo.jogada
(
    id         int NOT NULL AUTO_INCREMENT,
    id_jogador int NOT NULL,
    id_turno   int NOT NULL,
    jogada     VARCHAR(30),
    PRIMARY KEY (id)
);

ALTER TABLE jokenpo.jogada
    ADD FOREIGN KEY (id_jogador) REFERENCES jokenpo.jogador (id);
ALTER TABLE jokenpo.jogada
    ADD FOREIGN KEY (id_turno) REFERENCES jokenpo.turno (id);



