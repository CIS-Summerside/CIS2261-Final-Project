package api.repositories;

import api.models.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Connor on 2016-01-27.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    User findOneById(Integer id);
}
