package com.dgtech.k8s.todo.model;

import com.dgtech.k8s.todo.ToDoProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(ToDoProperties.MONGO_ITEM_DOC)
@Data
public class Item {

    @Id
    private String id;

    private String name;
    private LocalDate date;
    private String priority;
}
