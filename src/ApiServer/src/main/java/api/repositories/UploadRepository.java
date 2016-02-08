package api.repositories;

import api.models.data.Upload;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Connor on 2016-02-07.
 */
@Repository
public interface UploadRepository extends CrudRepository<Upload, Long> {
    
}
