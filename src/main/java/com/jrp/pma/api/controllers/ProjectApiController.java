package com.jrp.pma.api.controllers;


import com.jrp.pma.entities.Project;
import com.jrp.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id")Long id){
        return projectService.findById(id).get();
    }

    @PostMapping//(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project){
        return projectService.save(project);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project){
        return projectService.save(project);
    }

    @PatchMapping(path="/{id}",consumes = "application/json")
    public Project partialUpdate(@PathVariable("id")Long id, @RequestBody Project patchProject){
        Project project = projectService.findById(id).get();

        if(patchProject.getName() != null){
            project.setName(patchProject.getName());
        }
        if(patchProject.getStage() != null){
            project.setStage(patchProject.getStage());
        }
        if(patchProject.getDescription() != null){
            project.setDescription(patchProject.getDescription());
        }

        return projectService.save(project);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id")Long id){
        try{
            projectService.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){

        }
    }
}
