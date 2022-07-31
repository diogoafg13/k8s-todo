package com.dgtech.k8s.todo.dao;

import com.dgtech.k8s.todo.dto.ToDo;
import com.dgtech.k8s.todo.dto.ToDoItems;
import org.springframework.stereotype.Component;

public interface ToDoDAO {

    public ToDoItems createItem(ToDoItems createItem);

    public ToDoItems patchItem(String id, ToDoItems patchItem);

    public void deleteItem(String id);

    public ToDoItems getItem(String id);

    public ToDo getAllItems();
}
