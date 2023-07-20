package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    void saveItem() throws Exception {
        //given
        Item item = new Book();
        item.setName("Spring");

        //when
        itemService.saveItem(item);
        Item findItem = itemService.findOne(item.getId());

        //then
        assertThat(item).isEqualTo(findItem);
    }

    @Test
    void findItems() throws Exception {
        //given
        Item item1 = new Book();
        item1.setName("Spring");
        Item item2 = new Book();
        item2.setName("Oracle");

        //when
        itemService.saveItem(item1);
        itemService.saveItem(item2);
        List<Item> items = itemService.findItems();

        //then
        assertThat(items.size()).isEqualTo(2);
    }
}