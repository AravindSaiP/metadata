package com.workbench.automate.metadata.result_model;

import com.workbench.automate.metadata.entity.ObjectClass;
import lombok.Builder;

import java.util.Arrays;
import java.util.Map;

@Builder
public class ObjectClassResponse {

    private ObjectClass []objectClasses;
    private Map<String,String[]> options;

    public ObjectClassResponse(ObjectClass[] objectClasses, Map<String, String[]> options) {
        this.objectClasses = objectClasses;
        this.options = options;
    }

    public ObjectClass[] getObjectClasses() {
        return objectClasses;
    }

    public void setObjectClasses(ObjectClass[] objectClasses) {
        this.objectClasses = objectClasses;
    }

    public Map<String, String[]> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String[]> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ObjectClassResponse{" +
                "objectClasses=" + Arrays.toString(objectClasses) +
                ", options=" + options +
                '}';
    }
}
