package com.rosteach.statemachine.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import com.rosteach.statemachine.action.OrderCompleteAction;
import com.rosteach.statemachine.action.OrderConfirmedAction;
import com.rosteach.statemachine.action.OrderDeliveredAction;
import com.rosteach.statemachine.action.OrderPartialyPayedAction;
import com.rosteach.statemachine.action.OrderVerifiedAction;
import com.rosteach.statemachine.action.error.OrderCompleteErrorAction;
import com.rosteach.statemachine.action.error.OrderConfirmedErrorAction;
import com.rosteach.statemachine.action.error.OrderDeliveredErrorAction;
import com.rosteach.statemachine.action.error.OrderPartialyPayedErrorAction;
import com.rosteach.statemachine.action.error.OrderVerifiedErrorAction;
import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;
import com.rosteach.statemachine.guard.OrderCancelledGuard;
import com.rosteach.statemachine.guard.OrderCompleteGuard;
import com.rosteach.statemachine.guard.OrderConfirmedGuard;
import com.rosteach.statemachine.guard.OrderDeliveredGuard;
import com.rosteach.statemachine.guard.OrderPartialyPayedGuard;
import com.rosteach.statemachine.guard.OrderVerifiedGuard;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableStateMachineFactory(name = "sm_order")
@RequiredArgsConstructor
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {
	// Global
	private final OrderSMListener listener;
	private final StateMachineRuntimePersister<OrderState, OrderEvent, String> persister;

	// Actions
	private final OrderVerifiedAction verifiedAction;
	private final OrderConfirmedAction confirmedAction;
	private final OrderPartialyPayedAction partialyPayedAction;
	private final OrderDeliveredAction deliveredAction;
	private final OrderCompleteAction completeAction;
	private final OrderConfirmedAction cancelledAction;

	// Actions on error
	private final OrderVerifiedErrorAction verifiedErrorAction;
	private final OrderConfirmedErrorAction confirmedErrorAction;
	private final OrderPartialyPayedErrorAction partialyPayedErrorAction;
	private final OrderDeliveredErrorAction deliveredErrorAction;
	private final OrderCompleteErrorAction completeErrorAction;
	private final OrderConfirmedErrorAction cancelledErrorAction;

	// Guards
	private final OrderVerifiedGuard verifiedGuard;
	private final OrderConfirmedGuard confirmedGuard;
	private final OrderPartialyPayedGuard partialyPayedGuard;
	private final OrderDeliveredGuard deliveredGuard;
	private final OrderCompleteGuard completeGuard;
	private final OrderCancelledGuard cancelledGuard;

	@Override
	public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
		// @formatter:off
		config
			.withConfiguration()
				.listener(listener)
			.and()
			.withPersistence()
				.runtimePersister(persister);
		// @formatter:on
	}

	@Override
	public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
		// @formatter:off
		states
			.withStates()
				.initial(OrderState.DRAFT)
				.states(EnumSet.allOf(OrderState.class));
		// @formatter:on
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
		// @formatter:off
		transitions
			.withExternal()
				.source(OrderState.DRAFT)
				.target(OrderState.VERIFIED)
				.event(OrderEvent.TRANSITION_TO_VERIFIED)
				.action(verifiedAction, verifiedErrorAction)
				.guard(verifiedGuard)
			.and()
			.withExternal()
				.source(OrderState.VERIFIED)
				.target(OrderState.CONFIRMED)
				.event(OrderEvent.TRANSITION_TO_CONFIRMED)
				.action(confirmedAction, confirmedErrorAction)
				.guard(confirmedGuard)
			.and()
			.withExternal()
				.source(OrderState.CONFIRMED)
				.target(OrderState.PARTIALY_PAYED)
				.event(OrderEvent.TRANSITION_TO_PARTIALY_PAYED)
				.action(partialyPayedAction, partialyPayedErrorAction)
				.guard(partialyPayedGuard)
			.and()
			.withExternal()
				.source(OrderState.PARTIALY_PAYED)
				.target(OrderState.DELIVERED)
				.event(OrderEvent.TRANSITION_TO_DELIVERED)
				.action(deliveredAction, deliveredErrorAction)
				.guard(deliveredGuard)
			.and()
			.withExternal()
				.source(OrderState.DELIVERED)
				.target(OrderState.COMPLETE)
				.event(OrderEvent.TRANSITION_TO_COMPLETE)
				.action(completeAction, completeErrorAction)
				.guard(completeGuard)
			.and()
			.withExternal()
				.target(OrderState.CANCELLED)
				.event(OrderEvent.TRANSITION_TO_CANCELLED)
				.action(cancelledAction, cancelledErrorAction)
				.guard(cancelledGuard);
		// @formatter:on
	}
}
