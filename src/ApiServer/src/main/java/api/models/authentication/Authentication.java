package api.models.authentication;

import api.models.data.Token;
import api.models.data.User;
import api.repositories.TokenRepository;
import api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-04
 */
public class Authentication {
    @Autowired TokenRepository tr;
    @Autowired UserRepository ur;

    public Boolean getBasicAuth(String tokenUUID){
        Token token = tr.findOneByToken(tokenUUID);

        if(token != null){
            return true;
        }
        return false;
    }

    public Long getUserId(String tokenUUID){
        Token token = tr.findOneByToken(tokenUUID);
        return token.getUser().getId();
    }
}
