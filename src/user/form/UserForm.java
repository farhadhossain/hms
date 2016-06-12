package user.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm
{
	private int userID;
	private String userName;
	private String password;
	private int roleId;

	public int[] getRoleList() {
		return roleList;
	}

	public void setRoleList(int[] roleList) {
		this.roleList = roleList;
	}

	private int [] roleList;
	private String employeeName;
	private String employeePhone;
	private int employeeType;
	private String designation;

	private String studentId;
	private String program;
	private String phase;
	private String block;
	
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}
}
