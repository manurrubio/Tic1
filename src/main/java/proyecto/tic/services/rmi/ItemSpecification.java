package proyecto.tic.services.rmi;

import org.springframework.data.jpa.domain.Specification;
import proyecto.tic.services.entities.Item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification implements Specification<Item> {
    private String type;
    private String color;
    private String size;
    private String style;
    private String category;
    private String brand;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates=new ArrayList<>();
        if(type!=null){
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if(color!=null){
            predicates.add(criteriaBuilder.equal(root.get("color"), color));
        }
        if(size!=null){
            predicates.add(criteriaBuilder.equal(root.get("size"), size));
        }
        if(style!=null){
            predicates.add(criteriaBuilder.equal(root.get("style"), style));
        }
        if(category!=null){
            predicates.add(criteriaBuilder.equal(root.get("category"), category));
        }
        if(brand!=null){
            predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
