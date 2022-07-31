package com.dgtech.k8s.todo.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum ToDoPriority {

    LOW("LOW"),
    NORMAL("NORMAL"),
    HIGH("HIGH");

    private String priority;

}
