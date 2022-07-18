package br.ufrn.imd.mmgg.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "internment")
public class Internment implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column("id")
	private Integer id;
	
	@Column("patient_id")
	@NotNull(message = "The field 'patientId' is mandatory.")
	private Integer patientId;
	
	@Column("date_time")
	private LocalDateTime dateTime = LocalDateTime.now();
	
	@Column("status")
	private boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
