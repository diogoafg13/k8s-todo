package com.dgtech.k8s.todo.dao;

import com.dgtech.k8s.todo.dto.ToDo;
import com.dgtech.k8s.todo.dto.ToDoItems;
import com.dgtech.k8s.todo.mapper.ToDoMapper;
import com.dgtech.k8s.todo.model.Item;
import com.dgtech.k8s.todo.repo.ItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ToDoDAOImpl implements ToDoDAO{

    private ItemRepo itemRepo;

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
        log.info("Item saved in database {}", itemSaved.toString());

        return ToDoMapper.INSTANCE.toToDoItems(itemSaved);
    }

    @Override
    public ToDoItems patchItem(String id, ToDoItems patchItem) {
        log.info("Search for item with id {}", id);
        Optional<Item> optItemToPatch = itemRepo.findById(id);

        if(!optItemToPatch.isPresent()){
            log.info("No item found for id {}", id);
            return null;
        }

        log.info("Map patch item to Entity");
        Item itemToPatch = ToDoMapper.INSTANCE.mergeItem(optItemToPatch.get());
        log.info("Item entity mapped: {} and will patched in database", itemToPatch);
        Item itemPatched = itemRepo.save(itemToPatch);
        log.info("Item patched in database {}", itemPatched.toString());

        return ToDoMapper.INSTANCE.toToDoItems(itemPatched);
    }

    @Override
    public void deleteItem(String id) {
        log.info("Search for item with id {}", id);
        Optional<Item> itemFound = itemRepo.findById(id);

        if(!itemFound.isPresent()){
            log.info("No item found for id {}", id);
        }

        itemRepo.deleteById(id);
    }

    @Override
    public ToDoItems getItem(String id) {
        log.info("Search for item with id {}", id);
        Optional<Item> itemFound = itemRepo.findById(id);

        if(!itemFound.isPresent()){
            log.info("No item found for id {}", id);
            return null;
        }

        log.info("Item found {}", itemFound.get().toString());
        return ToDoMapper.INSTANCE.toToDoItems(itemFound.get());

    }

    @Override
    public ToDo getAllItems() {
        log.info("Get all Items");
        List<Item> itemsFound = itemRepo.findAll();
        log.info("{} items found in database");

        return ToDo.builder().items(ToDoMapper.INSTANCE.toToDo(itemsFound)).build();
    }
}
