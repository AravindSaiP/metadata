package com.workbench.automate.metadata.controller;

import com.workbench.automate.metadata.data.Data;
import com.workbench.automate.metadata.request.Requirement;
import com.workbench.automate.metadata.service.MetadataService;
import com.workbench.automate.metadata.storage.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class RestApiControllerCSV {

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

    @PostMapping("/data/csv")
    public ResponseEntity uploadCsvFile(@RequestParam("file") MultipartFile file){
        try {
            fileStorageServiceImp.init();
            fileStorageServiceImp.save(file);
            List<Requirement> requirementList = metadataService.mapRequirementToPogo(file.getOriginalFilename());
            MetadataService metadata[] = metadataService.mapReqPojoToMetadata(requirementList);
            fileStorageServiceImp.deleteAll();
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


}



