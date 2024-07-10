package com.untilwed.jpaweb.repository;

import com.untilwed.jpaweb.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        } else {
            em.merge(item); // 이미 식별자값이 있으면 merge()로 수정(병합)한다.
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",
                Item.class).getResultList();
    }
}
