package user.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm
{
	private int userID;
	private String userName;
	private String password;
	private int roleId;
	private String employeeName;
	private String employeePhone;
	private int employeeType;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}
	public String getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}
	public int getEmployeeType() {
		return employeeType;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getRoleId() {
		return roleId;
	}
	
}
