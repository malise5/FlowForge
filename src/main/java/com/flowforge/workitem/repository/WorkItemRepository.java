package com.flowforge.workitem.repository;

import com.flowforge.workitem.domain.entity.WorkItem;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkItemRepository extends JpaRepository<WorkItem,UUID> {
}
