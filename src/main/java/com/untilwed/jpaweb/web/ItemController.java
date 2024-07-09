package com.untilwed.jpaweb.web;

import com.untilwed.jpaweb.domain.item.Book;
import com.untilwed.jpaweb.domain.item.Item;
import com.untilwed.jpaweb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;



    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createForm(){
        return "items/createItemForm";
    }

    /** 상품 수정 폼*/
    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){

        Item item = itemService.findOne(itemId);
        model.addAttribute(item);
        return "/items/updateItemForm";
    }

    /**상품 수정*/
    @PostMapping("/{itemId}/edit")
    public String updateItem(@ModelAttribute("item") Book item){

        itemService.saveItem(item);
        return "redirect:/items";
    }



    @GetMapping("/")
    public String list(Model model){

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @PostMapping("/new")
    public String create(Book item){
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
