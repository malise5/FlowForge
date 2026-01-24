package com.flowforge.workitem.repository;

import com.flowforge.workitem.domain.entity.StateTransition;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateTransitionRepository extends JpaRepository<StateTransition, UUID> {

    List<StateTransition> findByWorkItemIdOrderByCreatedAtAsc(UUID workItemId);
}
