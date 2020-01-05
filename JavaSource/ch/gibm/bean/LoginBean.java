package ch.gibm.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.entity.Role;
import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;
import ch.gibm.util.Hashers;

@ManagedBean
@RequestScoped
public class LoginBean extends AbstractBean {
	public static final String ATTR_USER = "user";

	@ManagedProperty(value = UserBean.DI_NAME)
	private UserBean userBean;

	private String email;
	private String password;
	
	UserFacade userFacade = new UserFacade();

	public String login() {

		User user = userFacade.getUserIfExists(this.email, this.password);

		if (user != null) {
			userBean.setLoggedInUser(user);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
			req.getSession().setAttribute(ATTR_USER, user);
			return "/pages/protected/index.xhtml?faces-redirect=true";
			
		} else {
			keepDialogOpen();
			displayErrorMessageToUser("Wrong Email/Password. Try again");
			
		}
		return null;
	}
	
	public String register() {
		if(userFacade.checkIfUserExists(this.email)) {
			keepDialogOpen();
			displayErrorMessageToUser("User could not be created.");
			return null;
		} else {
			UserDAO dao = new UserDAO();
			EntityManagerHelper.beginTransaction();
			User newuser = new User(this.email, Hashers.sha256WithSalt(this.password, Hashers.generateSalt()), Role.USER);
			dao.save(newuser);
			EntityManagerHelper.commitAndCloseTransaction();
			userBean.setLoggedInUser(newuser);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
			req.getSession().setAttribute(ATTR_USER, newuser);
			return "/pages/protected/index.xhtml?faces-redirect=true";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
