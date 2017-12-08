/**
 *  Java Web template.
    
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
package templates.java.web.project1.initialize;

import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Implementação da interface {@link ServletContextListener} para instruções de
 * inicialização e destruição da aplicação em conjunto com o contexto do
 * Servlet.
 */
@WebListener
public class InitializeServletContext implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		Logger.getGlobal().info("Java Web template initialized");

		Locale.setDefault(new Locale("pt", "BR"));
		Logger.getGlobal().info("Local padrão JVM = " + Locale.getDefault().toString());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Logger.getGlobal().info("Java Web template destroyed");
	}

}
