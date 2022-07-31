package com.dgtech.k8s.todo.repo;

import com.dgtech.k8s.todo.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepo extends MongoRepository<Item, String> {
}
