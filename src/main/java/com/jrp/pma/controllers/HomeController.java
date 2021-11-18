package com.jrp.pma.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.dto.ChartData;
import com.jrp.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.repository.EmployeeRepository;
import com.jrp.pma.repository.ProjectRepository;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    private String version;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber",version);


        // querying the database for projects
        List<Project> projects = projectService.getAll();
        model.addAttribute("projectsList",projects);

        List<ChartData> projectData = projectService.getProjectStatus();

        //convert projectData into json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // {["NOTSTARTED",1],["INPROGRESS",2],["COMPLETED",1]}

        model.addAttribute("projectStatusCnt",jsonString);


        // querying the database for employees
        List<EmployeeProject> employeesProjectCnt = employeeService.employeeProjects();
        model.addAttribute("employeesListProjectsCnt",employeesProjectCnt);
        return "main/home";
    }
}
