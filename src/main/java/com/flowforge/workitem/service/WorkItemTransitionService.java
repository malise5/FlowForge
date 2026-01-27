package com.flowforge.workitem.service;

import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.domain.enums.WorkItemState;
import com.flowforge.workitem.dto.WorkItemResponse;
import java.util.UUID;

public interface WorkItemTransitionService {
    public WorkItemResponse transition(UUID workItemId, WorkItemState toState, String reason);
}
