package com.untilwed.jpaweb.service;

import com.untilwed.jpaweb.domain.OrderItem;
import com.untilwed.jpaweb.domain.item.Book;
import com.untilwed.jpaweb.domain.item.Item;
import com.untilwed.jpaweb.domain.item.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProxyTest {

    @Autowired
    EntityManager em;

    @Test
    public void parentType_ProxyCheck(){

        //테스트 데이터 준비
        Book saveBook = new Book();
        saveBook.setName("jpabook");
        saveBook.setAuthor("kim");
        em.persist(saveBook);

        em.flush();
        em.clear();

        //테스트 시작
        Item proxyItem = em.getReference(Item.class, saveBook.getId());
        System.out.println("proxyItem = " + proxyItem.getClass());

        if(proxyItem instanceof Book){
            System.out.println("proxItem instance of Book");
            Book book = (Book) proxyItem;
            System.out.println("책 저자 = " + book.getAuthor());
        }

        //결과 검증
        Assertions.assertThat(proxyItem.getClass()).isEqualTo(Book.class);
        Assertions.assertThat(proxyItem).isInstanceOf(Book.class);
        Assertions.assertThat(proxyItem).isInstanceOf(Item.class);
    }

    //다형성과 프록시 조회 실행
    @Test
    public void inheritance_Proxy_DomainModel(){

        //테스트 데이터 준비
        Book book = new Book();
        book.setName("jpabook");
        book.setAuthor("kim");
        em.persist(book);

        OrderItem saveOrderItem = new OrderItem();
        saveOrderItem.setItem(book);
        em.persist(saveOrderItem);

        em.flush();
        em.clear();

        //테스트 시작
        OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
        Item item = orderItem.getItem();

        System.out.println("item = " + item.getClass());


        //결과 검증
        Assertions.assertThat(item.getClass()).isEqualTo(Book.class);
        Assertions.assertThat(item).isInstanceOf(Book.class);
        Assertions.assertThat(item).isInstanceOf(Item.class);
    }

    //JPA 페이징 배치 처리 예제
    public void usingBatch(){
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        int pageSize = 100;
        for(int i = 0; i < 10; i++){

            List<Item> resultList = em.createQuery("select i from Item i", Item.class)
                    .setFirstResult(i*pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();

            //비즈니스 로직 실행
            for(Item item : resultList){
                item.setPrice(item.getPrice() + 100);
            }

            em.flush();
            em.clear();
        }

        tx.commit();
        em.clear();
    }

}
