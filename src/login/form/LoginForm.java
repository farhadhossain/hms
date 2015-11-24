package login.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm
{
  private String m_userName;
  private String m_passWord;

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


public void reset(ActionMapping p_mapping, HttpServletRequest p_request)
  {
  }


  public ActionErrors validate(ActionMapping p_mapping, HttpServletRequest p_request)
  {
    ActionErrors errors = new ActionErrors();

    if (false)
    {
      errors.add("  ", new ActionError("error.key.  "));
    }
    return errors;
  }


}
