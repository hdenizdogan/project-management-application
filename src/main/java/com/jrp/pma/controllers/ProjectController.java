package com.jrp.pma.controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.dto.TimeChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {

        Project aProject = new Project();
        Iterable<Employee> employees = employeeService.findAll();
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);

        return "projects/project-form";
    }

    @PostMapping("/save")
    public String createProject(Project project, @RequestParam List<Long> employees, Model model, Errors errors) {

        if(errors.hasErrors()){
            return "redirect:/projects/new";
        }

        projectService.save(project);

        // use a redirect to prevent duplicate submissions
        return "redirect:/projects";

    }

    @GetMapping("/timelines")
    public String displayProjectTimelines(Model model) throws JsonProcessingException {

        List<TimeChartData> timelineData = projectService.getTimeData();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

        System.out.println("---------- project timelines ----------");
        System.out.println(jsonTimelineString);

        model.addAttribute("projectTimeList", jsonTimelineString);

        return "projects/project-timelines";
    }

}