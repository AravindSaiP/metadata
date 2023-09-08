package com.workbench.automate.metadata.constants;

import org.springframework.stereotype.Component;

@Component
public class MetadataUserPreference {
    private  int start_index;
    private String sheet_name;

    private int option_start_index;

    private int group_start_index;

    public MetadataUserPreference() {
    }

    public  int getStart_index() {
        return start_index;
    }

    public  void setStart_index(int start_index) {
        this.start_index = start_index;
    }

    public void incrementStart_index(){
        this.start_index = this.start_index + 1;
    }

    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }

    public String getSheet_name() {
        return sheet_name;
    }

    public int getOption_start_index() {
        return option_start_index;
    }

    public void setOption_start_index(int option_start_index) {
        this.option_start_index = option_start_index;
    }

    public int getGroup_start_index() {
        return group_start_index;
    }

    public void setGroup_start_index(int group_start_index) {
        this.group_start_index = group_start_index;
    }

    public  void incrementGroupStart_index(){
        this.group_start_index = this.group_start_index + 1;
    }

    public  void incrementOptionStart_index(){
        this.option_start_index = this.option_start_index + 1;
    }

    @Override
    public String toString() {
        return "MetadataUserPreference{" +
                "start_index=" + start_index +
                ", sheet_name='" + sheet_name + '\'' +
                '}';
    }
}
