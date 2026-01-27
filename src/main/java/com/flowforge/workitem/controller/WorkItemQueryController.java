package com.flowforge.workitem.controller;

import com.flowforge.workitem.dto.StateTransitionResponse;
import com.flowforge.workitem.service.WorkItemQueryService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/work-items")
public class WorkItemQueryController {

    private final WorkItemQueryService service;

    public WorkItemQueryController(WorkItemQueryService service) {
        this.service = service;
    }

    @GetMapping("/{id}/timeline")
    public List<StateTransitionResponse> timeline(@PathVariable UUID id) {
        return service.getTimeline(id);
    }


}
