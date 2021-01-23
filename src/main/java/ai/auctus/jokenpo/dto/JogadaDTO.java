package ai.auctus.jokenpo.dto;

import ai.auctus.jokenpo.service.JokenpoEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = JogadaDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Mensagem de erro padrão do sistema")
public class JogadaDTO {

    JokenpoEnum jokenpoEnum;
    Long idJogador;
    Long idJogo;
    Long idJogada;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
