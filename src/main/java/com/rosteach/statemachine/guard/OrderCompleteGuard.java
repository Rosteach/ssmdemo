package com.rosteach.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCompleteGuard implements Guard<OrderState,OrderEvent>{

	@Override
	public boolean evaluate(StateContext<OrderState, OrderEvent> context) {
		log.info("Guard for DRAFT state evaluation");
		return true;
	}

}
