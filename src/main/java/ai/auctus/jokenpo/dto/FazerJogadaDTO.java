package ai.auctus.jokenpo.dto;

import ai.auctus.jokenpo.service.JokenpoEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;

@Value
@With
@JsonDeserialize(builder = FazerJogadaDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Mensagem de erro padrão do sistema")
public class FazerJogadaDTO {

    @NotNull
    JokenpoEnum jokenpoEnum;
    @NotNull
    Long idJogador;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
