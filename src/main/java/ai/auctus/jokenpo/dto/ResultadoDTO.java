package ai.auctus.jokenpo.dto;

import ai.auctus.jokenpo.service.JokenpoEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Value
@With
@JsonDeserialize(builder = PartidaDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Mensagem de erro padrão do sistema")
@AllArgsConstructor
public class ResultadoDTO {

    Long idJogadorGanhador;
    JokenpoEnum jokenpoEnum;
    Long idPartida;
    String mensagem;

    public ResultadoDTO(Long idJogadorGanhador, JokenpoEnum jokenpoEnum, Long idPartida) {
        this.idJogadorGanhador = idJogadorGanhador;
        this.jokenpoEnum = jokenpoEnum;
        this.idPartida = idPartida;
        this.mensagem = "";
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
