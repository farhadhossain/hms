package user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import prescription.action.ActionSupport;
import user.UserDAO;
import user.UserDTO;

import java.util.List;

/**
 * Created by farhad on 6/27/16.
 */
@ParentPackage("json-default")
public class UserAction extends ActionSupport {

    @Action(value = "/rest/users/get", results = {@Result(name="success",type="json") })
    public String findUserByProgram() {
        UserDAO dao = new UserDAO();
        users = dao.getUserByProgram(program);
        return ActionSupport.SUCCESS;
    }

    private String program;

    public void setProgram(String program) {
        this.program = program;
    }

    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }
}
