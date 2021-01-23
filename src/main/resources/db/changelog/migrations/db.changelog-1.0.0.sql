--liquibase formatted sql

--changeset medeiros:1
CREATE TABLE jokenpo.jogador
(
    id   int NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

--changeset medeiros:2
CREATE TABLE jokenpo.jogo
(
    id   int NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

--changeset medeiros:3
CREATE TABLE jokenpo.jogada
(
    id           int NOT NULL AUTO_INCREMENT,
    id_jogador int NOT NULL,
    id_jogo    int NOT NULL,
    jogada       VARCHAR(30),
    PRIMARY KEY (id)
);

ALTER TABLE jokenpo.partida
    ADD FOREIGN KEY (id_jogador) REFERENCES jokenpo.jogador (id);
ALTER TABLE jokenpo.partida
    ADD FOREIGN KEY (id_jogo) REFERENCES jokenpo.jogo (id);

