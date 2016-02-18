package api.repositories;

import api.models.data.Report;
import api.models.data.Token;
import api.models.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-03
 */
@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findOneByUser(User user);
    Token findOneByToken(String token);
}
