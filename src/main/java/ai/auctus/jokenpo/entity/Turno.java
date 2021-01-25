package ai.auctus.jokenpo.entity;

import lombok.*;

import javax.persistence.*;

import static java.lang.Boolean.FALSE;
import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.data.mapping.Alias.ofNullable;

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

    public Long getIdPartida() {
        return ofNullable(this.partida).isPresent() ? this.partida.getId() : null;
    }
}
