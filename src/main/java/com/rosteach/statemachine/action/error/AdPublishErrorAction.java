package com.rosteach.statemachine.action.error;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdPublishErrorAction implements Action<AdState, AdEvent> {
	@Override
	public void execute(StateContext<AdState, AdEvent> context) {
		log.info("transition to ad error: PUBLISH execution");
	}
}
