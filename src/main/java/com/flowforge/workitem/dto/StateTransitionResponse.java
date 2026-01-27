package com.flowforge.workitem.dto;

import com.flowforge.workitem.domain.enums.WorkItemState;
import java.time.Instant;

public record StateTransitionResponse(WorkItemState fromState, WorkItemState toState, Instant changedAt) {
}
