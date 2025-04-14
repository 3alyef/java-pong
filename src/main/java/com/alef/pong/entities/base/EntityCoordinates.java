package com.alef.pong.entities.base;

public class EntityCoordinates {
	protected double var;

	public EntityCoordinates(double var) {
		this.var = var;
	}

	public double getValue() {
		return this.var;
	};

	public void setValue(double valueVar) {
		this.var = valueVar;
	}

	public void setValue(double valueVar, boolean increment) {
		if (increment) {
			this.var++;
		}
	}

}
