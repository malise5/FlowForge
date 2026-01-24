package com.flowforge.workitem.service;

import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.domain.enums.WorkItemState;
import java.util.UUID;

public interface WorkItemTransitionService {
    public WorkItem transition(UUID workItemId, WorkItemState toState, String reason);
}
