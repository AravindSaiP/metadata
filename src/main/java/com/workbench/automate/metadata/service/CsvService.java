package com.workbench.automate.metadata.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.workbench.automate.metadata.entity.GroupClass;
import com.workbench.automate.metadata.entity.LabelClass;
import com.workbench.automate.metadata.entity.ObjectClass;
import com.workbench.automate.metadata.entity.OptionClass;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvService {

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
}
