package com.workbench.automate.metadata.controller;

import com.workbench.automate.metadata.data.Data;
import com.workbench.automate.metadata.model.Metadata;
import com.workbench.automate.metadata.request.Requirement;
import com.workbench.automate.metadata.result_model.GroupClassResponse;
import com.workbench.automate.metadata.result_model.ObjectClassResponse;
import com.workbench.automate.metadata.service.MetadataService;
import com.workbench.automate.metadata.storage.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RestController
public class RestApiControllerJSON {

    @Autowired
    MetadataService metadataService;

    @Autowired
    FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    Data data;


    @PostMapping("/data/csv")
    public ResponseEntity uploadCsvFile(@RequestParam("file") MultipartFile file,@RequestParam("objectId") String objectId, @RequestHeader int start){
        try {
            fileStorageServiceImp.init();
            fileStorageServiceImp.save(file);
            List<Requirement> requirementList = metadataService.mapRequirementToPogo(file.getOriginalFilename());
            for(Requirement req : requirementList)
                System.out.println("req = " + req);
            Metadata metadata[] = metadataService.mapReqPojoToMetadata(requirementList);
            fileStorageServiceImp.deleteAll();

            data.setStart_index(start);
            data.setSheet_name(objectId);
            data.setGroup_start_index(start);
            data.setOption_start_index(start);
//            System.out.println("Before Object response");
//            for (Metadata metadata1 : metadata)
//                System.out.println("metadata1 = " + metadata1);
            ObjectClassResponse response = metadataService.createMetadata(metadata,objectId);
            //System.out.println("After Object response");
            //System.out.println("response = " + response);
            //System.out.println("response.getObjectClasses().length = " + response.getObjectClasses().length);
            //System.out.println("metadata.length = " + metadata.length);
            if(response.getObjectClasses().length == metadata.length){
                GroupClassResponse groupResponse = metadataService.createGroups(response.getObjectClasses(), metadata);
                System.out.println("After group classes" );
                metadataService.createLables(response.getObjectClasses(),response.getOptions(),groupResponse.getGroupIds(),metadata);
                metadataService.createOptions(response.getOptions());

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

            return new ResponseEntity<>(entityModel, HttpStatus.CREATED);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping("/data")
    public ResponseEntity<EntityModel> createMetadata(@RequestBody Metadata metadata[], @RequestParam("objectId") String objectId, @RequestHeader int start){
        data.setStart_index(start);
        data.setSheet_name(objectId);
        data.setGroup_start_index(start);
        data.setOption_start_index(start);
        ObjectClassResponse response = metadataService.createMetadata(metadata,objectId);
        if(response.getObjectClasses().length == metadata.length){
            GroupClassResponse groupResponse = metadataService.createGroups(response.getObjectClasses(), metadata);
            metadataService.createLables(response.getObjectClasses(),response.getOptions(),groupResponse.getGroupIds(),metadata);
            metadataService.createOptions(response.getOptions());

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

        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
        //return response;

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
