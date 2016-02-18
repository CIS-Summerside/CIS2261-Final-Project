package api.repositories;

import api.models.data.Computer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Connor on 2016-01-30.
 */
@Repository
public interface ComputerRepository extends CrudRepository<Computer, Long> {
    Computer findOneByIdentifierHash(String identifierHash);
}
