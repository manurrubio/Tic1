package proyecto.tic.services.entities;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification implements Specification<Item> {
    private Brand brand;
    private String type;

    public ItemSpecification (Brand brand, String type){
        this.brand = brand;
        this.type = type;
    }
    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (brand != null) {
            predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
        }
        if (type != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    }

