package br.inatel.dm110.api.auditing;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuditingTO implements Serializable {

	private static final long serialVersionUID = -3226154108429480413L;

	private Integer identifier;

	private String registerFrom;
	private String registerCode;

	private String operation;

	private LocalDateTime dateTime;


	public AuditingTO() {} //default constructor

	public AuditingTO(Integer identifier, String registerFrom, String registerCode, String operation, LocalDateTime dateTime) {
		super();

		this.identifier = identifier;
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
		return "AuditingTO{" +
				"identifier=" + identifier +
				", registerFrom='" + registerFrom + '\'' +
				", registerCode='" + registerCode + '\'' +
				", operation='" + operation + '\'' +
				", dateTime=" + dateTime +
				'}';
	}
}
