package ai.auctus.jokenpo.resource;


import ai.auctus.jokenpo.dto.CadastroJogadorDTO;
import ai.auctus.jokenpo.dto.ErrorMessage;
import ai.auctus.jokenpo.dto.JogadorDTO;
import ai.auctus.jokenpo.service.JogadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static ai.auctus.jokenpo.config.SwaggerConfig.SwaggerTags.JOGADOR;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/jogador", produces = APPLICATION_JSON_VALUE)
@Api(tags = JOGADOR)
public class JogadorResource {

    private final JogadorService jogadorService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "${api.jogador.criacao}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping
    public JogadorDTO criar(@Valid @RequestBody CadastroJogadorDTO cadastroJogadorDTO) {
        return this.jogadorService.cadastrarJogador(cadastroJogadorDTO);
    }

    @ResponseStatus(OK)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @GetMapping("/{idJogador}")
    public JogadorDTO buscarJogador(@PathVariable("idJogador") Long idJogador) {
        return this.jogadorService.buscaJogador(idJogador);
    }

    @GetMapping("/find")
    public Page<JogadorDTO> findJogador(@RequestParam(name = "nome", required = false) String nome,
                                        @RequestParam(name = "ativo", required = false) Boolean ativo,
                                        Pageable pageable) {
        return this.jogadorService.buscaJogador(nome, ativo, pageable);
    }

    @ResponseStatus(OK)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao inativar", response = ErrorMessage.class)
    })
    @DeleteMapping("/{idJogador}")
    public JogadorDTO invativar(@PathVariable("idJogador") Long idJogador) {
        return this.jogadorService.inativarJogador(idJogador);
    }
}
