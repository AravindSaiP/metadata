package com.workbench.automate.metadata.controller;

import com.workbench.automate.metadata.constants.MetadataUserPreference;
import com.workbench.automate.metadata.model.Metadata;
import com.workbench.automate.metadata.result_model.ObjectClassResponse;
import com.workbench.automate.metadata.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
public class RestApiController {

    @Autowired
    MetadataService metadataService;

    @Autowired
    MetadataUserPreference metadataUserPreference;

    @PostMapping("/data")
    public ResponseEntity<ObjectClassResponse> createMetadata(@RequestBody Metadata metadata[], @RequestParam("objectId") String objectId, @RequestHeader int start){
        metadataUserPreference.setStart_index(start);
        metadataUserPreference.setSheet_name(objectId);
        metadataUserPreference.setGroup_start_index(start);
        metadataUserPreference.setOption_start_index(start);
        ObjectClassResponse response = metadataService.createMetadata(metadata,objectId);
        if(response.getObjectClasses().length == metadata.length){
            metadataService.createLables(response.getObjectClasses(),response.getOptions(),metadata);
            metadataService.createOptions(response.getOptions());
            metadataService.createGroups(response.getObjectClasses(), metadata);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping(value = "/data/groups", produces = "text/csv")
    public ResponseEntity getGroupMetadata(){
        try {
            File file = new File("groups.csv");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + "groups" + ".csv")
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/data/labels", produces = "text/csv")
    public ResponseEntity getLabelMetadata(){
        try {
            File file = new File("labels.csv");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + "labels" + ".csv")
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/data/options", produces = "text/csv")
    public ResponseEntity getOptionMetadata(){
        try {
            File file = new File("options.csv");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + "options" + ".csv")
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/data/options/{fileName}", produces = "text/csv")
    public ResponseEntity getObjectMetadata(@PathVariable String fileName){
        try {
            File file = new File(fileName+".csv");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + "options" + ".csv")
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
