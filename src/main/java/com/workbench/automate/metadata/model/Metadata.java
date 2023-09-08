package com.workbench.automate.metadata.model;


import java.util.Arrays;

public class Metadata {

    private String type;
    private String deleted;
    private String read_only;
    private String version;
    private String variable;
    private String variable_type;
    private String sub_type;
    private String option_values[];
    private String option_labels[];
    private String title;
    private String description;
    private String defaultValue;
    private String tooltip;
    private String dependencies[];

    private String accordion_name;
    private String parent_accordion_name;


    public Metadata(String type, String deleted, String read_only, String version, String variable, String variable_type, String sub_type, String[] option_values, String[] option_labels, String title, String description, String defaultValue, String tooltip, String[] dependencies, String accordion_name, String parent_accordion_name) {
        this.type = type;
        this.deleted = deleted;
        this.read_only = read_only;
        this.version = version;
        this.variable = variable;
        this.variable_type = variable_type;
        this.sub_type = sub_type;
        this.option_values = option_values;
        this.option_labels = option_labels;
        this.title = title;
        this.description = description;
        this.defaultValue = defaultValue;
        this.tooltip = tooltip;
        this.dependencies = dependencies;
        this.accordion_name = accordion_name;
        this.parent_accordion_name = parent_accordion_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getRead_only() {
        return read_only;
    }

    public void setRead_only(String read_only) {
        this.read_only = read_only;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getVariable_type() {
        return variable_type;
    }

    public void setVariable_type(String variable_type) {
        this.variable_type = variable_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String[] getOption_values() {
        return option_values;
    }

    public void setOption_values(String[] option_values) {
        this.option_values = option_values;
    }

    public String[] getOption_labels() {
        return option_labels;
    }

    public void setOption_labels(String[] option_labels) {
        this.option_labels = option_labels;
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

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public void setDependencies(String[] dependencies) {
        this.dependencies = dependencies;
    }

    public String getAccordion_name() {
        return accordion_name;
    }

    public void setAccordion_name(String accordion_name) {
        this.accordion_name = accordion_name;
    }

    public String getParent_accordion_name() {
        return parent_accordion_name;
    }

    public void setParent_accordion_name(String parent_accordion_name) {
        this.parent_accordion_name = parent_accordion_name;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "type='" + type + '\'' +
                ", deleted='" + deleted + '\'' +
                ", read_only='" + read_only + '\'' +
                ", version='" + version + '\'' +
                ", variable='" + variable + '\'' +
                ", variable_type='" + variable_type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", option_values=" + Arrays.toString(option_values) +
                ", option_labels=" + Arrays.toString(option_labels) +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", tooltip='" + tooltip + '\'' +
                ", dependencies=" + Arrays.toString(dependencies) +
                '}';
    }
}
