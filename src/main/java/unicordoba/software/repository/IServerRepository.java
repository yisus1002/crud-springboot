package unicordoba.software.repository;

import org.springframework.data.repository.CrudRepository;

import unicordoba.software.models.Server;

public interface IServerRepository extends CrudRepository<Server, Long>{
    
}
