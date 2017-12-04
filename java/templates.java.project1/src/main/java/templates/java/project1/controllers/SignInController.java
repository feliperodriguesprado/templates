package templates.java.project1.controllers;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;

@ViewScoped
@Named
public class SignInController implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "{user_name_not_empty}")
	private String userName;
	@NotEmpty(message = "{password_not_empty}")
	private String password;

	@PostConstruct
	public void init() {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("pt", "BR")); // Set locale to bean validation
	}

	public void access() {

		// Obtem i18n
		String display_username_password = ResourceBundle.getBundle("templates.java.project1.i18n.messages", Locale.getDefault())
				.getString("display_username_password");

		System.out.printf(display_username_password + "\n", userName, password);

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

}
