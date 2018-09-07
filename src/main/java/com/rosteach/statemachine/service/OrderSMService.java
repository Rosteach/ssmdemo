package com.rosteach.statemachine.service;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;

import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSMService {

	private final StateMachineService<OrderState, OrderEvent> service;
	private final StateMachinePersist<OrderState, OrderEvent, String> persist;

	public StateMachineContext<OrderState, OrderEvent> updateState(final String machineId, final OrderEvent event)
			throws Exception {

		final StateMachine<OrderState, OrderEvent> sm = service.acquireStateMachine(machineId, true);
		sm.sendEvent(event);
		service.releaseStateMachine(sm.getId(), true);
		return persist.read(sm.getId());
	}

	public StateMachineContext<OrderState, OrderEvent> getSMContext(String id) throws Exception {
		return persist.read(id);
	}
}
