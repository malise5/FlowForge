package com.flowforge.workitem.service;

import com.flowforge.workitem.domain.entity.StateTransition;
import com.flowforge.workitem.domain.entity.WorkItem;
import com.flowforge.workitem.repository.StateTransitionRepository;
import com.flowforge.workitem.repository.WorkItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public WorkItem create(String title, String description) {
        WorkItem item = new WorkItem(title, description);
        WorkItem saved = workItemRepository.save(item);


        transitionRepository.save(
                new StateTransition(
                        saved.getId(),
                        null,
                        saved.getCurrentState()
                )
        );

        return saved;
    }
}
