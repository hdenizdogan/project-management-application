package com.jrp.pma.repository;

import com.jrp.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "apiemployees",path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {

    @Override
    Page<Employee> findAll(Pageable pageable);

    @Override
    List<Employee> findAll();

    public Employee findByEmployeeId(Long id);

    @Query(nativeQuery = true, value="SELECT  e.last_name as lastName, e.first_name as firstName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name,e.last_name ORDER BY 3 DESC")
    List<EmployeeProject> employeeProjects();

    Optional<Employee> findByEmail(String email);
}
