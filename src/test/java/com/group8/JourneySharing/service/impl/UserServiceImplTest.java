package com.group8.JourneySharing.service.impl;
import com.group8.JourneySharing.entity.Gender;
import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.Rating;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.vo.EditUserVo;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.RatingVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group8.JourneySharing.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith ( MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserServiceImpl userServiceImpl;

	private NewUserVo newUser;
	private User user;
	private UserDetailsVo userDetails;
	private Journey journey;
	private List<RatingVo> ratings;
	private RatingVo rating;
	private EditUserVo editUserVo;
	
	@Mock
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	JourneyRepository journeyRepository;

	@Mock
	JourneyService journeyService;


	@Before
	public void init() {
		
		userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserRepository(userRepository);
		userServiceImpl.setPasswordEncoder(passwordEncoder);
		userServiceImpl.setJourneyRepository(journeyRepository);
		userServiceImpl.setJourneyService(journeyService);
		newUser = new NewUserVo("email", "password","firstName","lastName");
		user = new User("email", "Unique char","password","firstName","lastName","mobile number","iban", new ArrayList<String>(),20, Gender.FEMALE, new Rating(10.0,2));
        userDetails = new UserDetailsVo("email", "firstName","lastName", "mobileNumber","iban", null);
        journey = new Journey("journeyId", "name", false, false, "ownerEmail", null,
				null, null, 6, null, null, null, null, null,
		false, false, 0);
        ratings = new ArrayList<>();
        rating = new RatingVo("userEmail", 5.0);
        ratings.add(rating);
        editUserVo = new EditUserVo();
        editUserVo.setGender(Gender.MALE);
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
		given( passwordEncoder.encode(ArgumentMatchers.anyString())).willReturn( "password" );
		given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);
		userServiceImpl.addUser(newUser);
	}

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

	@Test (expected = BadRequestException.class)
	public void testGetUserByIdForInvalidUser()
	{
		given( userRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(null) );
		userServiceImpl.getUserByID("id");
	}

	@Test
	public void testGetUserById()
	{
		given( userRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(user) );
		userServiceImpl.getUserByID("id");
	}

	@Test (expected = BadRequestException.class)
	public void testAddToHistoryInvalidJourney()
	{
		given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(null) );
		userServiceImpl.addToHistory("userEmail", "journeyId");
	}

	@Test (expected = BadRequestException.class)
	public void testAddToHistoryInvalidUserEmail()
	{
		given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.of(journey) );
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(null);
		userServiceImpl.addToHistory("userEmail", "journeyId");
	}

	@Test
	public void testAddToHistory()
	{
		given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.of(journey) );
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);
		userServiceImpl.addToHistory("userEmail", "journeyId");
	}

	@Test (expected = BadRequestException.class)
	public void testGetRatingInvalidUser ()
	{
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(null);
		userServiceImpl.getRating("userEmail");
	}

	@Test
	public void testGetRating()
	{
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		double rating = userServiceImpl.getRating("userEmail");
		Assert.assertEquals(5.0, rating,0);
	}


	@Test
	public void testAddRating()
	{
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);
		userServiceImpl.addRating(ratings);
	}

	@Test(expected = BadRequestException.class)
	public void testEditUserInvalidUser()
	{
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(null);
		userServiceImpl.editUser("userEmail", editUserVo);
	}

	@Test
	public void testEditUserInvalidGender()
	{
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		userServiceImpl.editUser("userEmail", editUserVo);
	}

	@Test
	public void testEditUser()
	{
		editUserVo.setGender(null);
		editUserVo.setAge(25);
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		userServiceImpl.editUser("userEmail", editUserVo);
	}

	@Test
	public void testGetPaymentDetails()
	{
		given(journeyService.getJourneyByID(ArgumentMatchers.anyString())).willReturn(journey);
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		userServiceImpl.getPaymentDetails("journeyId");
	}

	@Test (expected = BadRequestException.class)
	public void testGetPaymentDetailsNoDetails()
	{
		user.setMobileNumber(null);
		user.setIban(null);
		given(journeyService.getJourneyByID(ArgumentMatchers.anyString())).willReturn(journey);
		given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(user);
		userServiceImpl.getPaymentDetails("journeyId");
	}

	@Test
	public void testDeleteUser() {
		doNothing().when(userRepository).deleteByEmail(ArgumentMatchers.anyString());
		userServiceImpl.deleteUser("userEmail");
	}

}
