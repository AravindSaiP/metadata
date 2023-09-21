package com.workbench.automate.metadata.request;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class Requirement {
    //Accordions	Title	PM Category	Type	Options	dev description	Description	Default Value	Tooltip	Comments	Category	Variable Name	Variable Type

    private String Title;

    private String Type;
    private String Options_Labels;
    private String Options_Values;
    private String Description;
    private String Default_Value;
    private String Tooltip;
    private String Dependencies;
    private String Variable_Name;
    private String Variable_Type;

    private String Parent_Accordion;

    private String Accordion_Name;


    public Requirement() {
    }

    public Requirement(String title, String type, String options_Labels, String options_Values, String description, String default_Value, String tooltip, String dependencies, String variable_Name, String variable_Type, String parent_Accordion, String accordion_Name) {

        Title = title;
        Type = type;
        Options_Labels = options_Labels;
        Options_Values = options_Values;
        Description = description;
        Default_Value = default_Value;
        Tooltip = tooltip;
        Dependencies = dependencies;
        Variable_Name = variable_Name;
        Variable_Type = variable_Type;
        Parent_Accordion = parent_Accordion;
        Accordion_Name = accordion_Name;
    }

    public String getParent_Accordion() {
        return Parent_Accordion;
    }

    public void setParent_Accordion(String parent_Accordion) {
        Parent_Accordion = parent_Accordion;
    }

    public String getAccordion_Name() {
        return Accordion_Name;
    }

    public void setAccordion_Name(String accordion_Name) {
        Accordion_Name = accordion_Name;
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getOptions_Labels() {
        return Options_Labels;
    }

    public void setOptions_Labels(String options_Labels) {
        Options_Labels = options_Labels;
    }

    public String getOptions_Values() {
        return Options_Values;
    }

    public void setOptions_Values(String options_Values) {
        Options_Values = options_Values;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDefault_Value() {
        return Default_Value;
    }

    public void setDefault_Value(String default_Value) {
        Default_Value = default_Value;
    }

    public String getTooltip() {
        return Tooltip;
    }

    public void setTooltip(String tooltip) {
        Tooltip = tooltip;
    }

    public String getDependencies() {
        return Dependencies;
    }

    public void setDependencies(String dependencies) {
        Dependencies = dependencies;
    }

    public String getVariable_Name() {
        return Variable_Name;
    }

    public void setVariable_Name(String variable_Name) {
        Variable_Name = variable_Name;
    }

    public String getVariable_Type() {
        return Variable_Type;
    }

    public void setVariable_Type(String variable_Type) {
        Variable_Type = variable_Type;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                ", Title='" + Title + '\'' +
                ", Type='" + Type + '\'' +
                ", Options_Labels='" + Options_Labels + '\'' +
                ", Options_Values='" + Options_Values + '\'' +
                ", Description='" + Description + '\'' +
                ", Default_Value='" + Default_Value + '\'' +
                ", Tooltip='" + Tooltip + '\'' +
                ", Dependencies='" + Dependencies + '\'' +
                ", Variable_Name='" + Variable_Name + '\'' +
                ", Variable_Type='" + Variable_Type + '\'' +
                ", Parent_Accordion='" + Parent_Accordion + '\'' +
                ", Accordion_Name='" + Accordion_Name + '\'' +
                '}';
    }
}
