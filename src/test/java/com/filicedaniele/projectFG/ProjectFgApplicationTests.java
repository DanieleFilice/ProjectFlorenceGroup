package com.filicedaniele.projectFG;

import com.filicedaniele.projectFG.controller.UserController;
import com.filicedaniele.projectFG.repository.UserRepository;
import com.filicedaniele.projectFG.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {ProjectFgApplication.class})
class ProjectFgApplicationTests {


	@Autowired
	UserRepository userRepository;
	@Autowired
	UserController userController;
	@MockBean
	UserService userService;




	@Test
	public void contextLoadsRepository() {
		Assertions.assertNotNull(userRepository);
	}
	@Test
	public void contextLoadsController() {
		Assertions.assertNotNull(userController);
	}
	@Test
	public void contextLoadsService() {
		Assertions.assertNotNull(userService);
	}





}
