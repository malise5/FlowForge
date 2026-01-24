package com.flowforge.workitem.domain.entity;

import com.flowforge.workitem.domain.enums.WorkItemState;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "state_transitions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateTransition {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "work_item_id", nullable = false)
    private UUID workItemId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkItemState fromState;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkItemState toState;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }


    public StateTransition(UUID workItemId, WorkItemState fromState, WorkItemState toState) {
        this.workItemId = workItemId;
        this.fromState = fromState;
        this.toState = toState;
    }
}
