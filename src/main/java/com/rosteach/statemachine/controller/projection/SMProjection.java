package com.rosteach.statemachine.controller.projection;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.OrderEvent;

import lombok.Data;

@Data
public class SMProjection {
	private String event;
	private String adId;
	private String orderId;

	public String getMachineId() {
		return this.adId.concat("_").concat(this.orderId);
	}

	public AdEvent getAdEvent() {
		return AdEvent.valueOf(this.event);
	}

	public OrderEvent getOrderEvent() {
		return OrderEvent.valueOf(this.event);
	}
}
