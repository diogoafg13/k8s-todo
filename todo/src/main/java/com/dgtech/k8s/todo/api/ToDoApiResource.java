package com.dgtech.k8s.todo.api;

import com.dgtech.k8s.todo.ToDoProperties;
import com.dgtech.k8s.todo.dto.ToDo;
import com.dgtech.k8s.todo.dto.ToDoItems;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(ToDoProperties.ADDRESS)
public interface ToDoApiResource {

    @PostMapping
    public @ResponseBody ResponseEntity<ToDoItems> createNewToDo(@RequestBody ToDoItems item);

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<ToDoItems> getToDo(@PathVariable("id") String id);

    @GetMapping
    public @ResponseBody ResponseEntity<ToDo> getToDoList();

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<ToDoItems> patchToDo(@PathVariable("id") String id, @RequestBody ToDoItems itemUpdate);

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") String id);
}
