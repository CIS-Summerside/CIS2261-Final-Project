package api.repositories;

import api.models.data.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Connor on 2016-01-30.
 */
@Repository
public interface FileRepository extends CrudRepository<File, Long> {

}
