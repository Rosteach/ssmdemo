package com.rosteach.statemachine.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import com.rosteach.statemachine.action.AdPublishAction;
import com.rosteach.statemachine.action.AdReservedAction;
import com.rosteach.statemachine.action.AdUnpublishAction;
import com.rosteach.statemachine.action.error.AdPublishErrorAction;
import com.rosteach.statemachine.action.error.AdReservedErrorAction;
import com.rosteach.statemachine.action.error.AdUnpublishErrorAction;
import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;
import com.rosteach.statemachine.guard.AdPublishGuard;
import com.rosteach.statemachine.guard.AdReservedGuard;
import com.rosteach.statemachine.guard.AdUnpublishGuard;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableStateMachineFactory(name = "sm_ad")
public class AdStateMachineConfig extends EnumStateMachineConfigurerAdapter<AdState, AdEvent> {
	// Global
	private final AdSMListener listener;
	private final StateMachineRuntimePersister<AdState, AdEvent, String> persister;

	// Actions
	private final AdPublishAction publishAction;
	private final AdUnpublishAction unpublishAction;
	private final AdReservedAction reservedAction;

	// Actions on error
	private final AdPublishErrorAction publishErrorAction;
	private final AdUnpublishErrorAction unpublishErrorAction;
	private final AdReservedErrorAction reservedErrorAction;

	// Guards
	private final AdPublishGuard publishGuard;
	private final AdUnpublishGuard unpublishGuard;
	private final AdReservedGuard reservedGuard;

	@Override
	public void configure(StateMachineConfigurationConfigurer<AdState, AdEvent> config) throws Exception {
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
	public void configure(StateMachineStateConfigurer<AdState, AdEvent> states) throws Exception {
		// @formatter:off
		states
			.withStates()
				.initial(AdState.INITIAL)
				.states(EnumSet.allOf(AdState.class));
		// @formatter:on
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<AdState, AdEvent> transitions) throws Exception {
		// @formatter:off
		transitions
			.withExternal()
				.source(AdState.INITIAL)
				.target(AdState.PUBLISH)
				.event(AdEvent.TRANSITION_TO_PUBLISH)
				.action(publishAction, publishErrorAction)
				.guard(publishGuard)
			.and()
			.withExternal()
				.source(AdState.PUBLISH)
				.target(AdState.RESERVED)
				.event(AdEvent.TRANSITION_TO_RESERVED)
				.action(reservedAction, reservedErrorAction)
				.guard(reservedGuard)
			.and()
			.withExternal()
				.source(AdState.PUBLISH)
				.target(AdState.UNPUBLISH)
				.event(AdEvent.TRANSITION_TO_UNPUBLISH)
				.action(unpublishAction, unpublishErrorAction)
				.guard(unpublishGuard);
		// @formatter:on
	}
}
