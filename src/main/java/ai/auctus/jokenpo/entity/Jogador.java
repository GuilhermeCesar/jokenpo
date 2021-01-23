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
@Table(name = "jogador")
@ToString
public class Jogador {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Builder.Default
    @Column
    private Boolean ativo = TRUE;
}
