package com.rosteach.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class S1Guard implements Guard<States,Events>{

	@Override
	public boolean evaluate(StateContext<States, Events> context) {
		log.info("S1Guard evaluation");
		return true;
	}

}
