package com.kroger.pharmacy.csr.domain;

import javax.persistence.Transient;

public abstract class SelectableObject {
	protected boolean selected;

	@Transient
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
