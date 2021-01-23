package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogadorDTO;
import ai.auctus.jokenpo.dto.JogadorDTO;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorDTO cadastrarJogador(CadastroJogadorDTO cadastroJogadorDTO) {
        final var jogador = Jogador.builder()
                .nome(cadastroJogadorDTO.getNome())
                .build();

        this.jogadorRepository.save(jogador);

        return JogadorDTO
                .builder()
                .idJogador(jogador.getId())
                .jogador(jogador.getNome())
                .ativo(jogador.getAtivo())
                .build();
    }
}
