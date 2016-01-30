package api.models.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 1)
    @NotNull
    @Column(name = "file_size")
    private Long fileSize;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "file_status")
    private Byte fileStatus;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "file_access")
    private Byte fileAccess;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "expiration_time")
    private String expirationTime;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "upload_date")
    private String uploadDate;

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

    public String getExpirationTime() {
        return expirationTime;
    }
    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
