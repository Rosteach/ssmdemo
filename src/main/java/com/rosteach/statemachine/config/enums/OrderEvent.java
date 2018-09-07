package com.rosteach.statemachine.config.enums;

public enum OrderEvent {
	TRANSITION_TO_VERIFIED,
	TRANSITION_TO_CONFIRMED,
	TRANSITION_TO_PARTIALY_PAYED,
	TRANSITION_TO_DELIVERED,
	TRANSITION_TO_COMPLETE,
	TRANSITION_TO_CANCELLED;
}