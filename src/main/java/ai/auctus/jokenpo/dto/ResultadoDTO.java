package ai.auctus.jokenpo.dto;

import ai.auctus.jokenpo.service.JokenpoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(builderClassName = "JacksonBuilder")
public class ResultadoDTO {

    private Long idJogadorVencedor;
    private JokenpoEnum jokenpoEnum;
    private Long idPartida;
    private String mensagem;
}
