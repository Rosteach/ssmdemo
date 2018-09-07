package com.rosteach.statemachine.service;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdSMService {
	private final StateMachineService<AdState, AdEvent> service;
	private final StateMachinePersist<AdState, AdEvent, String> persist;

	public StateMachineContext<AdState, AdEvent> getSMContext(String id) throws Exception {
		return persist.read(id);
	}

	public StateMachineContext<AdState, AdEvent> updateState(String machineId, AdEvent event) throws Exception {
		final StateMachine<AdState, AdEvent> sm = service.acquireStateMachine(machineId, true);
		sm.sendEvent(event);
		service.releaseStateMachine(sm.getId(), true);
		return persist.read(sm.getId());
	}
}
