package api.repositories;

import api.models.data.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Connor on 2016-01-30.
 */
@Repository
public interface FileRepository extends CrudRepository<File, Long> {

    @Query("select t from File t where t.fileStatus = 1 and t.fileAccess = 1")
    List<File> findAllActiveAndViewable();
}
