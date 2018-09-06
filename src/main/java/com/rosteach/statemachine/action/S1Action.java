package com.rosteach.statemachine.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class S1Action implements Action<States,Events>{

	@Override
	public void execute(StateContext<States, Events> context) {
		log.info("S1Action execution");
	}

}
