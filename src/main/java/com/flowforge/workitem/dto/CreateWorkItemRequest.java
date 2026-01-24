package com.flowforge.workitem.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateWorkItemRequest( @NotBlank String title, String description) {
}
