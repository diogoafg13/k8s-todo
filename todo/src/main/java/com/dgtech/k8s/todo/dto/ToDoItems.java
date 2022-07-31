package com.dgtech.k8s.todo.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDoItems {

    private String name;
    private LocalDate date;
    private ToDoPriority priority;
    private LocalDateTime updateDate;
}
