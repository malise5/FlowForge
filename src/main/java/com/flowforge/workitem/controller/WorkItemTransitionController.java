package com.flowforge.workitem.controller;

import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.dto.TransitionWorkItemRequest;
import com.flowforge.workitem.dto.WorkItemResponse;
import com.flowforge.workitem.service.WorkItemTransitionService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/work-items")
public class WorkItemTransitionController {

    private final WorkItemTransitionService service;

    public WorkItemTransitionController(WorkItemTransitionService service) {
        this.service = service;
    }

    @PostMapping("/{id}/transition")
    public WorkItemResponse transition(@PathVariable UUID id, @Valid @RequestBody TransitionWorkItemRequest request) {
        return service.transition(id, request.toState());
    }
}
