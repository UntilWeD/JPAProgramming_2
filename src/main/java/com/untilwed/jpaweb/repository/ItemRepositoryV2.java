package com.untilwed.jpaweb.repository;

import com.untilwed.jpaweb.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
