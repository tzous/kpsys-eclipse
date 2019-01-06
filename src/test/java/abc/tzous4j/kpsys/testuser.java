package abc.tzous4j.kpsys;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import abc.tzous4j.kpsys.dao.TbuserMapper;
import abc.tzous4j.kpsys.model.Tbuser;
import abc.tzous4j.kpsys.service.ITbuserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class testuser extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    ITbuserService userService;

	@Before
	public void setUp() throws Exception {
		System.out.println("@Before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
	}

	@Test
	@Transactional
	public void test() throws Exception {
		Tbuser user = new Tbuser();
		//user.setUid(3);
		user.setUname("tom8");
		user.setUpasswd("123456");
		user.setUdesc("tomtom");
		user.setUstat(1);
		userService.AddUser(user);

	}

}
