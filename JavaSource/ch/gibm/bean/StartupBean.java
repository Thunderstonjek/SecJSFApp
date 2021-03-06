package ch.gibm.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.entity.Role;
import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;
import ch.gibm.util.Hashers;

@ApplicationScoped
@ManagedBean(eager = true)
public class StartupBean {

	/**
	 * initialize EntityManagerFactory at application startup
	 */
	@PostConstruct
	public void init() {
		EntityManagerHelper.init();

		UserFacade facade = new UserFacade();
		// Create dummy ADMIN user if not exist
		if (!facade.checkIfUserExists("admin")) {
			UserDAO dao = new UserDAO();
			EntityManagerHelper.beginTransaction();
			// dao.save(new User("admin", Hashers.md5("admin"), Role.ADMIN));
			dao.save(new User("admin", Hashers.sha256WithSalt("admin", Hashers.generateSalt()), Role.ADMIN));
			EntityManagerHelper.commitAndCloseTransaction();
		}
	}
}
