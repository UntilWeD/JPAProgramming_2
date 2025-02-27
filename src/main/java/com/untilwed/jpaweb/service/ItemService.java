package com.untilwed.jpaweb.service;

import com.untilwed.jpaweb.domain.item.Item;
import com.untilwed.jpaweb.repository.ItemRepository;
import com.untilwed.jpaweb.repository.ItemRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepositoryV2 itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).get();
    }
}
