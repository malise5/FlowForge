package com.flowforge.workitem.service;

import com.flowforge.auth.security.SecurityConfig;
import com.flowforge.common.exception.InvalidStateTransitionException;
import com.flowforge.common.exception.UnauthorizedActionException;
import com.flowforge.workitem.domain.entity.StateTransition;
import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.domain.enums.WorkItemState;
import static com.flowforge.auth.security.SecurityUtils.getCurrentUserEmail;
import static com.flowforge.auth.security.SecurityUtils.hasRole;

import com.flowforge.workitem.dto.WorkItemResponse;
import com.flowforge.workitem.repository.StateTransitionRepository;
import com.flowforge.workitem.repository.WorkItemRepository;
import java.util.EnumMap;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkItemTransitionServiceImp implements WorkItemTransitionService{

    private final WorkItemRepository workItemRepository;
    private final StateTransitionRepository transitionRepository;

    private static final EnumMap<WorkItemState, Set<WorkItemState>> ALLOWED_TRANSITIONS = new EnumMap<>(WorkItemState.class);

    static {
        ALLOWED_TRANSITIONS.put(WorkItemState.TODO, Set.of(WorkItemState.IN_PROGRESS));
        ALLOWED_TRANSITIONS.put(WorkItemState.IN_PROGRESS, Set.of(WorkItemState.REVIEW, WorkItemState.BLOCKED));
        ALLOWED_TRANSITIONS.put(WorkItemState.REVIEW, Set.of(WorkItemState.DONE));
        ALLOWED_TRANSITIONS.put(WorkItemState.BLOCKED, Set.of(WorkItemState.IN_PROGRESS));
        ALLOWED_TRANSITIONS.put(WorkItemState.DONE, Set.of());
    }

    public WorkItemTransitionServiceImp(WorkItemRepository workItemRepository,
                                     StateTransitionRepository transitionRepository) {
        this.workItemRepository = workItemRepository;
        this.transitionRepository = transitionRepository;
    }

    @Override
    @Transactional
    public WorkItemResponse transition(UUID id, WorkItemState newState) {
        WorkItem item = workItemRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Work item not found"));


        String currentUser = getCurrentUserEmail();

        if (!hasRole("ADMIN") && !item.getCreatedBy().equals(currentUser)) {
            throw new UnauthorizedActionException("You cannot modify someone else's work item");
        }

        WorkItemState oldState = item.getCurrentState();
        item.changeState(newState);

        transitionRepository.save(new StateTransition(id, oldState, newState));

        return mapToResponse(item);


    }
    private void validateTransition(WorkItemState fromState, WorkItemState toState) {
        if (!ALLOWED_TRANSITIONS.getOrDefault(fromState, Set.of()).contains(toState)) {
            throw new InvalidStateTransitionException("Invalid transition from " + fromState + " to " + toState);
        }
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
