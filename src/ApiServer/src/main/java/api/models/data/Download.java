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
@Table(name = "downloads")
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "download_id")
    private Long downloadId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "file_id")
    private Long fileId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "agent_id")
    private Long userAgentId;

    @Basic(optional = false)
    @Size(min = 7, max = 39)
    @NotNull
    @Column(name = "ip_address")
    private String ipAddress;

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "download_date", insertable = false, updatable = false)
    private Date downloadDate;

    @Basic(optional = true)
    @Temporal(TemporalType.TIME)
    @Column(name = "download_time", insertable = false, updatable = false)
    private Date downloadTime;

    public Download(){

    }

    public Long getDownloadId() {
        return downloadId;
    }
    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
    }

    @JsonIgnore
    public Long getFileId() {
        return fileId;
    }
    @JsonProperty
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @JsonIgnore
    public Long getUserAgentId() {
        return userAgentId;
    }
    @JsonProperty
    public void setUserAgentId(Long userAgentId) {
        this.userAgentId = userAgentId;
    }

    @JsonIgnore
    public String getIpAddress() {
        return ipAddress;
    }
    @JsonProperty
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @JsonIgnore
    public Date getDownloadTime() {
        return downloadTime;
    }
    @JsonIgnore
    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    @JsonIgnore
    public Date getDownloadDate() {
        return downloadDate;
    }
    @JsonIgnore
    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }
}
