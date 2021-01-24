package ai.auctus.jokenpo.entity;

import lombok.*;

import javax.persistence.*;

import static java.lang.Boolean.TRUE;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@Entity
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ganhador")
    private Jogador ganhador;

    @Builder.Default
    @Column
    private Boolean ativo = TRUE;
}
