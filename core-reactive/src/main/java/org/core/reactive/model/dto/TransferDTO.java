package org.core.reactive.model.dto;

import javax.validation.constraints.NotNull;

import org.core.reactive.enuns.TypeAction;

public class TransferDTO {


	@NotNull(message = "The field 'idPatient' is mandatory.")
	private Integer patientId;
	
	@NotNull(message = "The field 'typeAction' is mandatory and the possible values should be: CHECKIN, CHECKOUT, TO_ICU, TO_SURGERY_CENTER.")
	private TypeAction typeAction;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public TypeAction getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(TypeAction typeAction) {
		this.typeAction = typeAction;
	}
}
