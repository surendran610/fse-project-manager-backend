package com.cts.fse.projectmanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.fse.projectmanager.dto.ResponseEntity;
import com.cts.fse.projectmanager.dto.UIUsers;
import com.cts.fse.projectmanager.dto.Users;
import com.cts.fse.projectmanager.services.UIService;

@SpringBootTest
public class UIControllerTest {

	@InjectMocks
	private UIController uiController;
	
	@Mock
	private UIService uiService;
	
	@Test
	public void testCreateUser() {
		when(uiService.createUser(getUser())).thenReturn(false);
		ResponseEntity<String> response=uiController.createUser(getUser());
		assertEquals(response.getResponseCode(), "500");
	}
	
	@Test
	public void testUpdateUser() {
		when(uiService.updateUser(getUser(),1)).thenReturn(false);
		ResponseEntity<String> response=uiController.updateUser(getUser(),1);
		assertEquals(response.getResponseCode(), "500");	
	}
	
	@Test
	public void testGetUser() {
		when(uiService.findUserById(1)).thenReturn(getUser());
		Users response=uiController.getUser(1);
		assertEquals(response.getEmpId(), "12345");	
	}
	
	@Test
	public void testGetAllUsers() {
		when(uiService.findAllUsers()).thenReturn(listUser());
		List<UIUsers> response=uiController.getAllUsers();
		assertEquals(response.size(), 1);	
	}

	@Test
	public void testDeleteBook() {
		doNothing().when(uiService).deleteUserByid(1);
		uiController.deleteBook(1);
	}
	
	
	private Users getUser() {
		Users user=new Users();
		user.setEmpId("12345");
		user.setFirstName("Suren");
		user.setLastName("Suren");
		return user;
	}

	private UIUsers getUIUser() {
		UIUsers user=new UIUsers();
		user.setEmpId("12345");
		user.setFirstName("Suren");
		user.setLastName("Suren");
		return user;
	}

	
	private List<UIUsers> listUser() {
		List<UIUsers>userLst=new ArrayList<UIUsers>();
		userLst.add(getUIUser());
		return userLst;
	}
}
