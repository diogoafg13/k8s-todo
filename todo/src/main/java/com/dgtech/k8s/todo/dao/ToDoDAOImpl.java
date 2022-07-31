package com.dgtech.k8s.todo.dao;

import com.dgtech.k8s.todo.dto.ToDo;
import com.dgtech.k8s.todo.dto.ToDoItems;
import com.dgtech.k8s.todo.mapper.ToDoMapper;
import com.dgtech.k8s.todo.model.Item;
import com.dgtech.k8s.todo.repo.ItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ToDoDAOImpl implements ToDoDAO{

    private final ItemRepo itemRepo;

    @Autowired
    public ToDoDAOImpl(ItemRepo itemRepo){
        this.itemRepo = itemRepo;
    }

    @Override
    public ToDoItems createItem(ToDoItems createItem) {
        log.info("Map create item to Entity");
        Item itemToSave = ToDoMapper.INSTANCE.toItem(createItem);
        itemToSave.setDate(LocalDate.now());
        log.info("Item entity mapped: {} and will save in database", itemToSave);
        Item itemSaved = itemRepo.save(itemToSave);
        log.info("Item saved in database {}", itemSaved);

        return ToDoMapper.INSTANCE.toToDoItems(itemSaved);
    }

    @Override
    public ToDoItems patchItem(String id, ToDoItems patchItem) {
        log.info("Search for item with id {}", id);
        Optional<Item> optItemToPatch = itemRepo.findById(id);

        if(optItemToPatch.isEmpty()){
            log.info("No item found for id {}", id);
            return null;
        }

        log.info("Map patch item to Entity");
        Item itemToPatch = ToDoMapper.INSTANCE.mergeItem(optItemToPatch.get(), patchItem);
        itemToPatch.setUpdateDate(LocalDateTime.now());
        log.info("Item entity mapped: {} and will patched in database", itemToPatch);
        Item itemPatched = itemRepo.save(itemToPatch);
        log.info("Item patched in database {}", itemPatched);

        return ToDoMapper.INSTANCE.toToDoItems(itemPatched);
    }

    @Override
    public void deleteItem(String id) {
        log.info("Search for item with id {}", id);
        Optional<Item> itemFound = itemRepo.findById(id);

        if(itemFound.isEmpty()){
            log.info("No item found for id {}", id);
        }

        itemRepo.deleteById(id);
    }

    @Override
    public ToDoItems getItem(String id) {
        log.info("Search for item with id {}", id);
        Optional<Item> itemFound = itemRepo.findById(id);

        if(itemFound.isEmpty()){
            log.info("No item found for id {}", id);
            return null;
        }

        log.info("Item found {}", itemFound.get());
        return ToDoMapper.INSTANCE.toToDoItems(itemFound.get());

    }

    @Override
    public ToDo getAllItems() {
        log.info("Get all Items");
        List<Item> itemsFound = itemRepo.findAll();
        log.info("{} items found in database", itemsFound.size());

        return ToDo.builder().items(ToDoMapper.INSTANCE.toToDo(itemsFound)).build();
    }
}
