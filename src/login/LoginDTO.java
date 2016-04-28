package login;

public class LoginDTO
{
  private int userID;
  private String m_userName;
  private String m_passWord;

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }

    private String roleList;
    private int roleID;
    private int clientType;
    public String roleName;
    public String employeeName;
    public String designation;
  
  public String getuserName() {
	  return m_userName;
  }
  public void setuserName(String p_userName) {
	  m_userName = p_userName;
  }
  public String getpassWord() {
	  return m_passWord;
  }
  public void setpassWord(String p_passWord) {
	  m_passWord = p_passWord;
  }
  public void setRoleID(int roleID) {
	  this.roleID = roleID;
  }
  public int getRoleID() {
	  return roleID;
  }
  public void setUserID(int userID) {
	  this.userID = userID;
  }
  public int getUserID() {
	  return userID;
  }
  public void setClientType(int clientType) {
	  this.clientType = clientType;
  }
  public int getClientType() {
	  return clientType;
  }
}
