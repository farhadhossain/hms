package role.form;
import org.apache.struts.action.ActionForm;

public class RoleForm extends ActionForm
{
	private int roleID;
	private String roleName;
	private boolean patientAdd;
	private boolean patientEdit;
	private boolean patientSearch;
	
	private boolean reportView;
	
	private boolean userAdd;
	private boolean userEdit;
	private boolean userSearch;
	private boolean userDelete;
	
	private boolean roleAdd;
	private boolean roleEdit;
	private boolean roleSearch;
	private boolean roleDelete;
		
	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public boolean isUserAdd() {
		return userAdd;
	}

	public void setUserAdd(boolean userAdd) {
		this.userAdd = userAdd;
	}

	public boolean isUserEdit() {
		return userEdit;
	}

	public void setUserEdit(boolean userEdit) {
		this.userEdit = userEdit;
	}

	public boolean isUserSearch() {
		return userSearch;
	}

	public void setUserSearch(boolean userSearch) {
		this.userSearch = userSearch;
	}

	public boolean isUserDelete() {
		return userDelete;
	}

	public void setUserDelete(boolean userDelete) {
		this.userDelete = userDelete;
	}

	public boolean isRoleAdd() {
		return roleAdd;
	}

	public void setRoleAdd(boolean roleAdd) {
		this.roleAdd = roleAdd;
	}

	public boolean isRoleEdit() {
		return roleEdit;
	}

	public void setRoleEdit(boolean roleEdit) {
		this.roleEdit = roleEdit;
	}

	public boolean isRoleSearch() {
		return roleSearch;
	}

	public void setRoleSearch(boolean roleSearch) {
		this.roleSearch = roleSearch;
	}

	public boolean isRoleDelete() {
		return roleDelete;
	}

	public void setRoleDelete(boolean roleDelete) {
		this.roleDelete = roleDelete;
	}

	

	public void setReportView(boolean reportView) {
		this.reportView = reportView;
	}

	public boolean isReportView() {
		return reportView;
	}

	public void setPatientAdd(boolean patientAdd) {
		this.patientAdd = patientAdd;
	}

	public boolean isPatientAdd() {
		return patientAdd;
	}

	public void setPatientEdit(boolean patientEdit) {
		this.patientEdit = patientEdit;
	}

	public boolean isPatientEdit() {
		return patientEdit;
	}

	public void setPatientSearch(boolean patientSearch) {
		this.patientSearch = patientSearch;
	}

	public boolean isPatientSearch() {
		return patientSearch;
	}
	
}
