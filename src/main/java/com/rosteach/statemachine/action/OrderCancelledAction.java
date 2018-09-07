package com.rosteach.statemachine.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class OrderCancelledAction implements Action<OrderState,OrderEvent>{
	@Override
	public void execute(StateContext<OrderState, OrderEvent> context) {
		log.info("transition to state: CANCELLED execution");
	}
}
