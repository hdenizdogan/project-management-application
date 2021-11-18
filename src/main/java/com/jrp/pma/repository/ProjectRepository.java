package com.jrp.pma.repository;

import com.jrp.dto.ChartData;
import com.jrp.dto.TimeChartData;
import com.jrp.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {
    List<Project> findAll();

    @Query(nativeQuery = true,value = "SELECT stage as label,COUNT(*) as value " +
            "FROM project "+
            "GROUP BY stage")
    List<ChartData> getProjectStatus();

    Project findByProjectId(Long id);

    @Query(nativeQuery = true, value="SELECT name as projectName,start date as startDate,end date as endDate "
            +"FROM project WHERE start_date is not null")
    List<TimeChartData> getTimeData();
}
