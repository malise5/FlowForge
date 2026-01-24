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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "work_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkItem {

    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkItemState currentState;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public void changeState(WorkItemState newState) {
        this.currentState = newState;
    }

    public WorkItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.currentState = WorkItemState.TODO;
        this.createdAt = Instant.now();
    }
}
