package api.models.data;

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
    @Column(name = "expiration_time", insertable = false, updatable = false)
    private Date expirationTime;

    @Basic(optional = true)
    @Temporal(TemporalType.TIME)
    @Column(name = "upload_time", insertable = false, updatable = false)
    private Date uploadTime;

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "upload_date", insertable = false, updatable = false)
    private Date uploadDate;

    public File(){

    }

    public Long getFileId() {
        return fileId;
    }
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getOriginalName() {
        return originalName;
    }
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getStoredName() {
        return storedName;
    }
    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }

    public String getDownloadCode() {
        return downloadCode;
    }
    public void setDownloadCode(String downloadCode) {
        this.downloadCode = downloadCode;
    }

    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Byte getFileStatus() {
        return fileStatus;
    }
    public void setFileStatus(Byte fileStatus) {
        this.fileStatus = fileStatus;
    }

    public Byte getFileAccess() {
        return fileAccess;
    }
    public void setFileAccess(Byte fileAccess) {
        this.fileAccess = fileAccess;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }
    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getUploadTime() {
        return uploadTime;
    }
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
