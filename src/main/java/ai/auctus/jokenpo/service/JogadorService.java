package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogadorDTO;
import ai.auctus.jokenpo.dto.JogadorDTO;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.exception.JogadorException;
import ai.auctus.jokenpo.repository.JogadorRepository;
import ai.auctus.jokenpo.repository.JogadorSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorDTO cadastrarJogador(CadastroJogadorDTO cadastroJogadorDTO) {
        final var jogador = Jogador.builder()
                .nome(cadastroJogadorDTO.getNome())
                .build();

        this.jogadorRepository.save(jogador);

        return getJogadorDTO(jogador);
    }

    private JogadorDTO getJogadorDTO(Jogador jogador) {
        return JogadorDTO
                .builder()
                .idJogador(jogador.getId())
                .nome(jogador.getNome())
                .ativo(jogador.getAtivo())
                .build();
    }

    public JogadorDTO buscaJogador(Long idJogador) {
        var jogador = this.jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new JogadorException(HttpStatus.NOT_FOUND, "Jogador não encontrado"));

        return getJogadorDTO(jogador);
    }

    public JogadorDTO inativarJogador(Long idJogador) {
        var jogador = this.jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new JogadorException(HttpStatus.NOT_FOUND, "Jogador não encontrado"));

        jogador = this.jogadorRepository.save(jogador.withAtivo(Boolean.FALSE));

        return getJogadorDTO(jogador);
    }

    public Page<JogadorDTO> buscaJogador(String nome, Boolean ativo, Pageable pageable) {
        final var jogadorSpec = JogadorSpec
                .builder()
                .ativo(ofNullable(ativo))
                .nome(ofNullable(nome))
                .build();
        final var jogadoresPage = this.jogadorRepository.findAll(jogadorSpec, pageable);

        return jogadoresPage
                .map(jogador -> JogadorDTO
                        .builder()
                        .ativo(jogador.getAtivo())
                        .idJogador(jogador.getId())
                        .nome(jogador.getNome())
                        .build());
    }
}
