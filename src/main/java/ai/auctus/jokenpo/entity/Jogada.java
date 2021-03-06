package ai.auctus.jokenpo.entity;


import ai.auctus.jokenpo.service.JokenpoEnum;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "jogada")
public class Jogada {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private JokenpoEnum jogada;

    @ManyToOne
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;

    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno turno;

    public Boolean venceOuEmpata(JokenpoEnum jogada){
        return this.jogada.venceOuEmpata(jogada);
    }
}
