package com.rosteach.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;
import com.rosteach.statemachine.controller.projection.EventProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSMService {
	@Autowired
	@Qualifier("sm_order_service")
	private StateMachineService<States, Events> service;
	private final StateMachinePersist<States, Events, String> persist;

	public StateMachineContext<States, Events> updateState(EventProjection projection) throws Exception {
		final StateMachine<States, Events> sm = service.acquireStateMachine(projection.getMachineId(),
				true);
		sm.sendEvent(projection.getEvent());
		service.releaseStateMachine(sm.getId(), true);
		return persist.read(sm.getId());
	}

	public StateMachineContext<States, Events> get(String id) throws Exception{
		return persist.read(id);
	}
}
