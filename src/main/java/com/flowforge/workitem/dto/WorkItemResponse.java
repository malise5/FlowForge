package com.flowforge.workitem.dto;

import com.flowforge.workitem.domain.enums.WorkItemState;
import java.time.Instant;
import java.util.UUID;

public record WorkItemResponse(UUID id, String title, String description, WorkItemState currentState, Instant createdAt) {
}
