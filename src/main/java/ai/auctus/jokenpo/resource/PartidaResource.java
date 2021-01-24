package ai.auctus.jokenpo.resource;

import ai.auctus.jokenpo.dto.*;
import ai.auctus.jokenpo.service.PartidaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
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

    private final PartidaService jogoService;

    @ApiOperation(value = "Criar jogo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping
    public JogoDTO criar(@Valid @RequestBody CadastroJogoDTO cadastroJogadorDTO) {
        return this.jogoService.cadastrarJogo(cadastroJogadorDTO);
    }

    @ApiOperation(value = "Jogar")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadaDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping("/jogar/{idJogo}")
    public JogadaDTO jogar(@PathVariable("idJogo") Long idJogo, @RequestBody FazerJogadaDTO fazerJogadaDTO) {
        return this.jogoService.jogar(idJogo, fazerJogadaDTO);
    }
}
