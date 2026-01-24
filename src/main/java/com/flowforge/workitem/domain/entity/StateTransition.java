package com.flowforge.workitem.domain.entity;

import com.flowforge.workitem.domain.enums.WorkItemState;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "state_transitions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StateTransition {

    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "work_item_id", nullable = false)
    private UUID workItemId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkItemState fromState;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkItemState toState;

    @Column(name = "changed_at", nullable = false, updatable = false)
    private Instant changedAt;


    @PrePersist
    void onCreate() {
        this.changedAt = Instant.now();
    }


    public StateTransition(UUID workItemId, WorkItemState fromState, WorkItemState toState) {
        this.workItemId = workItemId;
        this.fromState = fromState;
        this.toState = toState;
    }
}
