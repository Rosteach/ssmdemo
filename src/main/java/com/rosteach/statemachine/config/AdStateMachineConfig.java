package com.rosteach.statemachine.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import com.rosteach.statemachine.action.S1AdAction;
import com.rosteach.statemachine.action.S2AdAction;
import com.rosteach.statemachine.action.error.S1AdActionError;
import com.rosteach.statemachine.action.error.S2AdActionError;
import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;
import com.rosteach.statemachine.guard.S1Guard;
import com.rosteach.statemachine.guard.S2Guard;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableStateMachineFactory(name = "sm_ad")
public class AdStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
	private final SMListener listener;
	private final StateMachineRuntimePersister<States, Events, String> persister;

	// Actions
	private final S1AdAction s1Action;
	private final S2AdAction s2Action;
	private final S1AdActionError s1ActionError;
	private final S2AdActionError s2ActionError;

	// Guards
	private final S1Guard s1Guard;
	private final S2Guard s2Guard;

	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
		config
			.withConfiguration()
				.listener(listener)
			.and()
			.withPersistence()
			.runtimePersister(persister);
	}

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states.withStates().initial(States.S1).states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions
			.withExternal()
				.source(States.S1)
				.target(States.S2)
				.event(Events.E1)
				.action(s1Action, s1ActionError)
				.guard(s1Guard)
			.and()
				.withExternal()
				.source(States.S2)
				.target(States.S3)
				.event(Events.E2)
				.action(s2Action, s2ActionError)
				.guard(s2Guard);
	}
}
