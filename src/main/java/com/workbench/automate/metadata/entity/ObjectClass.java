package com.workbench.automate.metadata.entity;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@Builder
public class ObjectClass  {

    //	code	label_id	type	hint_id	deleted	read_only	def_option_id	version	code	description	tooltip	variable_type	sub_type	selection_entity	content_variable	identifier
    private String entity_id;
    private String label_id;
    private String type;
    private String hint_id;
    private String deleted;
    private String read_only;
    private String def_option_id;
    private String version;
    private String code;
    private String description;
    private String tooltip;
    private String variable_type;
    private String sub_type;
    private String selection_entity;
    private String content_variable;
    private String identifier;

    public ObjectClass() {
    }

    public ObjectClass(String entity_id, String label_id, String type, String hint_id, String deleted, String read_only, String def_option_id, String version, String code, String description, String tooltip, String variable_type, String sub_type, String selection_entity, String content_variable, String identifier) {
        this.entity_id = entity_id;
        this.label_id = label_id;
        this.type = type;
        this.hint_id = hint_id;
        this.deleted = deleted;
        this.read_only = read_only;
        this.def_option_id = def_option_id;
        this.version = version;
        this.code = code;
        this.description = description;
        this.tooltip = tooltip;
        this.variable_type = variable_type;
        this.sub_type = sub_type;
        this.selection_entity = selection_entity;
        this.content_variable = content_variable;
        this.identifier = identifier;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHint_id() {
        return hint_id;
    }

    public void setHint_id(String hint_id) {
        this.hint_id = hint_id;
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

    public String getDef_option_id() {
        return def_option_id;
    }

    public void setDef_option_id(String def_option_id) {
        this.def_option_id = def_option_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
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

    public String getSelection_entity() {
        return selection_entity;
    }

    public void setSelection_entity(String selection_entity) {
        this.selection_entity = selection_entity;
    }

    public String getContent_variable() {
        return content_variable;
    }

    public void setContent_variable(String content_variable) {
        this.content_variable = content_variable;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "ObjectClass{" +
                "entity_id='" + entity_id + '\'' +
                ", label_id='" + label_id + '\'' +
                ", type='" + type + '\'' +
                ", hint_id='" + hint_id + '\'' +
                ", deleted='" + deleted + '\'' +
                ", read_only='" + read_only + '\'' +
                ", def_option_id='" + def_option_id + '\'' +
                ", version='" + version + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", tooltip='" + tooltip + '\'' +
                ", variable_type='" + variable_type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", selection_entity='" + selection_entity + '\'' +
                ", content_variable='" + content_variable + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }



}
