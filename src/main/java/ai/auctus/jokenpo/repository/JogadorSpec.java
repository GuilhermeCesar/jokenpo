package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Jogador;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Value
@With
@Builder
public class JogadorSpec implements Specification<Jogador> {

    @Builder.Default
    private transient Optional<String> nome = Optional.empty();
    @Builder.Default
    private transient Optional<Boolean> ativo = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Jogador> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predcs = new ArrayList<>();

        this.nome.ifPresent(nome -> predcs.add(builder.equal(root.get("nome"), nome)));
        this.ativo.ifPresent(ativo -> predcs.add(builder.equal(root.get("ativo"), ativo)));

        return builder.and(predcs.toArray(new Predicate[predcs.size()]));
    }
}
