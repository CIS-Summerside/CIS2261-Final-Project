package api.models.data;

import api.models.base.ApiBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Connor on 2016-01-28.
 */
@Entity
@Table(name = "users")
public class User extends ApiBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Size(min = 16, max = 16)
    @Column(name = "password_salt")
    private String passwordSalt;

    @Basic(optional = false)
    @Size(min = 64, max = 64)
    @Column(name = "password_hash")
    private String passwordHash;

    @Basic(optional = false)
    @Size(min = 7, max = 25)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "registration_date")
    private String regDate;

    public User(){

    }

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    @JsonIgnore
    public String getPasswordSalt(){
        return this.passwordSalt;
    }
    @JsonProperty
    public void setPasswordSalt(String passwordSalt){
        this.passwordSalt = passwordSalt;
    }

    @JsonIgnore
    public String getPasswordHash(){
        return this.passwordHash;
    }
    @JsonProperty
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

    @JsonIgnore
    public String getEmail(){
        return this.email;
    }
    @JsonProperty
    public void setEmail(String email){
        this.email = email;
    }

    public String getRegDate(){
        return this.regDate;
    }
    public void setRegDate(String regDate){
        this.regDate = regDate;
    }
}
