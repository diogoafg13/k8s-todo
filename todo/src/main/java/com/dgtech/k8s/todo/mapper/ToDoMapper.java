package com.dgtech.k8s.todo.mapper;

import com.dgtech.k8s.todo.dto.ToDo;
import com.dgtech.k8s.todo.dto.ToDoItems;
import com.dgtech.k8s.todo.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ToDoMapper {

    ToDoMapper INSTANCE = Mappers.getMapper(ToDoMapper.class);

    Item toItem(ToDoItems item);
    ToDoItems toToDoItems(Item item);
    List<ToDoItems> toToDo(List<Item> itemsList);
    @Mapping(source = "itemToDo.name", target = "name")
    @Mapping(source = "itemToDo.priority", target = "priority")
    @Mapping(source = "item.date", target = "date")
    @Mapping(source = "item.id", target = "id")
    @Mapping(source = "itemToDo.updateDate", target = "updateDate")
    Item mergeItem(Item item, ToDoItems itemToDo);
}
