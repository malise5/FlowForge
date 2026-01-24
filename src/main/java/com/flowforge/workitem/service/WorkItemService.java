package com.flowforge.workitem.service;

import com.flowforge.workitem.domain.entity.WorkItem;

public interface WorkItemService {
    WorkItem create(String title, String description);
}
