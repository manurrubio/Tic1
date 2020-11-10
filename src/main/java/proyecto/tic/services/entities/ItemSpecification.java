package proyecto.tic.services.entities;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification implements Specification<Item> {
    private String type, color, size, style, category;

    public ItemSpecification(String type, String color, String size, String style, String category) {
        this.type = type;
        this.color = color;
        this.size = size;
        this.style = style;
        this.category = category;
    }

    public ItemSpecification() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (type!=null)
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        if ( color!= null)
            predicates.add(criteriaBuilder.equal(root.get("color"), color));
        if ( size!= null)
            predicates.add(criteriaBuilder.equal(root.get("size"), size));
        if ( style!= null)
            predicates.add(criteriaBuilder.equal(root.get("style"), style));
        if ( category!= null)
            predicates.add(criteriaBuilder.equal(root.get("category"), category));
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return query.getRestriction();
    }

    @Override
    public Specification<Item> and(Specification<Item> other) {
        return null;
    }

    @Override
    public Specification<Item> or(Specification<Item> other) {
        return null;
    }

}

