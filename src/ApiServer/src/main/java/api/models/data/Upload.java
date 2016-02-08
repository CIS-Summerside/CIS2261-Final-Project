package api.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Connor on 2016-02-07.
 */
@Entity
@Table(name = "uploads")
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "upload_id")
    private Long uploadId;

    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File fileId;

    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computerId;

    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public Upload(){

    }

    @JsonIgnore
    public Long getUploadId() {
        return uploadId;
    }

    @JsonIgnore
    public File getFileId() {
        return fileId;
    }
    @JsonIgnore
    public void setFileId(File fileId) {
        this.fileId = fileId;
    }

    @JsonIgnore
    public Computer getComputerId() {
        return computerId;
    }
    @JsonIgnore
    public void setComputerId(Computer computerId) {
        this.computerId = computerId;
    }

    @JsonIgnore
    public User getUserId() {
        return userId;
    }
    @JsonIgnore
    public void setUserId(User userId) {
        this.userId = userId;
    }
}
