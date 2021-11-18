package com.jrp.pma.dao;

import com.jrp.pma.ProjectManagementApplication;
import com.jrp.pma.entities.Project;
import com.jrp.pma.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
//@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = {"classpath:schema.sql","classpath:data.sql"}),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = "classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {
    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void ifNewProjectSaved_thenSuccess(){
        Project project = new Project("New Test Project","COMPLETED","Test Description");
        projectRepository.save(project);
        assertEquals(1,projectRepository.findAll().size());
    }
}
