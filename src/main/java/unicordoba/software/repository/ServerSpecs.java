package unicordoba.software.repository;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import unicordoba.software.models.Aplication;
import unicordoba.software.models.Server;

public class ServerSpecs {
    
    public static Specification<Server> getServerByMoreOneAplication(Long number){
        return new Specification<Server>(){

            @Override
            public Predicate toPredicate(Root<Server> root,
                                        CriteriaQuery<?> query,
                                        CriteriaBuilder criteriaBuilder) {
                        // Subconsulta para contar aplicaciones del servidor
                 Subquery<Long> subquery = query.subquery(Long.class);
                 Root<Server> subRoot = subquery.from(Server.class);

                 Join<Server, Aplication> join = subRoot.join("aplications");
                 //Se selecciona la cuenta de la subconsulta
                 subquery.select(criteriaBuilder.count(join));
                 //Se relaciona la subconsulta con la principal
                 subquery.where(criteriaBuilder.equal(subRoot, root));
    
                 Predicate predicate = criteriaBuilder.greaterThan(subquery, number);
                 return predicate;


            }

        };

    }


}
