package ai.auctus.jokenpo.resource;


import ai.auctus.jokenpo.config.SwaggerConfig;
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
@Api(tags = SwaggerConfig.SwaggerTags.ASSOCIADO)
public class JogadorResource {


    @ResponseStatus(CREATED)
    @ApiOperation(value = "${api.sessao.criacao}")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "Sucesso", response = SessaoDTO.class),
//            @ApiResponse(code = 500, message = "Falha ao inserir sessão", response = ErrorMessage.class)
//    })
    @PostMapping
    public void create(@Valid @RequestBody String sessaoDTO) {

    }
}
