package unicordoba.software.repository;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import unicordoba.software.models.Aplication;

public class AplicationSpecs {
    public static Specification<Aplication> getAplicationByName(String name){
        return new Specification<Aplication>(){

            @Override
            public Predicate toPredicate(Root<Aplication> root, 
                                        CriteriaQuery<?> query,
                                        CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate= criteriaBuilder.equal(root.get("nombre"), name);
                return equalPredicate;
            }

        };

    }

    public static Specification<Aplication> getAplicationServerBySO(String so){
        return new Specification<Aplication>(){

            @Override
            public Predicate toPredicate(Root<Aplication> root, 
                                        CriteriaQuery<?> query,
                                        CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("server").get("so"), so);
                return equalPredicate;
            }

        };

    }  
}
