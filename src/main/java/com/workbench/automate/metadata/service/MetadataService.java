package com.workbench.automate.metadata.service;

import com.workbench.automate.metadata.data.Data;
import com.workbench.automate.metadata.entity.GroupClass;
import com.workbench.automate.metadata.entity.LabelClass;
import com.workbench.automate.metadata.entity.ObjectClass;
import com.workbench.automate.metadata.constants.MetadataConstants;
import com.workbench.automate.metadata.entity.OptionClass;
import com.workbench.automate.metadata.model.Metadata;
import com.workbench.automate.metadata.request.Requirement;
import com.workbench.automate.metadata.result_model.GroupClassResponse;
import com.workbench.automate.metadata.result_model.ObjectClassResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MetadataService {

    @Autowired
    CsvService csvService;

    @Autowired
    Data data;

    private Map<String,String> subTypeMap = new HashMap<>();
    private Map<String,String> typeMap = new HashMap<>();

    public ObjectClassResponse createMetadata(Metadata[] metadata, String obectId){

        List<ObjectClass> objectClasses = new ArrayList<>();
        Map<String,String[]> options = new HashMap<>();
        initilizeTypes();
        for(int i=0;i< metadata.length;i++){

            String uuid = Integer.toString(generateID("objectId"));
            String entity_id = "e"+ uuid; //Integer.toString(random);
            String label_id = "l"+ uuid; //Integer.toString(random);
            String hint_id = "";// metadata[i].getType().equals("textbox") ? "h" + uuid : "";
            String description =  "d" + uuid;
            String tooltip = "t" + uuid;

            String code = metadata[i].getVariable();
            String type = typeMap.get(metadata[i].getType());
            System.out.println("type = " + type);
            String deleted = MetadataConstants.DELETED;
            String read_only = MetadataConstants.READ_ONLY;
            String def_option_id = "";
            String version = MetadataConstants.VERSION;
            String variable_type = metadata[i].getVariable_type();
            String sub_type = subTypeMap.get(metadata[i].getType());
            String selection_entity = MetadataConstants.SELECTION_ENTITY;
            String content_variable = "";
            String identifier = "";

            if(!type.equals("textbox") && metadata[i].getOption_values().length > 0){

                Map<String,String[]> current_options = this.createOptions(metadata[i].getOption_labels(),metadata[i].getOption_values(),entity_id);
                System.out.println("current_options = " + current_options);
                Map.Entry<String, String[]> firstEntry = current_options.entrySet().iterator().next();
                def_option_id = firstEntry.getKey();
                options.putAll(current_options);

            }else {
                hint_id = "h"+uuid;
            }

            ObjectClass objectClass = ObjectClass.builder()
                    .entity_id(entity_id)
                    .label_id(label_id)
                    .type(type)
                    .hint_id(hint_id)
                    .deleted(deleted)
                    .read_only(read_only)
                    .def_option_id(def_option_id)
                    .version(version)
                    .code(code)
                    .description(description)
                    .tooltip(tooltip)
                    .variable_type(variable_type)
                    .sub_type(sub_type)
                    .selection_entity(selection_entity)
                    .content_variable(content_variable)
                    .identifier(identifier)
                    .build();

            objectClasses.add(objectClass);

        }

        csvService.beanToCsvObjectClass(objectClasses,obectId);
        //return objectClasses.toArray(new ObjectClass[0]);
        return ObjectClassResponse
                .builder()
                .objectClasses(objectClasses.toArray(new ObjectClass[0]))
                .options(options)
                .build();
    }

    private Map<String, String[]> createOptions(String[] option_labels, String[] option_values, String entity_id) {
        Map<String,String[]> options = new HashMap<>();
        for(int i=0;i< option_labels.length;i++){
            String optionId = "op"+ Integer.toString(generateID("optionId"));
            options.put(optionId, new String[]{option_values[i],option_labels[i],entity_id});
        }
        return options;
    }

    private int generateID(String type){
        int index;
        switch (type){
            case "optionId" : {
                index = data.getOption_start_index();
                data.incrementOptionStart_index();
                break;
            }
            case "groupId" : {
                index = data.getGroup_start_index()+1000;
                data.incrementGroupStart_index();
                break;
            }

            default:{
                index = data.getStart_index();
                data.incrementStart_index();
                break;
            }
        }
       return index;
    }

    private boolean initilizeTypes(){

        try{
            subTypeMap.put("Dropdown","DD");
            subTypeMap.put("Checkbox","CBX");
            subTypeMap.put("Textbox","TA");
            subTypeMap.put("Textarea","TA");
            subTypeMap.put("Radiobutton","RB");
            subTypeMap.put("Heading","DISP_TXT");

            typeMap.put("Dropdown","Single-Select");
            typeMap.put("Checkbox","Single-Select");
            typeMap.put("Textbox","textbox");
            typeMap.put("Textarea","textbox");
            typeMap.put("Radiobutton","Single-Select");
            typeMap.put("Heading","textbox");

            return true;
        }
        catch (Exception e){
            return false;
        }

    }


    public void createLables(ObjectClass[] objectClasses, Map<String, String[]> options, Map<String, String> groupIds, Metadata[] metadata) {
        List<LabelClass> labelClasses = new ArrayList<>();
        String text_id;
        String en;
        String version;

        for(int i=0;i<objectClasses.length;i++){
           text_id = objectClasses[i].getLabel_id();
           en = metadata[i].getTitle();
           version = MetadataConstants.VERSION;
           LabelClass label = new LabelClass(text_id,en,"","","","",version,"","");

           text_id = objectClasses[i].getDescription();
           en = metadata[i].getDescription();
           version = MetadataConstants.VERSION;
           LabelClass description = new LabelClass(text_id,en,"","","","",version,"","");

            text_id = objectClasses[i].getTooltip();
            en = metadata[i].getTooltip();
            version = MetadataConstants.VERSION;
            LabelClass tooltip = new LabelClass(text_id,en,"","","","",version,"","");

            labelClasses.add(label);
            labelClasses.add(description);
            labelClasses.add(tooltip);

            if(!metadata[i].getDefaultValue().equalsIgnoreCase("")){
                text_id = objectClasses[i].getTooltip();
                en = metadata[i].getTooltip();
                version = MetadataConstants.VERSION;
                LabelClass hint = new LabelClass(text_id,en,"","","","",version,"","");
                labelClasses.add(hint);
            }


        }

        Set<Map.Entry<String,String[]>> entrySet = options.entrySet();
        for(Map.Entry<String,String[]> keys : entrySet){
            text_id = keys.getKey();
            en = keys.getValue()[1];
            version = MetadataConstants.VERSION;
            LabelClass option = new LabelClass(text_id,en,"","","","",version,"","");
            labelClasses.add(option);
        }

        Set<Map.Entry<String,String>> entrySetGroups = groupIds.entrySet();
        for(Map.Entry<String,String> keys : entrySetGroups){
            text_id = keys.getKey();
            en = keys.getValue();
            version = MetadataConstants.VERSION;
            LabelClass option = new LabelClass(text_id,en,"","","","",version,"","");
            labelClasses.add(option);
        }

        csvService.beanToCsvLabelsClass(labelClasses,"labels");

    }

    public void createOptions(Map<String, String[]> options) {
        int length = options.size();
        List<OptionClass> optionClasses = new ArrayList<>();
        Set<Map.Entry<String,String[]>> entrySet = options.entrySet();
        for(Map.Entry<String,String[]> keys : entrySet){
            OptionClass optionClass = OptionClass.builder()
                    .id(keys.getKey())
                    .code(keys.getValue()[0])
                    .entity_id(keys.getValue()[2])
                    .version(MetadataConstants.VERSION)
                    .build();
            optionClasses.add(optionClass);
        }
        csvService.beanToCsvOptionsClass(optionClasses,"options");
    }


    public GroupClassResponse createGroups(ObjectClass[] objectClasses, Metadata[] metadata) {
        List<GroupClass> groupClasses = new ArrayList<>();
        Map<String,String> parentAccordions = new HashMap<>();
        Map<String,String> labelIdsMap = new HashMap<>();

        for(int i=0;i< objectClasses.length;){
            StringBuilder entities = new StringBuilder("");
            String accordion_name = metadata[i].getAccordion_name();
            String parentAccordian_name = metadata[i].getParent_accordion_name();

            while (i < objectClasses.length && metadata[i].getAccordion_name().equalsIgnoreCase(accordion_name)){
                entities = entities.append(objectClasses[i].getEntity_id()+",");
                i++;
            }
            entities = entities.deleteCharAt(entities.length()-1);
            String id = Integer.toString(generateID("groupId"));
            String groupId = "gr" + id;
            String labelId = "l" + id;
            String code = "GR" + id;
            //String parent_group = "";
            String version = MetadataConstants.VERSION;

            String category = data.getSheet_name();
            String repeatable = MetadataConstants.REPEATABLE;
            String groupByKeys = "";
            String isAccordion = "TRUE";

            if(!parentAccordian_name.equalsIgnoreCase("")){
                parentAccordions.put(parentAccordian_name,groupId);
            }

            labelIdsMap.put(labelId,accordion_name);

            GroupClass groupClass = GroupClass.builder()
                    .group_id(groupId)
                    .label_id(labelId)
                    .code(code)
                    .entities(new String(entities))
                    .category(category)
                    .parent_group(parentAccordions.get(groupId))
                    .version(version)
                    .repeatable(repeatable)
                    .group_by_key(groupByKeys)
                    .isAccordion(isAccordion)
                    .build();

            groupClasses.add(groupClass);
        }

        csvService.beanToCsvGroupsClass(groupClasses,"groups");

        return GroupClassResponse.builder()
                .groupIds(labelIdsMap)
                .build();
    }


    public List<Requirement> mapRequirementToPogo(String originalFileName) {
        System.out.println("originalFileName = " + originalFileName);
        List<Requirement> requirementList = csvService.csvToBeanRequirementClass(originalFileName);
        return requirementList;
    }

    public MetadataService[] mapReqPojoToMetadata(List<Requirement> requirementList) {
        List<Metadata> metadataList = new ArrayList<>();

        for(Requirement req : requirementList){
            String option_values[] = req.getOptions_Values().split(",");
            String option_labels[] = req.getOptions_Labels().split(",");

        }

        return null;
    }
}
