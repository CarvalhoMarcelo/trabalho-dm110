package br.inatel.dm110.product.entities.auditing;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUDITING", schema = "public")
public class Auditing implements Serializable {

	private static final long serialVersionUID = -3226154108429480413L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IDENTIFIER", unique = true)
	private Integer identifier;

	@Column(name = "REGISTERFROM")
	private String registerFrom;

	@Column(name = "REGISTERCODE")
	private String registerCode;

	@Column(name = "OPERATION")
	private String operation;

	@Column(name = "DATETIME")
	private LocalDateTime dateTime;


	public Auditing() {} //default constructor

	public Auditing(String registerFrom, String registerCode, String operation, LocalDateTime dateTime) {
		super();

		this.registerFrom = registerFrom;
		this.registerCode = registerCode;
		this.operation = operation;
		this.dateTime = dateTime;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Auditing{" +
				"identifier=" + identifier +
				", registerFrom='" + registerFrom + '\'' +
				", registerCode='" + registerCode + '\'' +
				", operation='" + operation + '\'' +
				", dateTime=" + dateTime +
				'}';
	}
}
