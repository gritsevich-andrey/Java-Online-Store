package com.gmail.andreygritsevich.service.util;

import com.gmail.andreygritsevich.repository.model.Item;
import com.gmail.andreygritsevich.service.model.ItemDTO;

public class ItemConverterUtil {

    public static ItemDTO getDTOFromObject(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setUniqueNumber(item.getUniqueNumber());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setDescription(item.getDescription());
        return itemDTO;
    }

    public static Item getObjectFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

}
