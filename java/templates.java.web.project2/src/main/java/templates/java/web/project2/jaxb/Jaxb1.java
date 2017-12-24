package templates.java.web.project2.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "date", "description" })
public class Jaxb1 {

	private long id;
	private Date date;
	private String description;

	public Jaxb1() {
	}

	public Jaxb1(long id, Date date, String description) {
		this.id = id;
		this.date = date;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
