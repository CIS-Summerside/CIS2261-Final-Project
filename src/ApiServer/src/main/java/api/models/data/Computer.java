package api.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Connor on 2016-01-30.
 */
@Entity
@Table(name = "computers")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "computer_id")
    private Long computerId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "identifier_raw")
    private String identifierRaw;

    @Basic(optional = false)
    @NotNull
    @Size(min = 64, max = 64)
    @Column(name = "identifier_Hash")
    private String identifierHash;

    @Basic(optional = true)
    @Temporal(TemporalType.TIME)
    @Column(name = "log_time", insertable = false, updatable = false)
    private Date logTime;

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "log_date", insertable = false, updatable = false)
    private Date logDate;

    public Computer(){

    }

    @JsonIgnore
    public Long getComputerId(){
        return this.computerId;
    }
    @JsonIgnore
    public void setComputerId(Long computerId){
        this.computerId = computerId;
    }

    @JsonIgnore
    public String getIdentifierRaw() {
        return identifierRaw;
    }
    @JsonProperty
    public void setIdentifierRaw(String identifierRaw) {
        this.identifierRaw = identifierRaw;
    }

    @JsonIgnore
    public String getIdentifierHash() {
        return identifierHash;
    }
    @JsonIgnore
    public void setIdentifierHash(String identifierHash) {
        this.identifierHash = identifierHash;
    }

    @JsonIgnore
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    @JsonIgnore
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @JsonIgnore
    public Date getLogDate() {
        return logDate;
    }
    @JsonIgnore
    public Date getLogTime() {
        return logTime;
    }
}
