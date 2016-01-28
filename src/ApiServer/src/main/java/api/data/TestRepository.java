package api.data;

import api.model.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Connor on 2016-01-27.
 */
@Repository
public interface TestRepository extends CrudRepository<Test, Integer>{

    Test findOneById(Integer id);
}
