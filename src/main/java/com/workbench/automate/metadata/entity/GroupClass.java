package com.workbench.automate.metadata.entity;

import lombok.Builder;

@Builder
public class GroupClass {

    //group_id	label_id	code	entities	category	parent_group	version	repeatable	group_by_key	rindex	isAccordion	isDynamicButtons	identifier
    private String group_id;
    private String label_id;
    private String code;
    private String entities;
    private String category;
    private String parent_group;
    private String version;
    private String repeatable;
    private String group_by_key;

    private String isAccordion;


    public GroupClass(String group_id, String label_id, String code, String entities, String category, String parent_group, String version, String repeatable, String group_by_key,String isAccordion) {
        this.group_id = group_id;
        this.label_id = label_id;
        this.code = code;
        this.entities = entities;
        this.category = category;
        this.parent_group = parent_group;
        this.version = version;
        this.repeatable = repeatable;
        this.group_by_key = group_by_key;

        this.isAccordion = isAccordion;

    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEntities() {
        return entities;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParent_group() {
        return parent_group;
    }

    public void setParent_group(String parent_group) {
        this.parent_group = parent_group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(String repeatable) {
        this.repeatable = repeatable;
    }

    public String getGroup_by_key() {
        return group_by_key;
    }

    public void setGroup_by_key(String group_by_key) {
        this.group_by_key = group_by_key;
    }



    public String getIsAccordion() {
        return isAccordion;
    }

    public void setIsAccordion(String isAccordion) {
        this.isAccordion = isAccordion;
    }





    @Override
    public String toString() {
        return "GroupClass{" +
                "group_id='" + group_id + '\'' +
                ", label_id='" + label_id + '\'' +
                ", code='" + code + '\'' +
                ", entities='" + entities + '\'' +
                ", category='" + category + '\'' +
                ", parent_group='" + parent_group + '\'' +
                ", version='" + version + '\'' +
                ", repeatable='" + repeatable + '\'' +
                ", group_by_key='" + group_by_key + '\'' +
                ", isAccordion='" + isAccordion + '\'' +
                '}';
    }
}
