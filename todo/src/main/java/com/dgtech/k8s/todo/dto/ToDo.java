package com.dgtech.k8s.todo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDo implements Serializable {
    public List<ToDoItems> items;
}
