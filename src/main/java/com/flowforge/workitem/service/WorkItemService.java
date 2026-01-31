package com.flowforge.workitem.service;

import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.dto.CreateWorkItemRequest;
import com.flowforge.workitem.dto.WorkItemResponse;
import java.util.UUID;

public interface WorkItemService {
    WorkItemResponse create(CreateWorkItemRequest req);
    void delete(UUID id);
}
