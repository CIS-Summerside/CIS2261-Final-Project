package api.models.data;

import api.models.base.ApiResponse;
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
    @Size(max = 17)
    @NotNull
    @Column(name = "mac_address")
    private String macAddress;

    @Basic(optional = false)
    @Size(min = 1, max = 150)
    @NotNull
    @Column(name = "bios_serial")
    private String biosSerial = "";

    @Basic(optional = false)
    @Size(min = 1, max = 150)
    @NotNull
    @Column(name = "operating_system")
    private String operatingSystem;

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
    @JsonProperty
    public void setComputerId(Long computerId){
        this.computerId = computerId;
    }

    @JsonIgnore
    public String getMacAddress(){
        return this.macAddress;
    }
    @JsonProperty
    public void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }

    @JsonIgnore
    public String getBiosSerial(){
        return this.biosSerial;
    }
    @JsonProperty
    public void setBiosSerial(String biosSerial){
        this.biosSerial = biosSerial;
    }

    @JsonIgnore
    public String getOperatingSystem(){
        return this.operatingSystem;
    }
    @JsonProperty
    public void setOperatingSystem(String operatingSystem){
        this.operatingSystem = operatingSystem;
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
