package api.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-03
 */
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "token_id")
    private Long tokenId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "token")
    private String token = UUID.randomUUID().toString();

    public Token(Long id){
        this.userId = id;
    }

    public Token(){

    }

    @JsonIgnore
    public Long getTokenId() {
        return tokenId;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }

    @JsonIgnore
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
