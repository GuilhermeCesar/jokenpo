package ai.auctus.jokenpo.dto;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(builderClassName = "JacksonBuilder")
public class JogadorDTO {

    Long idJogador;
    String nome;
    Boolean ativo;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
