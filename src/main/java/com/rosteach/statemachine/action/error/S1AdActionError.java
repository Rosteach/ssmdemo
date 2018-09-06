package com.rosteach.statemachine.action.error;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;
import com.rosteach.statemachine.service.OrderSMService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class S1AdActionError implements Action<States,Events>{
	
	private final OrderSMService orderSMService;

	@Override
	public void execute(StateContext<States, Events> context) {
		log.info("S1AdActionErorr execution");
	}

}
