package ai.auctus.jokenpo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = PartidaDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Mensagem de erro padrão do sistema")
public class PartidaDTO {

    Long idJogo;
    String nome;
    Boolean ativa;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
