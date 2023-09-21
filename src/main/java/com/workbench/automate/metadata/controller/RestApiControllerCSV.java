package com.workbench.automate.metadata.controller;

import com.workbench.automate.metadata.data.Data;
import com.workbench.automate.metadata.model.Metadata;
import com.workbench.automate.metadata.request.Requirement;
import com.workbench.automate.metadata.result_model.ObjectClassResponse;
import com.workbench.automate.metadata.service.MetadataService;
import com.workbench.automate.metadata.storage.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
public class RestApiControllerCSV {

    private String url = "http://localhost:8080/data/csv";

    @Autowired
    MetadataService metadataService;

    @Autowired
    FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    Data data;

//    @PostMapping("/data/csv")
//    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){
//        String message = "";
//        try {
//            fileStorageServiceImp.init();
//            fileStorageServiceImp.save(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            System.out.println("message = " + message);
//
//
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
//            System.out.println("message = " + message);
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
//        }
//    }


//    [
//    {
//        "type":"Textbox",
//            "variable":"var1",
//            "variable_type":"content",
//            "sub_type":"",
//            "option_values":[],
//        "option_labels":[],
//        "title":"title",
//            "description":"description",
//            "defaultValue":"defaultValue",
//            "tooltip":"tooltip",
//            "dependencies":[],
//        "accordion_name":"Acc1",
//            "parent_accordion_name":""
//    },
//    {
//        "type":"Dropdown",
//            "variable":"var2",
//            "variable_type":"content",
//            "sub_type":"RB",
//            "option_values":["0","1"],
//        "option_labels":["disable","enable"],
//        "title":"title2",
//            "description":"description2",
//            "defaultValue":"defaultValue2",
//            "tooltip":"tooltip",
//            "dependencies":[],
//        "accordion_name":"",
//            "parent_accordion_name":"Acc1"
//    },
//    {
//        "type":"Textbox",
//            "variable":"var1",
//            "variable_type":"content",
//            "sub_type":"",
//            "option_values":[],
//        "option_labels":[],
//        "title":"title",
//            "description":"description",
//            "defaultValue":"defaultValue",
//            "tooltip":"tooltip",
//            "dependencies":[],
//        "accordion_name":"",
//            "parent_accordion_name":"Acc1"
//    },
//    {
//        "type":"Dropdown",
//            "variable":"var2",
//            "variable_type":"content",
//            "sub_type":"RB",
//            "option_values":["0","1"],
//        "option_labels":["disable","enable"],
//        "title":"title2",
//            "description":"description2",
//            "defaultValue":"defaultValue2",
//            "tooltip":"tooltip",
//            "dependencies":[],
//        "accordion_name":"",
//            "parent_accordion_name":"Acc2"
//    }
//]

//    @PostMapping(value = "/data/csv")
//    public ResponseEntity uploadCsvFile(@RequestParam("file") MultipartFile file){
//        try {
//            System.out.println("Request received uploadCsvFile");
//            RestTemplate restTemplate = new RestTemplate();
//            fileStorageServiceImp.init();
//            System.out.println("Folder created");
//            fileStorageServiceImp.save(file);
//            System.out.println("File Saved");
//            List<Requirement> requirementList = metadataService.mapRequirementToPogo(file.getOriginalFilename());
//            System.out.println("Requirement list received");
//            Metadata metadata[] = metadataService.mapReqPojoToMetadata(requirementList);
//            System.out.println("Metadata built");
//            fileStorageServiceImp.deleteAll();
//            System.out.println("File deleted");
//            HttpEntity<List<Metadata>> httpEntity = new HttpEntity<>(Arrays.asList(metadata));
//            System.out.println("Body Framed");
//
//            ObjectClassResponse entityModelResponseEntity = restTemplate.postForEntity(url, httpEntity, ObjectClassResponse.class);
//            System.out.println("Response received");
//            return new ResponseEntity(entityModelResponseEntity,HttpStatus.CREATED);
//
//        } catch (Exception e) {
//
//            System.out.println("e = " + e);
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
//        }
//    }


}



