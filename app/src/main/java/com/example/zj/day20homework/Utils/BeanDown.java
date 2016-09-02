package com.example.zj.day20homework.Utils;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class BeanDown {
    String subject;
    String summary;
    String cover;

    public BeanDown(String subject, String summary, String cover) {
        this.subject = subject;
        this.summary = summary;
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "BeanDown{" +
                "subject='" + subject + '\'' +
                ", summary='" + summary + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
