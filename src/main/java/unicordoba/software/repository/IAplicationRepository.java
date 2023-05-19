package unicordoba.software.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import unicordoba.software.models.Aplication;

public interface IAplicationRepository extends CrudRepository<Aplication, Long>, JpaSpecificationExecutor<Aplication>{
    
}
