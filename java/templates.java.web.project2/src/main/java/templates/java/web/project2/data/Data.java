package templates.java.web.project2.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.NotFoundException;

import templates.java.web.project2.jaxb.Jaxb1;

public class Data {

	private static final Data INSTANCE = new Data();
	private List<Jaxb1> jaxbList;

	private Data() {

		try {

			jaxbList = new ArrayList<Jaxb1>();
			jaxbList.add(new Jaxb1(System.currentTimeMillis(), new Date(), "João"));
			Thread.sleep(10);
			jaxbList.add(new Jaxb1(System.currentTimeMillis(), new Date(), "Roberto"));
			Thread.sleep(10);
			jaxbList.add(new Jaxb1(System.currentTimeMillis(), new Date(), "Maria"));
			Thread.sleep(10);
			jaxbList.add(new Jaxb1(System.currentTimeMillis(), new Date(), "Pedro"));

		} catch (Exception e) {

			Logger.getGlobal().severe("Erro ao criar objetos JAXB.");
		}
	}

	public static Data getInstance() {
		return INSTANCE;
	}

	public List<Jaxb1> getJaxbList() {
		return jaxbList;
	}

	public Jaxb1 getJaxbById(long id) throws NotFoundException {

		for (Jaxb1 jaxb : jaxbList) {

			if (jaxb.getId() == id) {
				return jaxb;
			}
		}

		throw new NotFoundException();
	}

}
