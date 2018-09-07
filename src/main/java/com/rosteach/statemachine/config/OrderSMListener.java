package com.rosteach.statemachine.config;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderSMListener implements StateMachineListener<OrderState, OrderEvent> {
	@Override
	public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
		log.info("State changed to {} ", to.getId());
	}

	@Override
	public void stateEntered(State<OrderState, OrderEvent> state) {
		log.info("State {} is entered ", state.getId());
	}

	@Override
	public void stateExited(State<OrderState, OrderEvent> state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventNotAccepted(Message<OrderEvent> event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transition(Transition<OrderState, OrderEvent> transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transitionStarted(Transition<OrderState, OrderEvent> transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transitionEnded(Transition<OrderState, OrderEvent> transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateMachineStarted(StateMachine<OrderState, OrderEvent> stateMachine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateMachineStopped(StateMachine<OrderState, OrderEvent> stateMachine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateMachineError(StateMachine<OrderState, OrderEvent> stateMachine, Exception exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extendedStateChanged(Object key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateContext(StateContext<OrderState, OrderEvent> stateContext) {
		// TODO Auto-generated method stub

	}
}
