package api.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Connor on 2016-01-30.
 */
@Entity
@Table(name = "useragents")
public class UserAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "agent_id")
    private Long agentId;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "ua_raw")
    private String agentRaw;

    @Basic(optional = false)
    @Size(min = 40, max = 40)
    @NotNull
    @Column(name = "ua_hash")
    private String agentHash;

    public UserAgent(){

    }

    @JsonIgnore
    public Long getAgentId(){
        return this.agentId;
    }
    @JsonProperty
    public void setAgentId(Long agentId){
        this.agentId = agentId;
    }

    @JsonIgnore
    public String getAgentRaw(){
        return this.agentRaw;
    }
    @JsonProperty
    public void setAgentRaw(String agentRaw){
        this.agentRaw = agentRaw;
    }

    @JsonIgnore
    public String getAgentHash(){
        return this.agentHash;
    }
    @JsonProperty
    public void setAgentHash(String agentHash){
        this.agentHash = agentHash;
    }
}
