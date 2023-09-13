package com.workbench.automate.metadata.result_model;

import lombok.Builder;

import java.util.Map;

@Builder
public class GroupClassResponse {

    private Map<String,String> groupIds;

    public GroupClassResponse(Map<String, String> groupIds) {
        this.groupIds = groupIds;
    }

    public Map<String, String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Map<String, String> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return "GroupClassResponse{" +
                "groupIds=" + groupIds +
                '}';
    }
}
