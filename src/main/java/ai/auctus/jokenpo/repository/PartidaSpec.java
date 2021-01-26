package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Partida;
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
public class PartidaSpec implements Specification<Partida> {

    @Builder.Default
    private transient Optional<Long> id = Optional.empty();
    @Builder.Default
    private transient Optional<Boolean> ativo = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Partida> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predcs = new ArrayList<>();

        this.id.ifPresent(id -> predcs.add(builder.equal(root.get("id"), id)));
        this.ativo.ifPresent(ativo -> predcs.add(builder.equal(root.get("ativo"), ativo)));

        return builder.and(predcs.toArray(new Predicate[predcs.size()]));
    }
}
