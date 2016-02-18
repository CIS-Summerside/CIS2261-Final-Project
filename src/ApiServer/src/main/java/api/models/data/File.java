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
@Table(name = "file_details")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_id")
    private Long fileId;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "original_name")
    private String originalName;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "stored_name")
    private String storedName;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "download_code")
    private String downloadCode;

    @Basic(optional = false)
    @NotNull
    @Column(name = "file_size")
    private Long fileSize;

    @Basic(optional = false)
    @NotNull
    @Column(name = "file_status")
    private Byte fileStatus;

    @Basic(optional = false)
    @NotNull
    @Column(name = "file_access")
    private Byte fileAccess;

    @Basic(optional = true)
    @Temporal(TemporalType.TIME)
    @Column(name = "expiration_time", insertable = true, updatable = false)
    private Date expirationTime = new Date(System.currentTimeMillis()+(5*60*1000));

    @Basic(optional = true)
    @Temporal(TemporalType.TIME)
    @Column(name = "upload_time", insertable = true, updatable = false)
    private Date uploadTime = new Date(System.currentTimeMillis());

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "upload_date", insertable = true, updatable = false)
    private Date uploadDate = new Date(System.currentTimeMillis());

    public File(){

    }

    @JsonProperty
    public Long getFileId() {
        return fileId;
    }
    @JsonIgnore
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @JsonProperty
    public String getOriginalName() {
        return originalName;
    }
    @JsonIgnore
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @JsonIgnore
    public String getStoredName() {
        return storedName;
    }
    @JsonProperty
    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }

    @JsonProperty
    public String getDownloadCode() {
        return downloadCode;
    }
    @JsonIgnore
    public void setDownloadCode(String downloadCode) {
        this.downloadCode = downloadCode;
    }

    @JsonProperty
    public Long getFileSize() {
        return fileSize;
    }
    @JsonProperty
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @JsonIgnore
    public Byte getFileStatus() {
        return fileStatus;
    }
    @JsonProperty
    public void setFileStatus(Byte fileStatus) {
        this.fileStatus = fileStatus;
    }

    @JsonIgnore
    public Byte getFileAccess() {
        return fileAccess;
    }
    public void setFileAccess(Byte fileAccess) {
        this.fileAccess = fileAccess;
    }

    @JsonProperty
    public Date getExpirationTime() {
        return expirationTime;
    }
    @JsonProperty
    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    @JsonProperty
    public Date getUploadDate() {
        return uploadDate;
    }
    @JsonIgnore
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @JsonProperty
    public Date getUploadTime() {
        return uploadTime;
    }
    @JsonIgnore
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
