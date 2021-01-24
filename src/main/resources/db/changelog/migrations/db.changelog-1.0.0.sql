--liquibase formatted sql

--changeset medeiros:1
CREATE TABLE jokenpo.jogador
(
    id   int NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

--changeset medeiros:2
CREATE TABLE jokenpo.partida
(
    id   int NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

--changeset medeiros:3
CREATE TABLE jokenpo.jogada
(
    id         int NOT NULL AUTO_INCREMENT,
    id_jogador int NOT NULL,
    id_jogo    int NOT NULL,
    jogada     VARCHAR(30),
    PRIMARY KEY (id)
);

ALTER TABLE jokenpo.jogada
    ADD FOREIGN KEY (id_jogador) REFERENCES jokenpo.jogador (id);
ALTER TABLE jokenpo.jogada
    ADD FOREIGN KEY (id_jogo) REFERENCES jokenpo.partida (id);


--changeset medeiros:4
ALTER TABLE jokenpo.partida
    ADD ganhador INT;
ALTER TABLE jokenpo.partida
    ADD FOREIGN KEY (ganhador) REFERENCES jokenpo.jogador (id);

--changeset medeiros:5
ALTER TABLE jokenpo.jogador
    ADD ativo BIT

--changeset medeiros:6
ALTER TABLE jokenpo.partida
    ADD ativo BIT



