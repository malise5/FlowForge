package com.flowforge.workitem.dto;

import com.flowforge.workitem.domain.enums.WorkItemState;

import jakarta.validation.constraints.NotNull;

public record TransitionWorkItemRequest(@NotNull WorkItemState toState, String reason) {
}
