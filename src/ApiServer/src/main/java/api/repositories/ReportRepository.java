package api.repositories;

import api.models.data.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Connor on 2016-01-30.
 */
@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

}
