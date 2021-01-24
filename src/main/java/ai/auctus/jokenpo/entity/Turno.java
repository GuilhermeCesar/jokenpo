package ai.auctus.jokenpo.entity;

import lombok.*;

import javax.persistence.*;

import static java.lang.Boolean.FALSE;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_partida")
    private Partida partida;

    @Builder.Default
    @Column
    private Boolean finalizado = FALSE;
}
