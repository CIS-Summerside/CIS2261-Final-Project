package api.models.authentication;

import api.models.data.Token;
import api.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-04
 */
public class Authentication {
    @Autowired TokenRepository tr;

    public Boolean getBasicAuth(String tokenUUID){
        Token token = tr.findOneByToken(tokenUUID);

        if(token != null){
            return true;
        }
        return false;
    }
}
