package ai.auctus.jokenpo.dto;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Cadastro de jogador")
public class CadastroJogadorDTO {

    @NotNull
    String nome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
