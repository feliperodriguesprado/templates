/**
 *  DataSource template for Java.
    
    Copyright (C) 2017.
    
    Author: Felipe Prado <rodriguesprado.felipe@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package templates.java.web.project1.controllers;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

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
		// Define local para o Bean Validation:
		FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.getDefault());
	}

	public void access() {

		String display_username_password;

		// Obtem chave "display_username_password" da i18n:
		display_username_password = ResourceBundle.getBundle("i18n.messages", Locale.getDefault())
				.getString("display_username_password");

		Logger.getGlobal().info(String.format(display_username_password + "\n", userName, password));
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
