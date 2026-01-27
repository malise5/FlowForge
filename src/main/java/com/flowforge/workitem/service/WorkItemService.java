package com.flowforge.workitem.service;

import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.dto.WorkItemResponse;

public interface WorkItemService {
    WorkItemResponse create(String title, String description);
}
