package com.group8.JourneySharing.service.impl;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;

import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group8.JourneySharing.repository.UserRepository;

import java.util.ArrayList;

@RunWith ( MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserServiceImpl userServiceImpl;

	private NewUserVo newUser;
	private User user;
	private UserDetailsVo userDetails;
	
	@Mock
	UserRepository userRepository;

	@Before
	public void init() {
		
		userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserRepository(userRepository);
		newUser = new NewUserVo("email", "password","firstName","lastName");
		user = new User("email", "Unique char","password","firstName","lastName","mobile number","iban", null);
        userDetails = new UserDetailsVo("email", "firstName","lastName", "mobileNumber","iban", null);

	}


	@Test (expected = BadRequestException.class)
	public void testUserWithDuplicateEmail()
	{
		given( userRepository.findByEmail( ArgumentMatchers.anyString())).willReturn( new User() );
		userServiceImpl.addUser(newUser);
	}

	@Test
	public void testCreateUser()
	{
		given( userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn( null );
		given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);
		userServiceImpl.addUser(newUser);
	}
	// Testing for GetUser Deatils
	@Test (expected = BadRequestException.class)
	public void testGetUserWithEmail()
	{
		given( userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn( null );
		userServiceImpl.getUserByEmail(userDetails.getEmail().toLowerCase());
	}

	@Test
	public void testUserDetailsRetrived()
	{

		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		userServiceImpl.getUserByEmail(userDetails.getEmail().toLowerCase());
	}









}
