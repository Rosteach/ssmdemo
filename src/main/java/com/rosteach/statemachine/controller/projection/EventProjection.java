package com.rosteach.statemachine.controller.projection;

import com.rosteach.statemachine.config.enums.Events;

import lombok.Data;

@Data
public class EventProjection {
	private Events event;
	private String machineId;
}
