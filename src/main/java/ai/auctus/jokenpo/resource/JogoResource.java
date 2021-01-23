package ai.auctus.jokenpo.resource;

import ai.auctus.jokenpo.config.SwaggerConfig;
import ai.auctus.jokenpo.dto.*;
import ai.auctus.jokenpo.service.JogoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/jogo", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.JOGO)
public class JogoResource {

    private final JogoService jogoService;

    @ApiOperation(value = "Criar jogo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping
    public JogoDTO criar(@Valid @RequestBody CadastroJogoDTO cadastroJogadorDTO) {
        return this.jogoService.cadastrarJogo(cadastroJogadorDTO);
    }


    @ApiOperation(value = "${api.jogador.criacao}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadaDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping("/jogar/{idJogo)")
    public JogadaDTO jogar(@PathVariable("idJogo") Long idJogo, @Valid @RequestBody FazerJogadaDTO fazerJogadaDTO) {
        return this.jogoService.jogar(idJogo, fazerJogadaDTO);
    }
}
