package com.workbench.automate.metadata.controller;

import com.workbench.automate.metadata.data.Data;
import com.workbench.automate.metadata.model.Metadata;
import com.workbench.automate.metadata.result_model.ObjectClassResponse;
import com.workbench.automate.metadata.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
public class RestApiController {

    @Autowired
    MetadataService metadataService;

    @Autowired
    Data data;

    @PostMapping("/data")
    public EntityModel<ObjectClassResponse> createMetadata(@RequestBody Metadata metadata[], @RequestParam("objectId") String objectId, @RequestHeader int start){
        data.setStart_index(start);
        data.setSheet_name(objectId);
        data.setGroup_start_index(start);
        data.setOption_start_index(start);
        ObjectClassResponse response = metadataService.createMetadata(metadata,objectId);
        if(response.getObjectClasses().length == metadata.length){
            metadataService.createLables(response.getObjectClasses(),response.getOptions(),metadata);
            metadataService.createOptions(response.getOptions());
            metadataService.createGroups(response.getObjectClasses(), metadata);
        }

        EntityModel<ObjectClassResponse> entityModel = EntityModel.of(response);
        WebMvcLinkBuilder groupsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getGroupMetadata());
        WebMvcLinkBuilder labelsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLabelMetadata());
        WebMvcLinkBuilder optionsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getOptionMetadata());
        WebMvcLinkBuilder objectLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getObjectMetadata(objectId));

        entityModel.add(groupsLink.withRel("groups-metadata"));
        entityModel.add(labelsLink.withRel("labels-metadata"));
        entityModel.add(optionsLink.withRel("options-metadata"));
        entityModel.add(objectLink.withRel(objectId+"-metadata"));

        return entityModel;

        //return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping(value = "/data/groups", produces = "text/csv")
    public ResponseEntity getGroupMetadata(){
        try {
            File file = new File("groups.csv");
            if(!file.exists()){
                throw new FileNotFoundException();
            }
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

    @GetMapping(value = "/data/{fileName}", produces = "text/csv")
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
