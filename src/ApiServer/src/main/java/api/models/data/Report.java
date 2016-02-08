package api.models.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Connor on 2016-01-30.
 */
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "report_id")
    private Long reportId;

    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File fileId;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "report_details")
    private String details;

    @Basic(optional = false)
    @Size(min = 1)
    @NotNull
    @Column(name = "report_time")
    private String originalName;

    public Report(){

    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public File getFileId() {
        return fileId;
    }

    public void setFileId(File fileId) {
        this.fileId = fileId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
