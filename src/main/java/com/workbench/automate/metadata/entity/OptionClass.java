package com.workbench.automate.metadata.entity;

import lombok.Builder;

@Builder
public class OptionClass {

    private String id;
    private String entity_id;
    private String version;
    private String code;

    public OptionClass(String id, String entity_id, String version, String code) {
        this.id = id;
        this.entity_id = entity_id;
        this.version = version;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Option_Metadata{" +
                "id='" + id + '\'' +
                ", entity_id='" + entity_id + '\'' +
                ", version='" + version + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
