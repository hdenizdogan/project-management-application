package com.jrp.pma.services;

import com.jrp.dto.ChartData;
import com.jrp.dto.TimeChartData;
import com.jrp.pma.entities.Project;
import com.jrp.pma.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project save(Project project){
        return projectRepository.save(project);
    }
    public List<Project> getAll(){
        return projectRepository.findAll();
    }
    public List<ChartData> getProjectStatus(){
        return projectRepository.getProjectStatus();
    }
    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

    public Project findByProjectId(Long id) {
        return projectRepository.findByProjectId(id);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public List<TimeChartData> getTimeData() {
        return projectRepository.getTimeData();
    }
}
