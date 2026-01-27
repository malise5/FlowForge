package com.flowforge.workitem.service;

import com.flowforge.workitem.dto.StateTransitionResponse;
import java.util.List;
import java.util.UUID;

public interface WorkItemQueryService {
    public List<StateTransitionResponse> getTimeline(UUID workItemId);
}
