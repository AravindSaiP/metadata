package com.workbench.automate.metadata.service;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.workbench.automate.metadata.entity.GroupClass;
import com.workbench.automate.metadata.entity.LabelClass;
import com.workbench.automate.metadata.entity.ObjectClass;
import com.workbench.automate.metadata.entity.OptionClass;
import com.workbench.automate.metadata.request.Requirement;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CsvService {

    private final String requirements_path_prefix = "C:\\Automating_Metadata\\metadata\\requirments\\";

    public void beanToCsvObjectClass(List<ObjectClass> objectClassList, String sheetName){
        try {
            File myObj = new File(sheetName+".csv");
            myObj.createNewFile();
            FileWriter writer = new FileWriter(sheetName+".csv");

            ColumnPositionMappingStrategy mappingStrategy=
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(ObjectClass.class);

            String[] columns = new String[]
                    { "entity_id", "label_id", "type", "hint_id", "deleted","read_only","def_option_id","version","code","description","tooltip","variable_type","sub_type","selection_entity","content_variable","identifier" };
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<ObjectClass> builder=
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).build();

            objectClassList.add(0,new ObjectClass("entity_id", "label_id", "type", "hint_id", "deleted","read_only","def_option_id","version","code","description","tooltip","variable_type","sub_type","selection_entity","content_variable","identifier"));

            beanWriter.write(objectClassList);

            objectClassList.remove(0);

            writer.close();

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void beanToCsvLabelsClass(List<LabelClass> labelClasses, String sheetName){
        try {
            System.out.println(" = In label sheets");
            File myObj = new File(sheetName+".csv");
            myObj.createNewFile();
            FileWriter writer = new FileWriter(sheetName+".csv");

            ColumnPositionMappingStrategy mappingStrategy=
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(LabelClass.class);

            String[] columns = new String[]
                    { "text_id","en","fr_FR","ko","es","jp","version","de","fr"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<ObjectClass> builder=
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).build();

            labelClasses.add(0,new LabelClass("text_id","en","fr_FR","ko","es","jp","version","de","fr"));

            beanWriter.write(labelClasses);

            labelClasses.remove(0);
            writer.close();

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void beanToCsvOptionsClass(List<OptionClass> optionClasses, String sheetName) {
        try {

            File myObj = new File(sheetName+".csv");
            myObj.createNewFile();
            FileWriter writer = new FileWriter(sheetName+".csv");

            ColumnPositionMappingStrategy mappingStrategy=
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(OptionClass.class);

            String[] columns = new String[]
                    { "id","entity_id","version","code"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<ObjectClass> builder=
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).build();

            optionClasses.add(0,new OptionClass("id","entity_id","version","code"));

            beanWriter.write(optionClasses);

            optionClasses.remove(0);
            writer.close();

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void beanToCsvGroupsClass(List<GroupClass> groupClasses, String sheetName) {
        try {

            File myObj = new File(sheetName+".csv");
            myObj.createNewFile();
            FileWriter writer = new FileWriter(sheetName+".csv");

            ColumnPositionMappingStrategy mappingStrategy=
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(GroupClass.class);

            String[] columns = new String[]
                    { "group_id","label_id","code","entities","category","parent_group","version","repeatable","group_by_key","isAccordion"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<ObjectClass> builder=
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).build();

            groupClasses.add(0,new GroupClass("group_id","label_id","code","entities","category","parent_group","version","repeatable","group_by_key","isAccordion"));

            beanWriter.write(groupClasses);

            groupClasses.remove(0);
            writer.close();

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<Requirement> csvToBeanRequirementClass(String fileName){
         try(Reader reader = Files.newBufferedReader(Paths.get(requirements_path_prefix+fileName));)
         {
             ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
             strategy.setType(Requirement.class);
             String[] memberFieldsToBindTo = {"Accordions", "Title", "Type" ,"Options_Labels", "Options_Values","Description","Default_Value","Tooltip","Dependencies","Variable_Name","Variable_Type"};
             strategy.setColumnMapping(memberFieldsToBindTo);
             CsvToBean<Requirement> requirement = new CsvToBeanBuilder(reader)
                     .withMappingStrategy(strategy)
                     .withSkipLines(1)
                     .withIgnoreLeadingWhiteSpace(true)
                     .build();

             Iterator<Requirement> reqIterator = requirement.iterator();
             List<Requirement> requirementList = new ArrayList<>();
             while (reqIterator.hasNext()) {
                 Requirement req = reqIterator.next();
//                 System.out.println("Accordion : " + myUser.getAccordions());
//                 System.out.println("Title : " + myUser.getTitle());
//                 System.out.println("Option Labels : " + myUser.getOptions_Labels());
//                 System.out.println("Option Values : " + myUser.getOptions_Values());
//                 System.out.println("Description : " + myUser.getDescription());
//                 System.out.println("Default Value : " + myUser.getDefault_Value());
//                 System.out.println("Tooltip : " + myUser.getTooltip());
//                 System.out.println("Dependencies : " + myUser.getDependencies());
//                 System.out.println("Variable Name : " + myUser.getVariable_Name());
//                 System.out.println("Variable Type : " + myUser.getVariable_Type());
//                 System.out.println("------------------------------------------");
//                 System.out.println("------------------------------------------");
//                 System.out.println("------------------------------------------");
                 requirementList.add(req);
             }

             return requirementList;

         } catch (IOException e) {
             System.out.println("e = " + e);
             throw new RuntimeException(e);
         }

    }
}
