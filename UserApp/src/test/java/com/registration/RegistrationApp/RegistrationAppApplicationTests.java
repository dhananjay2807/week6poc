package com.registration.RegistrationApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.RegistrationApp.Dto.ResultModel;
import com.registration.RegistrationApp.Entity.Users;
import com.registration.RegistrationApp.Service.UserService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationAppApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	
	  @Autowired private WebApplicationContext webApplicationContext;
	  
	  @BeforeAll public void setup() {
	  mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
	 
	
	@Test
	public void createUserTest() throws Exception {
		Users mockUser = new Users();
		mockUser.setName("Dhananjay");
		mockUser.setSurname("Yadav");
		mockUser.setEmail("dhnnjy@gmail.com");
		mockUser.setDob(Date.valueOf("1997-07-28"));
		mockUser.setCity("Thane");
		mockUser.setContactNumber("7777777777");
		mockUser.setDesignation("Java");
		mockUser.setPinCode(400606);
		mockUser.setJoiningDate(Date.valueOf("2020-12-14"));

		List<String> stringList = new ArrayList<String>();
		stringList.add("Success");

		ResultModel resultModel = new ResultModel();
		resultModel.setMessage("Success");
		resultModel.setData(stringList);

		String inputInJson = this.mapToJson(resultModel);

		String Url = "/user/saveUpdateUser";

		Mockito.when(userService.saveUser(Mockito.any(Users.class))).thenReturn(stringList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Url).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	//add
	@Test
	public void UpdateUser() throws Exception {
		Users mockUser=new Users();
		mockUser.setUserId("NeoSoft_2");
		mockUser.setName("Soham");
		mockUser.setSurname("Surve");
		mockUser.setEmail("soham@gmail.com");
		mockUser.setDob(Date.valueOf("1998-02-11"));
		mockUser.setCity("Thane");
		mockUser.setContactNumber("987654321");
		mockUser.setDesignation("Java");
		mockUser.setPinCode(12344555);
		mockUser.setJoiningDate(Date.valueOf("2020-12-11"));

		List<String> stringList = new ArrayList<String>();
		stringList.add("Success");

		ResultModel resultModel = new ResultModel();
		resultModel.setMessage("Success");
		resultModel.setData(stringList);

		String inputInJson = this.mapToJson(resultModel);

		String Url = "/user/saveUpdateUser";

		Mockito.when(userService.saveUser(Mockito.any(Users.class))).thenReturn(stringList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Url).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	
	@Test
	public void getAllUsersTest() throws Exception {
		
		Users mock1=new Users();
		
		mock1.setId(2);//change
		mock1.setName("Soham");
		mock1.setSurname("Surve");
		mock1.setUserId("Neosoft_2");//change
		mock1.setPassword("soham123");
		mock1.setEmail("soham@gmail.com");
		mock1.setDob(Date.valueOf("1998-02-11"));
		mock1.setCity("Thane");
		mock1.setContactNumber("987654321");
		mock1.setPinCode(12344555);
		mock1.setDesignation("Java");
		mock1.setRole("Developer");
		mock1.setJoiningDate(Date.valueOf("2020-12-11"));
		
		
		Users mock2=new Users();
		
		mock2.setId(3);
		mock2.setName("Sudesh");
		mock2.setSurname("Patil");
		mock2.setUserId("Neosoft_3");
		mock2.setPassword("sudesh123");
		mock2.setEmail("sudesh@gmail.com");
		mock2.setDob(null);
		mock2.setCity("Pune");
		mock2.setContactNumber("987645621");
		mock2.setPinCode(425001);
		mock2.setDesignation("Angular Developer");
		mock2.setRole("Developer");
		mock2.setJoiningDate(null);
		
		List<Users> userList=new ArrayList<Users>();
		ResultModel resultModel = new ResultModel();
		userList.add(mock1);
		userList.add(mock2);
		resultModel.setData(userList);
		resultModel.setMessage("Success");
		
		String Url= "/user/getAllUsers";
		
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	

	@Test
	public void getUserByUserId() throws Exception{
		
		Users mock1=new Users();
		
		mock1.setId(1);
		mock1.setName("Dhananjay");
		mock1.setSurname("Yadav");
		mock1.setUserId("Neosoft_1");
		mock1.setPassword("dhananjay123");
		mock1.setEmail("dhnnjy@gmail.com");
		mock1.setDob(null);
		mock1.setCity("Thane");
		mock1.setContactNumber("987654321");
		mock1.setPinCode(425001);
		mock1.setDesignation("Java Developer");
		mock1.setRole("Developer");
		mock1.setJoiningDate(null);
		
		ResultModel resultModel=new ResultModel();
		resultModel.setData(mock1);
		resultModel.setMessage("Success");
		
		String Url="/user/getByUserId/Neosoft_1";
		
		Mockito.when(userService.getById(Mockito.anyString())).thenReturn(mock1);
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		
	}
	
	
	
	@Test
	public void deleteUserByIdTest() throws Exception {
		
		ResultModel resultModel=new ResultModel();
		resultModel.setMessage("Success");
		
		String Url="/user/deleteUserById/1";
		
		Mockito.when(userService.deleteUser(Mockito.anyInt())).thenReturn("Success");
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.delete(Url).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response= result.getResponse();
		
		String outputInJson=result.getResponse().getContentAsString();
		
		String expectedJson=this.mapToJson(resultModel);
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
		
		private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);//add
		final DateFormat df=new SimpleDateFormat("yyyy-MM-dd");//add
		objectMapper.setDateFormat(df);//add
		return objectMapper.writeValueAsString(object);
	}

}
