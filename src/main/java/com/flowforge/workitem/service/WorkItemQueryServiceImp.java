package com.flowforge.workitem.service;

import com.flowforge.workitem.dto.StateTransitionResponse;
import com.flowforge.workitem.repository.StateTransitionRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class WorkItemQueryServiceImp implements WorkItemQueryService{

    private final StateTransitionRepository transitionRepository;

    public WorkItemQueryServiceImp(StateTransitionRepository transitionRepository) {
        this.transitionRepository = transitionRepository;
    }

    @Override
    public List<StateTransitionResponse> getTimeline(UUID workItemId) {
        return transitionRepository.findByWorkItemIdOrderByChangedAtAsc(workItemId)
                .stream()
                .map(trans -> new StateTransitionResponse(
                        trans.getFromState(),
                        trans.getToState(),
                        trans.getChangedAt()
                )).toList();
    }
}
