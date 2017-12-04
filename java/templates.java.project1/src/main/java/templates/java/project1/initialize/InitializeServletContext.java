package templates.java.project1.initialize;

import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializeServletContext implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		System.out.println("Web Application Initialized.");

		// Define default locale:
		Locale.setDefault(new Locale("pt", "BR"));
		Locale locale = Locale.getDefault();
		System.out.println("Default locale = " + locale.toString());

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Web Application Destroyed.");
	}

}
