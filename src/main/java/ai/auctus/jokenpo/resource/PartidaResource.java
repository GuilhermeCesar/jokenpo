package ai.auctus.jokenpo.resource;

import ai.auctus.jokenpo.dto.*;
import ai.auctus.jokenpo.service.PartidaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static ai.auctus.jokenpo.config.SwaggerConfig.SwaggerTags.JOGO;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/partida", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = JOGO)
public class PartidaResource {

    private final PartidaService partidaService;

    @ApiOperation(value = "Criar jogo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping
    public PartidaDTO criar(@Valid @RequestBody CadastroJogoDTO cadastroJogadorDTO) {
        return this.partidaService.cadastrarJogo(cadastroJogadorDTO);
    }

    @ApiOperation(value = "Jogar")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadaDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping("/{idPartida}/jogar")
    public JogadaDTO jogar(@PathVariable("idPartida") Long idPartida, @RequestBody FazerJogadaDTO fazerJogadaDTO) {
        return this.partidaService.jogar(idPartida, fazerJogadaDTO);
    }

    @ApiOperation(value = "Retorna o resultado do jogo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = ResultadoDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @GetMapping("/{idPartida}/resultado")
    public ResultadoDTO buscaResultado(@PathVariable("idPartida") Long idPartida) {
        return this.partidaService.resultado(idPartida);
    }

    @ApiOperation(value = "Busca partida por filtro")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = Page.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @GetMapping("/busca")
    public Page<PartidaDTO> buscaPartidaPorFiltro(@RequestParam(name = "idPartida", required = false) Long idJogador,
                                                  @RequestParam(name = "ativo", required = false) Boolean ativo,
                                                  Pageable pageable) {
        return this.partidaService.getPartida(idJogador, ativo, pageable);
    }
}
