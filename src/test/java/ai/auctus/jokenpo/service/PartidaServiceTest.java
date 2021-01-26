package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Turno;
import ai.auctus.jokenpo.helper.MessageHelper;
import ai.auctus.jokenpo.repository.JogadaRepository;
import ai.auctus.jokenpo.repository.TurnoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static ai.auctus.jokenpo.service.JokenpoEnum.LAGARATO;
import static ai.auctus.jokenpo.service.JokenpoEnum.PAPEL;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PartidaServiceTest {

    @InjectMocks
    private PartidaService partidaService;
    @Mock
    private MessageHelper messageHelper;
    @Mock
    private TurnoRepository turnoRepository;
    @Mock
    private JogadaRepository jogadaRepository;


    @Test
    void achaVencedorPapel() {
        final var idJogadorVencedor = 7L;
        final var idPartida = 10L;
        final var turno = Turno
                .builder()
                .id(10L)
                .finalizado(FALSE)
                .build();

        final var jogador1 = Jogador
                .builder()
                .ativo(TRUE)
                .id(idJogadorVencedor)
                .build();
        final var jogada1 = Jogada
                .builder()
                .jogada(PAPEL)
                .jogador(jogador1)
                .build();

        final var jogador2 = Jogador
                .builder()
                .ativo(TRUE)
                .id(8L)
                .build();

        final var jogada2 = Jogada
                .builder()
                .jogada(JokenpoEnum.PEDRA)
                .jogador(jogador2)
                .build();


        final var jogador3 = Jogador
                .builder()
                .ativo(TRUE)
                .id(4L)
                .build();

        final var jogada3 = Jogada
                .builder()
                .jogada(JokenpoEnum.SPOCK)
                .jogador(jogador2)
                .build();

        when(this.turnoRepository.buscarUltimoTurnoSePartidaAtiva(idPartida, PageRequest.of(0, 1)))
                .thenReturn(List.of(turno));
        when(this.jogadaRepository.findJogadaByTurno(any()))
                .thenReturn(List.of(jogada1, jogada2, jogada3));

        var resultado = this.partidaService.resultado(idPartida);

        Assertions.assertEquals(PAPEL, resultado.getJokenpoEnum());
        Assertions.assertEquals(idJogadorVencedor, resultado.getIdJogadorVencedor());
    }


    @Test
    void achaEmpate() {
        final var mensagem = "Houve um empate, novo turno começa";
        final var idJogadorVencedor = 7L;
        final var idPartida = 10L;
        final var turno = Turno
                .builder()
                .id(10L)
                .finalizado(FALSE)
                .build();

        final var jogador1 = Jogador
                .builder()
                .ativo(TRUE)
                .id(idJogadorVencedor)
                .build();
        final var jogada1 = Jogada
                .builder()
                .jogada(LAGARATO)
                .jogador(jogador1)
                .build();

        final var jogador2 = Jogador
                .builder()
                .ativo(TRUE)
                .id(8L)
                .build();

        final var jogada2 = Jogada
                .builder()
                .jogada(JokenpoEnum.PEDRA)
                .jogador(jogador2)
                .build();


        final var jogador3 = Jogador
                .builder()
                .ativo(TRUE)
                .id(4L)
                .build();

        final var jogada3 = Jogada
                .builder()
                .jogada(JokenpoEnum.SPOCK)
                .jogador(jogador2)
                .build();

        when(this.turnoRepository.buscarUltimoTurnoSePartidaAtiva(idPartida, PageRequest.of(0, 1)))
                .thenReturn(List.of(turno));
        when(this.jogadaRepository.findJogadaByTurno(any()))
                .thenReturn(List.of(jogada1, jogada2, jogada3));

        var resultado = this.partidaService.resultado(idPartida);
        Assertions.assertNotNull(resultado.getMensagem());
        Assertions.assertEquals(mensagem, resultado.getMensagem());
        Assertions.assertNull(resultado.getJokenpoEnum());
        Assertions.assertNull(resultado.getIdJogadorVencedor());
    }
}
