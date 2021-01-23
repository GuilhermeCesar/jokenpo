package ai.auctus.jokenpo.resource;


import ai.auctus.jokenpo.config.SwaggerConfig;
import ai.auctus.jokenpo.dto.CadastroJogadorDTO;
import ai.auctus.jokenpo.dto.ErrorMessage;
import ai.auctus.jokenpo.dto.JogadorDTO;
import ai.auctus.jokenpo.service.JogadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/jogador", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.JOGADOR)
public class JogadorResource {

    private final JogadorService jogadorService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "${api.jogador.criacao}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = JogadorDTO.class),
            @ApiResponse(code = 500, message = "Falha ao cadastrar jogador", response = ErrorMessage.class)
    })
    @PostMapping
    public JogadorDTO create(@Valid @RequestBody CadastroJogadorDTO cadastroJogadorDTO) {
        return this.jogadorService.cadastrarJogador(cadastroJogadorDTO);
    }
}
