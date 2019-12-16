package org.neuedu.vblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Column {
    private Integer id;
    private String colname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date enabletime;
    private Integer counts;

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public Date getEnabletime() {
        return enabletime;
    }

    public void setEnabletime(Date enabletime) {
        this.enabletime = enabletime;
    }
}
