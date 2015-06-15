package cn.wp.study.activiti;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:activiti.cfg.xml")
public class CreateDatabase {
	
	@Autowired private StandaloneProcessEngineConfiguration s ;
	
	@Test
	public void createDatabase(){
		s.buildProcessEngine();
	}
	
}
