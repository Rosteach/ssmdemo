package com.rosteach.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdUnpublishGuard implements Guard<AdState, AdEvent> {

	@Override
	public boolean evaluate(StateContext<AdState, AdEvent> context) {
		log.info("Guard for AD UNPUBLISH state evaluation");
		return true;
	}

}
