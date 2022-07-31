package com.dgtech.k8s.todo.controller;

import com.dgtech.k8s.todo.api.ToDoApiResource;
import com.dgtech.k8s.todo.dao.ToDoDAO;
import com.dgtech.k8s.todo.dto.ToDo;
import com.dgtech.k8s.todo.dto.ToDoItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ToDoController implements ToDoApiResource {

    private ToDoDAO dao;

    @Autowired
    public void ToDoController(ToDoDAO dao){
        this.dao = dao;
    }

    @Override
    public ResponseEntity<ToDoItems> createNewToDo(ToDoItems item) {
        log.info("Create new ToDo item with body {}", item.toString());
        return new ResponseEntity<>(dao.createItem(item), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ToDoItems> getToDo(String id) {
        log.info("Get toDo item with id {} ", id);
        ToDoItems itemFound = dao.getItem(id);

        if(itemFound == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dao.getItem(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToDo> getToDoList() {
        log.info("Get all toDo items");
        return new ResponseEntity<>(dao.getAllItems(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToDoItems> patchToDo(String id, ToDoItems itemUpdate) {
        log.info("Patch item with id {}", id);
        ToDoItems patchedItem = dao.patchItem(id, itemUpdate);

        if(patchedItem == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(patchedItem, HttpStatus.OK);
    }

    @Override
    public void deleteItem(String id) {
        log.info("Delete toDo item with id {}", id);
        dao.deleteItem(id);
    }
}
