package api.models.data;

import api.models.base.ApiBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Connor on 2016-01-28.
 */
@Entity
@Table(name = "users")
public class User extends ApiBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;

    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @NotNull
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Size(min = 24, max = 24)
    @NotNull
    @Column(name = "password_salt")
    private String passwordSalt;

    @Basic(optional = false)
    @Size(min = 64, max = 64)
    @NotNull
    @Column(name = "password_hash")
    private String passwordHash;

    @Basic(optional = false)
    @Size(min = 7, max = 25)
    @NotNull
    @Column(name = "email")
    private String email;

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date", insertable = false, updatable = false)
    private Date regDate;


    public User(){

    }

    @JsonProperty
    public Long getId(){
        return this.userId;
    }
    @JsonIgnore
    public void setId(Long userId){
        this.userId = userId;
    }

    @JsonProperty
    public String getUsername(){
        return this.username;
    }
    @JsonProperty
    public void setUsername(String username){
        this.username = username;
    }

    @JsonIgnore
    public String getPasswordSalt(){
        return this.passwordSalt;
    }
    @JsonIgnore
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

    @JsonProperty
    public Date getRegDate(){
        return this.regDate;
    }
}
