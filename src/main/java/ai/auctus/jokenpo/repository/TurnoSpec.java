package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Turno;
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
public class TurnoSpec implements Specification<Turno> {

    @Builder.Default
    private transient Optional<String> nome = Optional.empty();
    @Builder.Default
    private transient Optional<Boolean> ativo = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Turno> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predcs = new ArrayList<>();

        this.nome.ifPresent(nome -> predcs.add(builder.equal(root.get("nome"), nome)));
        this.ativo.ifPresent(ativo -> predcs.add(builder.equal(root.get("ativo"), ativo)));

//        this.refinancingStatusOptional.ifPresent(refinancingStatus -> {
//            var statusPath = join.get("status");
//            var predStatus = builder.isTrue(statusPath.in(refinancingStatus));
//            predcs.add(predStatus);
//        });
//        crit

        return builder.and(predcs.toArray(new Predicate[predcs.size()]));
    }
}
