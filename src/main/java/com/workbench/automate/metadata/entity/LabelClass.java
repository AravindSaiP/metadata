package com.workbench.automate.metadata.entity;

import lombok.Builder;

@Builder
public class LabelClass {

    private String text_id;
    private String en;
    private String fr_FR;
    private String ko;
    private String es;
    private String jp;
    private String version;
    private String de;
    private String fr;

    public LabelClass(String text_id, String en, String fr_FR, String ko, String es, String jp, String version, String de, String fr) {
        this.text_id = text_id;
        this.en = en;
        this.fr_FR = fr_FR;
        this.ko = ko;
        this.es = es;
        this.jp = jp;
        this.version = version;
        this.de = de;
        this.fr = fr;
    }

    public String getText_id() {
        return text_id;
    }

    public void setText_id(String text_id) {
        this.text_id = text_id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getFr_FR() {
        return fr_FR;
    }

    public void setFr_FR(String fr_FR) {
        this.fr_FR = fr_FR;
    }

    public String getKo() {
        return ko;
    }

    public void setKo(String ko) {
        this.ko = ko;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    @Override
    public String toString() {
        return "Labels_Metadata{" +
                "text_id='" + text_id + '\'' +
                ", en='" + en + '\'' +
                ", fr_FR='" + fr_FR + '\'' +
                ", ko='" + ko + '\'' +
                ", es='" + es + '\'' +
                ", jp='" + jp + '\'' +
                ", version='" + version + '\'' +
                ", de='" + de + '\'' +
                ", fr='" + fr + '\'' +
                '}';
    }
}
