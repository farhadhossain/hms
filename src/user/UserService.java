package user;

import java.util.ArrayList;

import login.LoginDTO;

import utility.DAOResult;

public class UserService {
	
	public DAOResult addUser(UserDTO dto) {
		UserDAO dao = new UserDAO();
		return dao.addUser(dto);
	}
	public ArrayList<UserDTO> getAllUsers(String userName, int roleType){
		UserDAO dao = new UserDAO();
		return dao.getAllUsers(userName, roleType);
	}

	public boolean dropUser(int userID) {
		UserDAO dao = new UserDAO();
		return dao.dropUser(userID);
	}
	
	public UserDTO getUserDTO(int userID){
		UserDAO dao = new UserDAO();
		return dao.getUserDTO(userID);
	}
	
	public DAOResult updateUserDTO(UserDTO dto){
		UserDAO dao = new UserDAO();
		return dao.updateUserDTO(dto);
	}
	public DAOResult updateProfile(LoginDTO loginDTO, String employeeName, String newPassword) {
		return new UserDAO().updateProfile(loginDTO, employeeName, newPassword);
	}
	
}
