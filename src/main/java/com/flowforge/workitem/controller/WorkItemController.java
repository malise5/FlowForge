package com.flowforge.workitem.controller;

import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.dto.CreateWorkItemRequest;
import com.flowforge.workitem.dto.WorkItemResponse;
import com.flowforge.workitem.service.WorkItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/work-items")
public class WorkItemController {

    private final WorkItemService workItemService;


    public WorkItemController(WorkItemService workItemService) {
        this.workItemService = workItemService;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkItemResponse create(@RequestBody CreateWorkItemRequest request){
        return workItemService.create(request.title(), request.description());
    }

}
