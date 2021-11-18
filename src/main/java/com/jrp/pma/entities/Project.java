package com.jrp.pma.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(generator="project_seq")
    private long projectId;

    @NotBlank(message = "*Must give a project name")
    @Size(min=2,max=50,message = "*Size must be between 2 and 50")
    private String name;

    @NotBlank(message = "*Must give a project stage")
    @Size(min=2,max=50)
    private String stage; // NOTSTARTED, INPROGRESS, COMPLETED

    @NotBlank(message = "*Must give a project description")
    @Size(min=5,max=255, message = "*Size must be between 5 and 255")
    private String description;

    @NotBlank(message = "*Date cannot be empty")
    private Date startDate;

    @NotBlank(message = "*Date cannot be empty")
    private Date endDate;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns=@JoinColumn(name="employee_id"))
    @JsonIgnore
    private List<Employee> employees;

    public Project() {
    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        if(employees == null){
            employees = new ArrayList<>();
        }
        employees.add(employee);
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
