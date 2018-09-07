package com.rosteach.statemachine.config;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdSMListener implements StateMachineListener<AdState, AdEvent> {
	@Override
	public void stateChanged(State<AdState, AdEvent> from, State<AdState, AdEvent> to) {
		log.info("State changed to {} ", to.getId());
	}

	@Override
	public void stateEntered(State<AdState, AdEvent> state) {
		log.info("State {} is entered ", state.getId());
	}

	@Override
	public void stateExited(State<AdState, AdEvent> state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventNotAccepted(Message<AdEvent> event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transition(Transition<AdState, AdEvent> transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transitionStarted(Transition<AdState, AdEvent> transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transitionEnded(Transition<AdState, AdEvent> transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateMachineStarted(StateMachine<AdState, AdEvent> stateMachine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateMachineStopped(StateMachine<AdState, AdEvent> stateMachine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateMachineError(StateMachine<AdState, AdEvent> stateMachine, Exception exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extendedStateChanged(Object key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateContext(StateContext<AdState, AdEvent> stateContext) {
		// TODO Auto-generated method stub

	}
}
