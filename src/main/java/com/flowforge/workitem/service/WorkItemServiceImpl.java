package com.flowforge.workitem.service;

import com.flowforge.common.exception.ResourceNotFoundException;
import com.flowforge.common.exception.UnauthorizedActionException;
import com.flowforge.workitem.domain.entity.StateTransition;
import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.domain.enums.WorkItemState;
import com.flowforge.workitem.dto.CreateWorkItemRequest;
import com.flowforge.workitem.dto.WorkItemResponse;
import com.flowforge.workitem.repository.StateTransitionRepository;
import com.flowforge.workitem.repository.WorkItemRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.flowforge.auth.security.SecurityUtils.getCurrentUserEmail;
import static com.flowforge.auth.security.SecurityUtils.hasRole;


@Service
public class WorkItemServiceImpl implements WorkItemService {

    private final WorkItemRepository workItemRepository;

    private final StateTransitionRepository transitionRepository;

    public WorkItemServiceImpl(WorkItemRepository workItemRepository, StateTransitionRepository transitionRepository) {
        this.workItemRepository = workItemRepository;
        this.transitionRepository = transitionRepository;
    }

    @Override
    @Transactional
    public WorkItemResponse create(CreateWorkItemRequest req) {
        String userEmail = getCurrentUserEmail();
        WorkItem item = new WorkItem(
                req.title(),
                req.description(),
                userEmail
        );
        workItemRepository.save(item);


        transitionRepository.save(
                new StateTransition(
                        item.getId(),
                        null,
                        WorkItemState.TODO
                )
        );

        return mapToResponse(item);
    }

    @Override
    public void delete(UUID id) {
        WorkItem item = workItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work item not found"));

        String currentUser = getCurrentUserEmail();

        if (!hasRole("ADMIN") && !item.getCreatedBy().equals(currentUser)) {
            throw new UnauthorizedActionException("You cannot delete someone else's work item");
        }

        workItemRepository.delete(item);
    }

    private WorkItemResponse mapToResponse(WorkItem item) {
        return new WorkItemResponse(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getCurrentState(),
                item.getCreatedAt(),
                item.getCreatedBy()
        );
    }

}
