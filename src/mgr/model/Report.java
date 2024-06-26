package mgr.model;

import java.time.LocalDateTime;

/**
 *
 * @author ACER
 */
public class Report {
    private int id;
    private User user; //user yang melapor
    private String title; //nama barang yang hilang
    private String description; //deskripsi barang
    private LocalDateTime created_at; //tanggal laporan dibuat
    private LocalDateTime updated_at; //tanggal laporan diperbaharui
    private int status; //status barang (hilang=0 , ketemu=1, aman=2)
    private ReportState reportState;
    
    public enum ReportState {OPEN,CLOSE,CONFIRMED} //Status konfirmasi dari admin

    public Report(User user, String title, String description, int status, ReportState reportState) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.status = status;
        this.reportState = reportState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ReportState getReportState() {
        return reportState;
    }

    public void setReportState(ReportState reportState) {
        this.reportState = reportState;
    }
        
}
